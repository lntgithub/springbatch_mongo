/**
 * 
 */
package com.citigroup.gftp.spring.da.repository.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ap16737
 *
 */
@Document(collection = "counters")
public class IncrementerValue implements Serializable {
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
