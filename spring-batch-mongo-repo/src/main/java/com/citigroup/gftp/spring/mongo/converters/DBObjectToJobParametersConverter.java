/**
 *
 */
package com.citigroup.gftp.spring.mongo.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameter.ParameterType;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.citigroup.gftp.spring.da.repository.support.MongoJobRepositoryUtils;
import com.mongodb.DBObject;

/**
 * @author ap16737
 *
 */
@Component
public class DBObjectToJobParametersConverter implements
Converter<DBObject, JobParameters> {

	@Autowired
	@Qualifier("datePattern")
	private String dateConversionPattern;

	private SimpleDateFormat formatter;

	@PostConstruct
	public void initialize() {
		formatter = new SimpleDateFormat(dateConversionPattern);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.core.convert.converter.Converter#convert(java.lang
	 * .Object)
	 */
	@SuppressWarnings("unchecked")
	public JobParameters convert(DBObject source) {
		Map<String, JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
		if (source != null) {
			List<DBObject> params = (List<DBObject>) source
					.get(MongoJobRepositoryUtils.JOB_PARAMETERS_KEY);
			if (params != null) {
				for (DBObject param : params) {
					String paramName = (String) param
							.get(MongoJobRepositoryUtils.JOB_PARAMETER_NAME_KEY);
					String paramTypeStr = (String) param
							.get(MongoJobRepositoryUtils.JOB_PARAMETER_TYPE_KEY);
					ParameterType type = ParameterType.valueOf(paramTypeStr);
					switch (type) {
					case DATE:
						try {
							parameters
							.put(paramName,
									new JobParameter(
											formatter
											.parse((String) param
													.get(MongoJobRepositoryUtils.JOB_PARAMETER_VALUE_KEY)),
													(boolean) param
															.get(MongoJobRepositoryUtils.JOB_PARAMETER_IDENTIFYING_KEY)));
						} catch (ParseException pe) {
							// TODO
						}
						break;
					case DOUBLE:
						parameters
								.put(paramName,
										new JobParameter(
												(Double) param
														.get(MongoJobRepositoryUtils.JOB_PARAMETER_VALUE_KEY),
										(boolean) param
														.get(MongoJobRepositoryUtils.JOB_PARAMETER_IDENTIFYING_KEY)));

						break;
					case LONG:
						parameters
						.put(paramName,
								new JobParameter(
										(Long) param
										.get(MongoJobRepositoryUtils.JOB_PARAMETER_VALUE_KEY),
												(boolean) param
										.get(MongoJobRepositoryUtils.JOB_PARAMETER_IDENTIFYING_KEY)));

						break;
					case STRING:
						parameters
								.put(paramName,
										new JobParameter(
												(String) param
														.get(MongoJobRepositoryUtils.JOB_PARAMETER_VALUE_KEY),(boolean) param
														.get(MongoJobRepositoryUtils.JOB_PARAMETER_IDENTIFYING_KEY)));

						break;
					}
				}
			}
		}
		return new JobParameters(parameters);
	}
}
