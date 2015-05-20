/**
 *
 */
package com.citigroup.gftp.spring.da.repository.support;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author ap16737
 *
 */
@Component
public class PropertiesBasedEntityCollectionNameMapper implements
		EntityCollectionNameMapper {

	@Autowired
	@Qualifier("entityToCollectionMappings")
	private Properties entityToCollectionMappings;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.citigroup.gftp.spring.da.repository.EntityCollectionNameMapper#
	 * mapToCollectionName(java.lang.Class)
	 */
	public String mapToCollectionName(Class<?> entityClass) {
		return entityToCollectionMappings.getProperty(entityClass.getName()+ ".collectionName");
				
	}

}
