/**
 *
 */
package com.citigroup.gftp.spring.da.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.dao.ExecutionContextDao;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.citigroup.gftp.spring.da.repository.domain.ExecutionContextDTO;
import com.citigroup.gftp.spring.da.repository.domain.ExecutionContextType;
import com.citigroup.gftp.spring.mongo.repositories.MongoExecutionContextRepository;

/**
 * @author ap16737
 *
 */
@Component
public class MongoExecutionContextDao implements ExecutionContextDao {

	// protected MongoTemplate mongoTemplate;

	// private static final DBObject FIELDS_DBOBJECT = new Field()
	// .include("contextData").exclude("_id").exclude("type")
	// .getFieldsObject();
	//
	// private static final Criteria JOB_EXECUTION_CONTEXT_CRITERIA =
	// where("type")
	// .is('j');
	//
	// private static final Criteria STEP_EXECUTION_CONTEXT_CRITERIA = where(
	// "type").is('s');

	@Autowired
	protected MongoExecutionContextRepository ecRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.core.repository.dao.ExecutionContextDao#
	 * getExecutionContext(org.springframework.batch.core.JobExecution)
	 */
	public ExecutionContext getExecutionContext(JobExecution jobExecution) {
		Long executionId = jobExecution.getId();
		Assert.notNull(executionId, "ExecutionId must not be null.");

		List<ExecutionContextDTO> results = ecRepository
				.findByJobExecutionId(executionId);
		// List<ExecutionContext> results = mongoTemplate.find(new BasicQuery(
		// JOB_EXECUTION_CONTEXT_CRITERIA.and("execId").is(executionId)
		// .getCriteriaObject(), FIELDS_DBOBJECT),
		// ExecutionContext.class, collectionPrefix + "ExecutionContext");
		if (results.size() > 0) {
			return results.get(0).toExecutionContext();
		} else {
			return new ExecutionContext();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.core.repository.dao.ExecutionContextDao#
	 * getExecutionContext(org.springframework.batch.core.StepExecution)
	 */
	public ExecutionContext getExecutionContext(StepExecution stepExecution) {
		Long executionId = stepExecution.getId();
		Assert.notNull(executionId, "ExecutionId must not be null.");
		List<ExecutionContextDTO> results = ecRepository
				.findByStepExecutionId(executionId);
		// List<ExecutionContext> results = mongoTemplate.find(new BasicQuery(
		// STEP_EXECUTION_CONTEXT_CRITERIA.and("execId").is(executionId)
		// .getCriteriaObject(), FIELDS_DBOBJECT),
		// ExecutionContext.class, collectionPrefix + "ExecutionContext");
		if (results.size() > 0) {
			return results.get(0).toExecutionContext();
		} else {
			return new ExecutionContext();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.core.repository.dao.ExecutionContextDao#
	 * saveExecutionContext(org.springframework.batch.core.JobExecution)
	 */
	public void saveExecutionContext(JobExecution jobExecution) {
		Long executionId = jobExecution.getId();
		ExecutionContext executionContext = jobExecution.getExecutionContext();
		Assert.notNull(executionId, "ExecutionId must not be null.");
		Assert.notNull(executionContext,
				"The ExecutionContext must not be null.");
		ecRepository.save(new ExecutionContextDTO(executionId.toString(),
				executionContext.entrySet(),
				ExecutionContextType.JOB_EXECUTION_CONTEXT_TYPE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.core.repository.dao.ExecutionContextDao#
	 * saveExecutionContext(org.springframework.batch.core.StepExecution)
	 */
	public void saveExecutionContext(StepExecution stepExecution) {
		Long executionId = stepExecution.getId();
		ExecutionContext executionContext = stepExecution.getExecutionContext();
		Assert.notNull(executionId, "ExecutionId must not be null.");
		Assert.notNull(executionContext,
				"The ExecutionContext must not be null.");
		ecRepository.save(new ExecutionContextDTO(executionId.toString(),
				executionContext.entrySet(),
				ExecutionContextType.STEP_EXECUTION_CONTEXT_TYPE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.core.repository.dao.ExecutionContextDao#
	 * saveExecutionContexts(java.util.Collection)
	 */
	public void saveExecutionContexts(Collection<StepExecution> stepExecutions) {
		Assert.notNull(stepExecutions,
				"Attempt to save an null collection of step executions");
		for (StepExecution stepExecution : stepExecutions) {
			Long executionId = stepExecution.getId();
			ExecutionContext executionContext = stepExecution
					.getExecutionContext();
			Assert.notNull(executionId, "ExecutionId must not be null.");
			Assert.notNull(executionContext,
					"The ExecutionContext must not be null.");
			ecRepository.save(new ExecutionContextDTO(executionId.toString(),
					executionContext.entrySet(),
					ExecutionContextType.STEP_EXECUTION_CONTEXT_TYPE));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.core.repository.dao.ExecutionContextDao#
	 * updateExecutionContext(org.springframework.batch.core.JobExecution)
	 */
	public void updateExecutionContext(JobExecution jobExecution) {
		Long executionId = jobExecution.getId();
		ExecutionContext executionContext = jobExecution.getExecutionContext();
		Assert.notNull(executionId, "ExecutionId must not be null.");
		Assert.notNull(executionContext,
				"The ExecutionContext must not be null.");
		ecRepository.save(new ExecutionContextDTO(executionId.toString(),
				executionContext.entrySet(),
				ExecutionContextType.JOB_EXECUTION_CONTEXT_TYPE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.core.repository.dao.ExecutionContextDao#
	 * updateExecutionContext(org.springframework.batch.core.StepExecution)
	 */
	public void updateExecutionContext(StepExecution stepExecution) {
		Long executionId = stepExecution.getId();
		ExecutionContext executionContext = stepExecution.getExecutionContext();
		Assert.notNull(executionId, "ExecutionId must not be null.");
		Assert.notNull(executionContext,
				"The ExecutionContext must not be null.");
		ecRepository.save(new ExecutionContextDTO(executionId.toString(),
				executionContext.entrySet(),
				ExecutionContextType.STEP_EXECUTION_CONTEXT_TYPE));
	}

	// /**
	// * @return the mongoTemplate
	// */
	// public MongoTemplate getMongoTemplate() {
	// return mongoTemplate;
	// }
	//
	// /**
	// * @param mongoTemplate
	// * the mongoTemplate to set
	// */
	// public void setMongoTemplate(MongoTemplate mongoTemplate) {
	// this.mongoTemplate = mongoTemplate;
	// }
}
