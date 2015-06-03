/**
 *
 */
package com.citigroup.gftp.spring.mongo.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.citigroup.gftp.spring.da.repository.domain.StepExecutionDTO;

/**
 * @author ap16737
 *
 */
@Repository
public interface MongoStepExecutionRepository extends
		MongoRepository<StepExecutionDTO, Long>,
		MongoStepExecutionRepositoryCustom {

	@Query("{_id: ?0}")
	StepExecutionDTO findById(Long id);

	@Query("{jobExecId : ?0, _id: ?1}")
	List<StepExecutionDTO> findAllByJobExecutionIDAndStepExecutionID(
			Long jobID, Long stepID);

	@Query("{jobExecId : ?0}")
	List<StepExecutionDTO> findAllByJobExecutionID(Long jobID, Sort sort);

}
