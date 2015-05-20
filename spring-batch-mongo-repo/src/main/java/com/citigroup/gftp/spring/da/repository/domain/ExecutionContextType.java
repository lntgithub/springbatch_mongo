/**
 *
 */
package com.citigroup.gftp.spring.da.repository.domain;

/**
 * @author ap16737
 *
 */
public enum ExecutionContextType {

	JOB_EXECUTION_CONTEXT_TYPE('j'), STEP_EXECUTION_CONTEXT_TYPE('s');

	private char type;

	private ExecutionContextType(char type) {
		this.type = type;
	}

	public char getType() {
		return type;
	}
}
