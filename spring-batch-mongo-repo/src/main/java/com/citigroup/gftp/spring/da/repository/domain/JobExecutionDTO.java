/**
 *
 */
package com.citigroup.gftp.spring.da.repository.domain;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.citigroup.gftp.spring.da.repository.support.MongoJobRepositoryUtils;

/**
 * @author ap16737
 *
 */
@Document
public class JobExecutionDTO {

	@Id
	private Long id;

	@Field(MongoJobRepositoryUtils.JOB_INSTANCE_ID_KEY)
	private Long jobInstanceId;

	@Field(MongoJobRepositoryUtils.START_TIME_KEY)
	private Date startTime = null;

	@Field(MongoJobRepositoryUtils.END_TIME_KEY)
	private Date endTime = null;

	@Field(MongoJobRepositoryUtils.STATUS_KEY)
	private BatchStatus status = BatchStatus.STARTING;

	@Field(MongoJobRepositoryUtils.EXIT_CODE_KEY)
	private String exitCode;

	@Field(MongoJobRepositoryUtils.EXIT_MESSAGE_KEY)
	private String exitDescription;

	@Field(MongoJobRepositoryUtils.VERSION_KEY)
	private Integer version;

	@Field(MongoJobRepositoryUtils.CREATE_TIME_KEY)
	private Date createTime = new Date(System.currentTimeMillis());

	@Field(MongoJobRepositoryUtils.LAST_UPDATED_KEY)
	private Date lastUpdated = null;

	@Field(MongoJobRepositoryUtils.JOB_CONFIGURATION_NAME_KEY)
	private String jobConfigurationName;

	@Field(MongoJobRepositoryUtils.JOB_PARAMETERS_KEY)
	private JobParameters parameters;

	public JobExecutionDTO() {

	}

	public static JobExecutionDTO fromJobExecution(JobExecution jobExecution) {
		JobExecutionDTO dto = null;
		if (jobExecution != null) {
			dto = new JobExecutionDTO();
			dto.id = jobExecution.getId();
			dto.jobInstanceId = jobExecution.getJobId();
			dto.startTime = jobExecution.getStartTime();
			dto.endTime = jobExecution.getEndTime();
			dto.status = jobExecution.getStatus();
			if (jobExecution.getExitStatus() != null) {
				dto.exitCode = jobExecution.getExitStatus().getExitCode();
				dto.exitDescription = jobExecution.getExitStatus()
						.getExitDescription();
			}
			dto.version = jobExecution.getVersion();
			dto.createTime = jobExecution.getCreateTime();
			dto.lastUpdated = jobExecution.getLastUpdated();
			dto.jobConfigurationName = jobExecution.getJobConfigurationName();
			dto.parameters = jobExecution.getJobParameters();
		}
		return dto;
	}

	public JobExecution toJobExecution(JobInstance jobInstance) {
		JobExecution jobExecution = null;
		if (jobInstance == null) {
			jobExecution = new JobExecution(id, parameters,
					jobConfigurationName);
		} else {
			jobExecution = new JobExecution(jobInstance, id, parameters,
					jobConfigurationName);
		}
		jobExecution.setStartTime(startTime);
		jobExecution.setEndTime(endTime);
		jobExecution.setStatus(status);
		if (exitCode != null && !exitCode.trim().isEmpty()) {
			jobExecution
			.setExitStatus(new ExitStatus(exitCode, exitDescription));
		}
		jobExecution.setVersion(version);
		jobExecution.setCreateTime(createTime);
		jobExecution.setLastUpdated(lastUpdated);
		return jobExecution;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the jobInstanceId
	 */
	public Long getJobInstanceId() {
		return jobInstanceId;
	}

	/**
	 * @param jobInstanceId the jobInstanceId to set
	 */
	public void setJobInstanceId(Long jobInstanceId) {
		this.jobInstanceId = jobInstanceId;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the status
	 */
	public BatchStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(BatchStatus status) {
		this.status = status;
	}

	/**
	 * @return the exitCode
	 */
	public String getExitCode() {
		return exitCode;
	}

	/**
	 * @param exitCode the exitCode to set
	 */
	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}

	/**
	 * @return the exitDescription
	 */
	public String getExitDescription() {
		return exitDescription;
	}

	/**
	 * @param exitDescription the exitDescription to set
	 */
	public void setExitDescription(String exitDescription) {
		this.exitDescription = exitDescription;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the jobConfigurationName
	 */
	public String getJobConfigurationName() {
		return jobConfigurationName;
	}

	/**
	 * @param jobConfigurationName the jobConfigurationName to set
	 */
	public void setJobConfigurationName(String jobConfigurationName) {
		this.jobConfigurationName = jobConfigurationName;
	}

	/**
	 * @return the parameters
	 */
	public JobParameters getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(JobParameters parameters) {
		this.parameters = parameters;
	}

	
}
