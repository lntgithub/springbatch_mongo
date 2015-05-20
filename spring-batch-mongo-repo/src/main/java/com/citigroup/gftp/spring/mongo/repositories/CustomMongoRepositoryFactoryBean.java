/**
 *
 */
package com.citigroup.gftp.spring.mongo.repositories;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.stereotype.Component;

import com.citigroup.gftp.spring.da.repository.support.EntityCollectionNameMapper;

/**
 * @author ap16737
 *
 */
@Component
public class CustomMongoRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
		extends MongoRepositoryFactoryBean<T, S, ID> {

	@Autowired
	private EntityCollectionNameMapper mapper;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.mongodb.repository.support.
	 * MongoRepositoryFactoryBean
	 * #getFactoryInstance(org.springframework.data.mongodb
	 * .core.MongoOperations)
	 */
	protected RepositoryFactorySupport getFactoryInstance(
			MongoOperations operations) {
		return new CustomMongoRepositoryFactory(operations, mapper);
	}
}
