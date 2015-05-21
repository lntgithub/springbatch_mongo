/**
 *
 */
package com.citigroup.gftp.spring.mongo.repositories;

import com.citigroup.gftp.spring.da.repository.domain.JobExecutionDTO;

/**
 * @author ap16737
 *
 */
public interface MongoJobExecutionRepositoryCustom {

	public JobExecutionDTO updateForIDAndVersion(JobExecutionDTO stepExecution);

}
