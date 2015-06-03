/**
 *
 */
package com.citigroup.gftp.spring.da.repository.support;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.mongodb.BasicDBObject;

/**
 * @author ap16737
 *
 */
public class MongoFieldMaxValueIncrementer implements
		DataFieldMaxValueIncrementer {

	private MongoTemplate mongoTemplate;

	private String incrementerName;

	public MongoFieldMaxValueIncrementer(MongoTemplate mongoTemplate,
			String incrementerName) {
		this.mongoTemplate = mongoTemplate;
		this.incrementerName = incrementerName;
	}

	protected Long getNextValue() {
		Query query = Query.query(Criteria.where("_id").is(incrementerName));
		Update update = Update.update("$inc", new BasicDBObject("current", 1));
		IncrementerValue value = mongoTemplate.findAndModify(query, update,
				IncrementerValue.class);
		return value.getValue();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer
	 * #nextIntValue()
	 */
	public int nextIntValue() throws DataAccessException {
		return getNextValue().intValue();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer
	 * #nextLongValue()
	 */
	public long nextLongValue() throws DataAccessException {
		return getNextValue();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer
	 * #nextStringValue()
	 */
	@Override
	public String nextStringValue() throws DataAccessException {
		return getNextValue().toString();
	}

	@Document(collection = "counters")
	public static class IncrementerValue implements Serializable {
		private static final long serialVersionUID = -5041214287867871902L;

		@Id
		private String id;

		@Field("current")
		private Long value;

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the value
		 */
		public Long getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(Long value) {
			this.value = value;
		}

	}

}
