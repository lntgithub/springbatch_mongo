/**
 *
 */
package com.citigroup.gftp.spring.mongo.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.citigroup.gftp.spring.da.repository.domain.JobExecutionDTO;

/**
 * @author ap16737
 *
 */
@Repository
public interface MongoJobExecutionRepository extends
		MongoRepository<JobExecutionDTO, BigInteger>,
MongoJobExecutionRepositoryCustom {

	@Query("{_id: ?0}")
	public JobExecutionDTO findById(BigInteger id);

	public List<JobExecutionDTO> findByJobInstanceId(Long jobInstanceId,
			Sort sort);

}
