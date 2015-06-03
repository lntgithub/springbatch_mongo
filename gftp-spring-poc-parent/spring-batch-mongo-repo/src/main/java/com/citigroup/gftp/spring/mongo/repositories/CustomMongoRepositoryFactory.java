/**
 *
 */
package com.citigroup.gftp.spring.mongo.repositories;

import java.io.Serializable;

import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

import com.citigroup.gftp.spring.da.repository.support.EntityCollectionNameMapper;

/**
 * @author ap16737
 *
 */
public class CustomMongoRepositoryFactory extends MongoRepositoryFactory {

	private EntityCollectionNameMapper mapper;

	private MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext;

	public CustomMongoRepositoryFactory(MongoOperations ops,
			EntityCollectionNameMapper mapper) {
		super(ops);
		this.mapper = mapper;
		mappingContext = ops.getConverter().getMappingContext();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.data.mongodb.repository.support.MongoRepositoryFactory
	 * #getEntityInformation(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T, ID extends Serializable> MongoEntityInformation<T, ID> getEntityInformation(
			Class<T> domainClass) {
		MongoEntityInformation<T, ID> retValue = null;
		MongoPersistentEntity<?> entity = mappingContext
				.getPersistentEntity(domainClass);
		String collectionName = mapper.mapToCollectionName(domainClass);
		if (collectionName != null && !collectionName.isEmpty()) {
			retValue = new MappingMongoEntityInformation<T, ID>(
					(MongoPersistentEntity<T>) entity, collectionName);
		} else {
			retValue = new MappingMongoEntityInformation<T, ID>(
					(MongoPersistentEntity<T>) entity);
		}
		return retValue;
	}
}
