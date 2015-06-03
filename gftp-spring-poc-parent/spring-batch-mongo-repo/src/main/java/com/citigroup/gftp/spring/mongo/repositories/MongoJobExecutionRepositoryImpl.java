/**
 *
 */
package com.citigroup.gftp.spring.mongo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.citigroup.gftp.spring.da.repository.domain.JobExecutionDTO;
import com.citigroup.gftp.spring.da.repository.support.EntityCollectionNameMapper;
import com.citigroup.gftp.spring.da.repository.support.MongoJobRepositoryUtils;

/**
 * @author ap16737
 *
 */
@Component
public class MongoJobExecutionRepositoryImpl implements
MongoJobExecutionRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private EntityCollectionNameMapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citigroup.gftp.spring.mongo.repositories.
	 * MongoJobExecutionRepositoryCustom
	 * #updateForIDAndVersion(com.citigroup.gftp
	 * .spring.da.repository.domain.JobExecutionDTO)
	 */
	public JobExecutionDTO updateForIDAndVersion(JobExecutionDTO dto) {
		JobExecutionDTO retDTO = null;
		if (dto != null) {
			Query query = Query.query(Criteria
					.where(MongoJobRepositoryUtils.JOB_EXECUTION_ID_KEY)
					.is(dto.getId()).and(MongoJobRepositoryUtils.VERSION_KEY)
					.is(dto.getVersion()));

			Update update = MongoJobRepositoryUtils.toUpdate(dto);

			retDTO = mongoTemplate.findAndModify(query, update,
					JobExecutionDTO.class,
					mapper.mapToCollectionName(JobExecutionDTO.class));
		}
		return retDTO;
	}

}
