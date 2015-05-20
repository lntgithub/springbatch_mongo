/**
 * 
 */
package com.citigroup.gftp.spring.da.repository;

import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.dao.JobInstanceDao;

/**
 * @author ap16737
 *
 */
public class MongoJobInstanceDao implements JobInstanceDao {

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.repository.dao.JobInstanceDao#createJobInstance(java.lang.String, org.springframework.batch.core.JobParameters)
	 */
	@Override
	public JobInstance createJobInstance(String jobName,
			JobParameters jobParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.repository.dao.JobInstanceDao#getJobInstance(java.lang.String, org.springframework.batch.core.JobParameters)
	 */
	@Override
	public JobInstance getJobInstance(String jobName,
			JobParameters jobParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.repository.dao.JobInstanceDao#getJobInstance(java.lang.Long)
	 */
	@Override
	public JobInstance getJobInstance(Long instanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.repository.dao.JobInstanceDao#getJobInstance(org.springframework.batch.core.JobExecution)
	 */
	@Override
	public JobInstance getJobInstance(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.repository.dao.JobInstanceDao#getJobInstances(java.lang.String, int, int)
	 */
	@Override
	public List<JobInstance> getJobInstances(String jobName, int start,
			int count) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.repository.dao.JobInstanceDao#getJobNames()
	 */
	@Override
	public List<String> getJobNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.repository.dao.JobInstanceDao#findJobInstancesByName(java.lang.String, int, int)
	 */
	@Override
	public List<JobInstance> findJobInstancesByName(String jobName, int start,
			int count) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.repository.dao.JobInstanceDao#getJobInstanceCount(java.lang.String)
	 */
	@Override
	public int getJobInstanceCount(String jobName) throws NoSuchJobException {
		// TODO Auto-generated method stub
		return 0;
	}

}
