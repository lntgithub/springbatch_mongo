/**
 *
 */
package com.citigroup.gftp.spring.da.repository.support;

import static com.mongodb.BasicDBObjectBuilder.start;

import org.springframework.data.mongodb.core.query.Update;

import com.citigroup.gftp.spring.da.repository.domain.JobExecutionDTO;
import com.citigroup.gftp.spring.da.repository.domain.StepExecutionDTO;
import com.mongodb.DBObject;

/**
 * @author ap16737
 *
 */
public class MongoJobRepositoryUtils {

	public static final String STEP_EXECUTION_ID_KEY = "_id";
	public static final String STEP_NAME_KEY = "name";
	public static final String JOB_EXECUTION_ID_KEY = "jobExecId";
	public static final String START_TIME_KEY = "startTime";
	public static final String END_TIME_KEY = "endTime";
	public static final String STATUS_KEY = "status";
	public static final String COMMIT_COUNT_KEY = "commitCount";
	public static final String READ_COUNT_KEY = "readCount";
	public static final String FILTER_COUT_KEY = "filterCount";
	public static final String WRITE_COUNT_KEY = "writeCount";
	public static final String EXIT_CODE_KEY = "exitCode";
	public static final String EXIT_MESSAGE_KEY = "exitDesc";
	public static final String READ_SKIP_COUNT_KEY = "readSkipCount";
	public static final String WRITE_SKIP_COUNT_KEY = "writeSkipCount";
	public static final String PROCESS_SKIP_COUNT_KEY = "processSkipCount";
	public static final String ROLLBACK_COUNT_KEY = "rollbackCount";
	public static final String LAST_UPDATED_KEY = "lastUpdated";
	public static final String VERSION_KEY = "version";

	public static final String CREATE_TIME_KEY = "createTime";
	public static final String JOB_INSTANCE_ID_KEY = "jobInstanceId";
	public static final String JOB_PARAMETERS_KEY = "jobParameters";
	public static final String JOB_PARAMETER_TYPE_KEY = "parameterType";
	public static final String JOB_PARAMETER_NAME_KEY = "paramName";
	public static final String JOB_PARAMETER_VALUE_KEY = "paramValue";
	public static final String JOB_PARAMETER_IDENTIFYING_KEY = "paramIdentifying";
	public static final String JOB_CONFIGURATION_NAME_KEY = "jobConfigName";

	public static DBObject toDBObject(StepExecutionDTO dto) {
		return start().add(STEP_EXECUTION_ID_KEY, dto.getId())
				.add(STEP_NAME_KEY, dto.getStepName())
				.add(JOB_EXECUTION_ID_KEY, dto.getJobExecutionId())
				.add(START_TIME_KEY, dto.getStartTime())
				.add(END_TIME_KEY, dto.getEndTime())
				.add(STATUS_KEY, dto.getStatus().toString())
				.add(COMMIT_COUNT_KEY, dto.getCommitCount())
				.add(READ_COUNT_KEY, dto.getReadCount())
				.add(FILTER_COUT_KEY, dto.getFilterCount())
				.add(WRITE_COUNT_KEY, dto.getWriteCount())
				.add(EXIT_CODE_KEY, dto.getExitCode())
				.add(EXIT_MESSAGE_KEY, dto.getExitDescription())
				.add(READ_SKIP_COUNT_KEY, dto.getReadSkipCount())
				.add(WRITE_SKIP_COUNT_KEY, dto.getWriteSkipCount())
				.add(PROCESS_SKIP_COUNT_KEY, dto.getProcessSkipCount())
				.add(ROLLBACK_COUNT_KEY, dto.getRollbackCount())
				.add(LAST_UPDATED_KEY, dto.getLastUpdated()).get();
	}

	public static Update toUpdate(StepExecutionDTO dto) {
		DBObject updateObj = start().add(START_TIME_KEY, dto.getStartTime())
				.add(END_TIME_KEY, dto.getEndTime())
				.add(STATUS_KEY, dto.getStatus().toString())
				.add(COMMIT_COUNT_KEY, dto.getCommitCount())
				.add(READ_COUNT_KEY, dto.getReadCount())
				.add(FILTER_COUT_KEY, dto.getFilterCount())
				.add(WRITE_COUNT_KEY, dto.getWriteCount())
				.add(EXIT_CODE_KEY, dto.getExitCode())
				.add(EXIT_MESSAGE_KEY, dto.getExitDescription())
				.add(VERSION_KEY, dto.getVersion())
				.add(READ_SKIP_COUNT_KEY, dto.getReadSkipCount())
				.add(WRITE_SKIP_COUNT_KEY, dto.getWriteSkipCount())
				.add(PROCESS_SKIP_COUNT_KEY, dto.getProcessSkipCount())
				.add(ROLLBACK_COUNT_KEY, dto.getRollbackCount())
				.add(LAST_UPDATED_KEY, dto.getLastUpdated()).get();
		return Update.fromDBObject(updateObj, new String[] {});
	}

	public static Update toUpdate(JobExecutionDTO dto) {
		DBObject updateObj = start().add(START_TIME_KEY, dto.getStartTime())
				.add(END_TIME_KEY, dto.getEndTime())
				.add(STATUS_KEY, dto.getStatus().toString())
				.add(EXIT_CODE_KEY, dto.getExitCode())
				.add(EXIT_MESSAGE_KEY, dto.getExitDescription())
				.add(VERSION_KEY, dto.getVersion())
				.add(CREATE_TIME_KEY, dto.getCreateTime())
				.add(LAST_UPDATED_KEY, dto.getLastUpdated()).get();
		return Update.fromDBObject(updateObj, new String[] {});
	}
}
