/**
 *
 */
package com.citigroup.gftp.spring.da.repository.support;

import org.springframework.batch.item.database.support.DataFieldMaxValueIncrementerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Component;

/**
 * @author ap16737
 *
 */
@Component
public class MongoFieldMaxValueIncrementerFactory implements
DataFieldMaxValueIncrementerFactory {

	private MongoTemplate mongoTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.item.database.support.
	 * DataFieldMaxValueIncrementerFactory#getIncrementer(java.lang.String,
	 * java.lang.String)
	 */
	public DataFieldMaxValueIncrementer getIncrementer(String databaseType,
			String incrementerName) {
		if (isSupportedIncrementerType(databaseType)) {
			return new MongoFieldMaxValueIncrementer(mongoTemplate,
					incrementerName);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.item.database.support.
	 * DataFieldMaxValueIncrementerFactory
	 * #isSupportedIncrementerType(java.lang.String)
	 */
	public boolean isSupportedIncrementerType(String databaseType) {
		if (databaseType != null && databaseType.equalsIgnoreCase("mongodb")) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.item.database.support.
	 * DataFieldMaxValueIncrementerFactory#getSupportedIncrementerTypes()
	 */
	public String[] getSupportedIncrementerTypes() {
		return new String[] { "mongodb" };
	}

}
