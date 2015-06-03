/**
 *
 */
package com.citigroup.gftp.spring.da.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.dao.StepExecutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.citigroup.gftp.spring.da.repository.domain.StepExecutionDTO;
import com.citigroup.gftp.spring.da.repository.support.EntityCollectionNameMapper;
import com.citigroup.gftp.spring.da.repository.support.MongoJobRepositoryUtils;
import com.citigroup.gftp.spring.mongo.repositories.MongoStepExecutionRepository;

/**
 * @author ap16737
 *
 */
@Component
public class MongoStepExecutionDao implements StepExecutionDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	@Qualifier("stepExecIdIncrementer")
	private DataFieldMaxValueIncrementer incrementer;

	@Autowired
	private EntityCollectionNameMapper mapper;

	@Autowired
	private MongoStepExecutionRepository seRepository;

	/**
	 * Validate StepExecution. At a minimum, JobId, StartTime, and Status cannot
	 * be null. EndTime can be null for an unfinished job.
	 *
	 * @throws IllegalArgumentException
	 */
	private void validateStepExecution(StepExecution stepExecution) {
		Assert.notNull(stepExecution);
		Assert.notNull(stepExecution.getStepName(),
				"StepExecution step name cannot be null.");
		Assert.notNull(stepExecution.getStartTime(),
				"StepExecution start time cannot be null.");
		Assert.notNull(stepExecution.getStatus(),
				"StepExecution status cannot be null.");
	}

	private void prepareStepExecutionForSave(StepExecution stepExecution) {
		validateStepExecution(stepExecution);
		Assert.isNull(stepExecution.getId(),
				"to-be-saved (not updated) StepExecution can't already have an id assigned");
		Assert.isNull(stepExecution.getVersion(),
				"to-be-saved (not updated) StepExecution can't already have a version assigned");
		stepExecution.setId(incrementer.nextLongValue());
		stepExecution.incrementVersion();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.StepExecutionDao#
	 * saveStepExecution(org.springframework.batch.core.StepExecution)
	 */
	public void saveStepExecution(StepExecution stepExecution) {
		prepareStepExecutionForSave(stepExecution);
		StepExecutionDTO dto = StepExecutionDTO
				.fromStepExecution(stepExecution);
		seRepository.save(dto);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.StepExecutionDao#
	 * saveStepExecutions(java.util.Collection)
	 */
	public void saveStepExecutions(Collection<StepExecution> stepExecutions) {
		if (stepExecutions != null && !stepExecutions.isEmpty()) {

			List<StepExecutionDTO> dtos = new ArrayList<StepExecutionDTO>();
			for (StepExecution stepExec : stepExecutions) {
				prepareStepExecutionForSave(stepExec);
				dtos.add(StepExecutionDTO.fromStepExecution(stepExec));
			}
			seRepository.save(dtos);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.StepExecutionDao#
	 * updateStepExecution(org.springframework.batch.core.StepExecution)
	 */
	public void updateStepExecution(StepExecution stepExecution) {
		validateStepExecution(stepExecution);
		Assert.notNull(stepExecution.getId(),
				"StepExecution Id cannot be null. StepExecution must be saved"
						+ " before it can be updated.");
		synchronized (stepExecution) {
			StepExecutionDTO dto = seRepository
					.updateForIDAndVersion(StepExecutionDTO
							.fromStepExecution(stepExecution));
			if (dto == null) {
				// this means we were trying to save an obsolete version of the
				// step execution.
				StepExecutionDTO newDTO = seRepository.findById(stepExecution
						.getId());
				throw new OptimisticLockingFailureException(
						"Attempt to update step execution id="
								+ stepExecution.getId()
								+ " with wrong version ("
								+ stepExecution.getVersion()
								+ "), where current version is "
								+ newDTO.getVersion());
			}
			stepExecution.incrementVersion();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.StepExecutionDao#
	 * getStepExecution(org.springframework.batch.core.JobExecution,
	 * java.lang.Long)
	 */
	public StepExecution getStepExecution(JobExecution jobExecution,
			Long stepExecutionId) {
		Collection<StepExecutionDTO> executions = seRepository
				.findAllByJobExecutionIDAndStepExecutionID(
						jobExecution.getId(), stepExecutionId);
		Assert.state(
				executions.size() <= 1,
				"There can be at most one step execution with given name for single job execution");
		if (executions.isEmpty()) {
			return null;
		} else {
			return executions.iterator().next().toStepExecution(jobExecution);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.StepExecutionDao#
	 * addStepExecutions(org.springframework.batch.core.JobExecution)
	 */
	public void addStepExecutions(JobExecution jobExecution) {
		Sort sortCriteria = new Sort(new Order(Sort.Direction.ASC,
				MongoJobRepositoryUtils.STEP_EXECUTION_ID_KEY));
		List<StepExecutionDTO> dtos = seRepository.findAllByJobExecutionID(
				jobExecution.getId(), sortCriteria);
		if (dtos != null && !dtos.isEmpty()) {
			for (StepExecutionDTO dto : dtos) {
				dto.toStepExecution(jobExecution);
			}
		}
	}
}
