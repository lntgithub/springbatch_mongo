/**
 *
 */
package com.citigroup.gftp.spring.da.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.batch.core.repository.dao.NoSuchObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.citigroup.gftp.spring.da.repository.domain.JobExecutionDTO;
import com.citigroup.gftp.spring.da.repository.support.EntityCollectionNameMapper;
import com.citigroup.gftp.spring.da.repository.support.MongoJobRepositoryUtils;
import com.citigroup.gftp.spring.mongo.repositories.MongoJobExecutionRepository;

/**
 * @author ap16737
 *
 */
@Component
public class MongoJobExecutionDao implements JobExecutionDao {

	@Autowired
	@Qualifier("jobExecIdIncrementer")
	private DataFieldMaxValueIncrementer incrementer;

	@Autowired
	private EntityCollectionNameMapper mapper;

	@Autowired
	private MongoJobExecutionRepository jeRepository;

	/**
	 * Validate JobExecution. At a minimum, JobId, StartTime, EndTime, and
	 * Status cannot be null.
	 *
	 * @param jobExecution
	 * @throws IllegalArgumentException
	 */
	private void validateJobExecution(JobExecution jobExecution) {

		Assert.notNull(jobExecution);
		Assert.notNull(jobExecution.getJobId(),
				"JobExecution Job-Id cannot be null.");
		Assert.notNull(jobExecution.getStatus(),
				"JobExecution status cannot be null.");
		Assert.notNull(jobExecution.getCreateTime(),
				"JobExecution create time cannot be null");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.JobExecutionDao#
	 * saveJobExecution(org.springframework.batch.core.JobExecution)
	 */
	public void saveJobExecution(JobExecution jobExecution) {
		jeRepository.save(JobExecutionDTO.fromJobExecution(jobExecution));
	}

	private JobExecutionDTO getUpdatedDTO(JobExecutionDTO dto,
			JobExecutionDTO origDTO) {
		dto.setStartTime(origDTO.getStartTime());
		dto.setEndTime(origDTO.getEndTime());
		dto.setStatus(origDTO.getStatus());
		dto.setExitCode(origDTO.getExitCode());
		dto.setExitDescription(origDTO.getExitDescription());
		dto.setVersion(origDTO.getVersion() + 1);
		dto.setCreateTime(origDTO.getCreateTime());
		dto.setLastUpdated(origDTO.getLastUpdated());
		return dto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.JobExecutionDao#
	 * updateJobExecution(org.springframework.batch.core.JobExecution)
	 */
	public void updateJobExecution(JobExecution jobExecution) {
		validateJobExecution(jobExecution);
		Assert.notNull(
				jobExecution.getId(),
				"JobExecution ID cannot be null. JobExecution must be saved before it can be updated");

		Assert.notNull(
				jobExecution.getVersion(),
				"JobExecution version cannot be null. JobExecution must be saved before it can be updated");
		JobExecutionDTO origDTO = JobExecutionDTO
				.fromJobExecution(jobExecution);
		synchronized (jobExecution) {
			JobExecutionDTO dto = jeRepository.findOne(origDTO.getId());
			if (dto != null) {
				// it means we found the execution to be updated.
				JobExecutionDTO updatedDTO = jeRepository
						.updateForIDAndVersion(getUpdatedDTO(dto, origDTO));
				if (updatedDTO == null) {
					// this means we were trying to save an obsolete version of
					// the
					// step execution.
					JobExecutionDTO currDTO = jeRepository.findById(origDTO
							.getId());
					throw new OptimisticLockingFailureException(
							"Attempt to update job execution id="
									+ currDTO.getId() + " with wrong version ("
									+ dto.getVersion()
									+ "), where current version is "
									+ currDTO.getVersion());
				}
			} else {
				throw new NoSuchObjectException("Invalid JobExecution, ID "
						+ jobExecution.getId() + " not found.");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.JobExecutionDao#
	 * findJobExecutions(org.springframework.batch.core.JobInstance)
	 */
	public List<JobExecution> findJobExecutions(JobInstance jobInstance) {
		Assert.notNull(jobInstance, "Job cannot be null.");
		Assert.notNull(jobInstance.getId(), "Job Id cannot be null.");
		Sort sortCriteria = new Sort(new Order(Sort.Direction.DESC,
				MongoJobRepositoryUtils.JOB_EXECUTION_ID_KEY));
		List<JobExecutionDTO> dtos = jeRepository.findByJobInstanceId(
				jobInstance.getId(), sortCriteria);
		List<JobExecution> executions = new ArrayList<JobExecution>();
		if (dtos != null && !dtos.isEmpty()) {
			for (JobExecutionDTO dto : dtos) {
				executions.add(dto.toJobExecution(jobInstance));
			}
		}
		return executions;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.JobExecutionDao#
	 * getLastJobExecution(org.springframework.batch.core.JobInstance)
	 */
	public JobExecution getLastJobExecution(JobInstance jobInstance) {
		// TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.JobExecutionDao#
	 * findRunningJobExecutions(java.lang.String)
	 */
	public Set<JobExecution> findRunningJobExecutions(String jobName) {
		// TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.batch.core.repository.dao.JobExecutionDao#getJobExecution
	 * (java.lang.Long)
	 */
	public JobExecution getJobExecution(Long executionId) {
		// TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.dao.JobExecutionDao#
	 * synchronizeStatus(org.springframework.batch.core.JobExecution)
	 */
	public void synchronizeStatus(JobExecution jobExecution) {
		// TODO

	}
}
