/**
 *
 */
package com.citigroup.gftp.spring.mongo.repositories;

import com.citigroup.gftp.spring.da.repository.domain.StepExecutionDTO;

/**
 * @author ap16737
 *
 */
public interface MongoStepExecutionRepositoryCustom {

	public StepExecutionDTO updateForIDAndVersion(StepExecutionDTO stepExecution);

	// public Integer findVersionForStepExecutionID(Long id);

}
