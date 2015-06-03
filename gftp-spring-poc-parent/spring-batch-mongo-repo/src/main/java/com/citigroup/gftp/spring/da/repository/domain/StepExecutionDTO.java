/**
 *
 */
package com.citigroup.gftp.spring.da.repository.domain;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;

import com.citigroup.gftp.spring.da.repository.support.MongoJobRepositoryUtils;

/**
 * @author ap16737
 *
 */
@Document
public class StepExecutionDTO {

	@Id
	private Long id;

	@Field(MongoJobRepositoryUtils.STEP_NAME_KEY)
	private String stepName;

	@Field(MongoJobRepositoryUtils.JOB_EXECUTION_ID_KEY)
	private Long jobExecutionId;

	@Field(MongoJobRepositoryUtils.STATUS_KEY)
	private BatchStatus status = BatchStatus.STARTING;

	@Field(MongoJobRepositoryUtils.VERSION_KEY)
	private Integer version;

	@Field(MongoJobRepositoryUtils.READ_COUNT_KEY)
	private int readCount = 0;

	@Field(MongoJobRepositoryUtils.WRITE_COUNT_KEY)
	private int writeCount = 0;

	@Field(MongoJobRepositoryUtils.COMMIT_COUNT_KEY)
	private int commitCount = 0;

	@Field(MongoJobRepositoryUtils.ROLLBACK_COUNT_KEY)
	private int rollbackCount = 0;

	@Field(MongoJobRepositoryUtils.READ_SKIP_COUNT_KEY)
	private int readSkipCount = 0;

	@Field(MongoJobRepositoryUtils.PROCESS_SKIP_COUNT_KEY)
	private int processSkipCount = 0;

	@Field(MongoJobRepositoryUtils.WRITE_SKIP_COUNT_KEY)
	private int writeSkipCount = 0;

	@Field(MongoJobRepositoryUtils.START_TIME_KEY)
	private Date startTime = new Date(System.currentTimeMillis());

	@Field(MongoJobRepositoryUtils.END_TIME_KEY)
	private Date endTime = null;

	@Field(MongoJobRepositoryUtils.LAST_UPDATED_KEY)
	private Date lastUpdated = null;

	@Field(MongoJobRepositoryUtils.EXIT_CODE_KEY)
	private String exitCode;

	@Field(MongoJobRepositoryUtils.EXIT_MESSAGE_KEY)
	private String exitDescription;

	@Field(MongoJobRepositoryUtils.FILTER_COUT_KEY)
	private int filterCount;

	public static StepExecutionDTO fromStepExecution(StepExecution stepExecution) {
		StepExecutionDTO dto = null;
		if (stepExecution != null) {
			dto = new StepExecutionDTO(stepExecution.getStepName(),
					stepExecution.getId());
			dto.setJobExecutionId(stepExecution.getJobExecutionId());
			dto.setStatus(stepExecution.getStatus());
			dto.setVersion(stepExecution.getVersion());
			dto.setReadCount(stepExecution.getReadCount());
			dto.setWriteCount(stepExecution.getWriteCount());
			dto.setCommitCount(stepExecution.getCommitCount());
			dto.setRollbackCount(stepExecution.getRollbackCount());
			dto.setReadSkipCount(stepExecution.getReadSkipCount());
			dto.setProcessSkipCount(stepExecution.getProcessSkipCount());
			dto.setWriteSkipCount(stepExecution.getWriteSkipCount());
			dto.setStartTime(stepExecution.getStartTime());
			dto.setEndTime(stepExecution.getEndTime());
			dto.setLastUpdated(stepExecution.getLastUpdated());
			if (stepExecution.getExitStatus() != null) {
				dto.setExitCode(stepExecution.getExitStatus().getExitCode());
				dto.setExitDescription(stepExecution.getExitStatus()
						.getExitDescription());
			}
			dto.setFilterCount(stepExecution.getFilterCount());
		}
		return dto;
	}

	public StepExecution toStepExecution(JobExecution jobExecution) {
		StepExecution stepExecution = new StepExecution(stepName, jobExecution,
				id);
		stepExecution.setStatus(getStatus());
		stepExecution.setVersion(getVersion());
		stepExecution.setReadCount(getReadCount());
		stepExecution.setWriteCount(getWriteCount());
		stepExecution.setCommitCount(getCommitCount());
		stepExecution.setRollbackCount(getRollbackCount());
		stepExecution.setReadSkipCount(getReadSkipCount());
		stepExecution.setProcessSkipCount(getProcessSkipCount());
		stepExecution.setWriteSkipCount(getWriteSkipCount());
		stepExecution.setStartTime(getStartTime());
		stepExecution.setEndTime(getEndTime());
		stepExecution.setLastUpdated(getLastUpdated());
		if (getExitCode() != null) {
			stepExecution.setExitStatus(new ExitStatus(getExitCode(),
					getExitDescription()));
		}
		stepExecution.setFilterCount(getFilterCount());
		return stepExecution;
	}

	public StepExecutionDTO() {
	}

	/**
	 * Constructor with mandatory properties.
	 *
	 * @param stepName
	 *            the step to which this execution belongs
	 * @param jobExecution
	 *            the current job execution
	 * @param id
	 *            the id of this execution
	 */
	public StepExecutionDTO(String stepName, Long id) {
		this(stepName);
		Assert.notNull(id,
				"The entity Id must be provided to re-hydrate an existing StepExecution");
		setId(id);
	}

	/**
	 * Constructor that requires only a stepName. Intended only to be used via
	 * serialization libraries to address the circular reference between
	 * {@link JobExecution} and StepExecution.
	 *
	 * @param stepName
	 *            the name of the executed step
	 */
	private StepExecutionDTO(String stepName) {
		super();
		Assert.hasLength(stepName);
		this.stepName = stepName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * Public setter for the version needed only by repository methods.
	 *
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Increment the version number
	 */
	public void incrementVersion() {
		if (version == null) {
			version = 0;
		} else {
			version = version + 1;
		}
	}

	/**
	 * @return the jobExecutionId
	 */
	public Long getJobExecutionId() {
		return jobExecutionId;
	}

	/**
	 * @param jobExecutionId
	 *            the jobExecutionId to set
	 */
	public void setJobExecutionId(Long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	/**
	 * @return the status
	 */
	public BatchStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(BatchStatus status) {
		this.status = status;
	}

	/**
	 * @return the readCount
	 */
	public int getReadCount() {
		return readCount;
	}

	/**
	 * @param readCount
	 *            the readCount to set
	 */
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	/**
	 * @return the writeCount
	 */
	public int getWriteCount() {
		return writeCount;
	}

	/**
	 * @param writeCount
	 *            the writeCount to set
	 */
	public void setWriteCount(int writeCount) {
		this.writeCount = writeCount;
	}

	/**
	 * @return the commitCount
	 */
	public int getCommitCount() {
		return commitCount;
	}

	/**
	 * @param commitCount
	 *            the commitCount to set
	 */
	public void setCommitCount(int commitCount) {
		this.commitCount = commitCount;
	}

	/**
	 * @return the rollbackCount
	 */
	public int getRollbackCount() {
		return rollbackCount;
	}

	/**
	 * @param rollbackCount
	 *            the rollbackCount to set
	 */
	public void setRollbackCount(int rollbackCount) {
		this.rollbackCount = rollbackCount;
	}

	/**
	 * @return the readSkipCount
	 */
	public int getReadSkipCount() {
		return readSkipCount;
	}

	/**
	 * @param readSkipCount
	 *            the readSkipCount to set
	 */
	public void setReadSkipCount(int readSkipCount) {
		this.readSkipCount = readSkipCount;
	}

	/**
	 * @return the processSkipCount
	 */
	public int getProcessSkipCount() {
		return processSkipCount;
	}

	/**
	 * @param processSkipCount
	 *            the processSkipCount to set
	 */
	public void setProcessSkipCount(int processSkipCount) {
		this.processSkipCount = processSkipCount;
	}

	/**
	 * @return the writeSkipCount
	 */
	public int getWriteSkipCount() {
		return writeSkipCount;
	}

	/**
	 * @param writeSkipCount
	 *            the writeSkipCount to set
	 */
	public void setWriteSkipCount(int writeSkipCount) {
		this.writeSkipCount = writeSkipCount;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
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
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated
	 *            the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the filterCount
	 */
	public int getFilterCount() {
		return filterCount;
	}

	/**
	 * @param filterCount
	 *            the filterCount to set
	 */
	public void setFilterCount(int filterCount) {
		this.filterCount = filterCount;
	}

	/**
	 * @return the stepName
	 */
	public String getStepName() {
		return stepName;
	}

	/**
	 * @return the exitCode
	 */
	public String getExitCode() {
		return exitCode;
	}

	/**
	 * @param exitCode
	 *            the exitCode to set
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
	 * @param exitDescription
	 *            the exitDescription to set
	 */
	public void setExitDescription(String exitDescription) {
		this.exitDescription = exitDescription;
	}

	/**
	 * @param stepName
	 *            the stepName to set
	 */
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
}
