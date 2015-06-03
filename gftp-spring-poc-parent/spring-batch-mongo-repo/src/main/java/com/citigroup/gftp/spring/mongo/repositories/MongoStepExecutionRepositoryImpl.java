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

import com.citigroup.gftp.spring.da.repository.domain.StepExecutionDTO;
import com.citigroup.gftp.spring.da.repository.support.EntityCollectionNameMapper;
import com.citigroup.gftp.spring.da.repository.support.MongoJobRepositoryUtils;

/**
 * @author ap16737
 *
 */
@Component
public class MongoStepExecutionRepositoryImpl implements
MongoStepExecutionRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private EntityCollectionNameMapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citigroup.gftp.spring.mongo.repositories.
	 * MongoStepExecutionRepositoryCustom#findForIDAndVersion(java.lang.Long,
	 * java.lang.String)
	 */
	public StepExecutionDTO updateForIDAndVersion(StepExecutionDTO dto) {
		StepExecutionDTO retDTO = null;
		if (dto != null) {
			Query query = Query.query(Criteria
					.where(MongoJobRepositoryUtils.STEP_EXECUTION_ID_KEY)
					.is(dto.getId()).and(MongoJobRepositoryUtils.VERSION_KEY)
					.is(dto.getVersion()));

			Update update = MongoJobRepositoryUtils.toUpdate(dto);

			retDTO = mongoTemplate.findAndModify(query, update,
					StepExecutionDTO.class,
					mapper.mapToCollectionName(StepExecutionDTO.class));
		}
		return retDTO;
	}

}
