/**
 *
 */
package com.citigroup.gftp.spring.mongo.converters;

import java.util.Map;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.citigroup.gftp.spring.da.repository.support.MongoJobRepositoryUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author ap16737
 *
 */
@Component
public class JobParametersToDBObjectConverter implements
Converter<JobParameters, DBObject> {

	public DBObject convert(JobParameters source) {
		DBObject obj = new BasicDBObject();
		if (source != null) {
			Map<String, JobParameter> params = source.getParameters();
			JobParameter curParam = null;
			for (String paramName : params.keySet()) {
				curParam = params.get(paramName);
				obj.put(MongoJobRepositoryUtils.JOB_PARAMETER_NAME_KEY,
						paramName);
				obj.put(MongoJobRepositoryUtils.JOB_PARAMETER_TYPE_KEY,
						curParam.getType().toString());
				obj.put(MongoJobRepositoryUtils.JOB_PARAMETER_IDENTIFYING_KEY,
						curParam.isIdentifying());
				obj.put(MongoJobRepositoryUtils.JOB_PARAMETER_VALUE_KEY,
						curParam.getValue());
			}
		}
		return obj;
	}

}
