/**
 *
 */
package com.citigroup.gftp.spring.da.repository.domain;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ap16737
 *
 */
@Document
public class ExecutionContextDTO {

	@Id
	private String id;

	@Field("context")
	private Map<String, Object> context;

	@Field("type")
	private ExecutionContextType type;

	public ExecutionContextDTO() {
	}

	public ExecutionContextDTO(String id,
			Set<Map.Entry<String, Object>> contextData,
			ExecutionContextType type) {
		context = new ConcurrentHashMap<String, Object>();
		if (contextData != null) {
			for (Map.Entry<String, Object> entry : contextData) {
				context.put(entry.getKey(), entry.getValue());
			}
		}
		this.type = type;
	}

	public ExecutionContextDTO(ExecutionContext executionContext) {
		this();
		if (executionContext == null) {
			return;
		}
		for (Entry<String, Object> entry : executionContext.entrySet()) {
			context.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * @return the map
	 */
	public Map<String, Object> getContextData() {
		return context;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setContextData(Map<String, Object> map) {
		context = map;
	}

	public static ExecutionContextDTO fromExecutionContext(
			ExecutionContext context) {
		return new ExecutionContextDTO(context);
	}

	public ExecutionContext toExecutionContext() {
		return new ExecutionContext(context);
	}
}
