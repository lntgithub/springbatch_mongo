package com.citigroup.gftp.spring.job;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "RunJobWithMongoRepositoryTest-context.xml" })
public class RunJobWithMongoRepositoryTest {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Test
	public void testJobRunWithMongoRepository() {
		Map<String, JobParameter> paramsMap = new HashMap<String, JobParameter>();
		paramsMap.put("input.file.name", new JobParameter("MyContracts.csv"));
		JobParameters params = new JobParameters(paramsMap);
		try {
			JobExecution jobExecution = jobLauncher.run(job, params);
			ExitStatus status = null;
			do {
				status = jobExecution.getExitStatus();
			} while (!status.isRunning());
			System.out.println("Job completed with status code "
					+ status.getExitCode() + "\nAnd status description "
					+ status.getExitDescription());
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
