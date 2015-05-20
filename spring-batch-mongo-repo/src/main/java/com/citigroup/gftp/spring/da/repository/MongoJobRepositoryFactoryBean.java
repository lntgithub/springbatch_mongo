/**
 *
 */
package com.citigroup.gftp.spring.da.repository;

import org.springframework.batch.core.repository.dao.ExecutionContextDao;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.batch.core.repository.dao.JobInstanceDao;
import org.springframework.batch.core.repository.dao.StepExecutionDao;
import org.springframework.batch.core.repository.support.AbstractJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author ap16737
 *
 */
@Component("mongoJobRepository")
public class MongoJobRepositoryFactoryBean extends
		AbstractJobRepositoryFactoryBean {

	@Autowired
	private MongoJobExecutionDao jobExecutionDao;

	@Autowired
	private MongoJobInstanceDao jobInstanceDao;

	@Autowired
	private MongoStepExecutionDao stepExecutionDao;

	@Autowired
	private MongoExecutionContextDao executionContextDao;

	@Autowired
	public MongoJobRepositoryFactoryBean(
			PlatformTransactionManager transactionManager) {
		setTransactionManager(transactionManager);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.support.
	 * AbstractJobRepositoryFactoryBean#createJobInstanceDao()
	 */
	protected JobInstanceDao createJobInstanceDao() throws Exception {
		return getJobInstanceDao();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.support.
	 * AbstractJobRepositoryFactoryBean#createJobExecutionDao()
	 */
	protected JobExecutionDao createJobExecutionDao() throws Exception {
		return getJobExecutionDao();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.support.
	 * AbstractJobRepositoryFactoryBean#createStepExecutionDao()
	 */
	protected StepExecutionDao createStepExecutionDao() throws Exception {
		return getStepExecutionDao();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.repository.support.
	 * AbstractJobRepositoryFactoryBean#createExecutionContextDao()
	 */
	protected ExecutionContextDao createExecutionContextDao() throws Exception {
		return getExecutionContextDao();
	}

	public JobExecutionDao getJobExecutionDao() {
		return jobExecutionDao;
	}

	public JobInstanceDao getJobInstanceDao() {
		return jobInstanceDao;
	}

	public StepExecutionDao getStepExecutionDao() {
		return stepExecutionDao;
	}

	public ExecutionContextDao getExecutionContextDao() {
		return executionContextDao;
	}
}
