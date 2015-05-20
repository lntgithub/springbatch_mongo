/**
 *
 */
package com.citigroup.gftp.spring.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.citigroup.gftp.spring.da.repository.domain.ExecutionContextDTO;

/**
 * @author ap16737
 *
 */
@Repository
public interface MongoExecutionContextRepository extends
		MongoRepository<ExecutionContextDTO, Long> {

	@Query(value = "{'execId' : ?0, 'type': 'j'}", fields = "{'contextData':1, '_id':0, '_class':0}")
	List<ExecutionContextDTO> findByJobExecutionId(Long executionId);

	@Query(value = "{'execId' : ?0, 'type': 's'}", fields = "{'contextData':1, '_id':0, '_class':0}")
	List<ExecutionContextDTO> findByStepExecutionId(Long executionId);
}
