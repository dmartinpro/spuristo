/**
 * 
 */
package org.spuristo.core.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author dmartin
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6134497260459185982L;

	private String resourceIdentifier;

	public ResourceNotFoundException() {
	}
	
	public ResourceNotFoundException(String resourceIdentifier) {
		this.resourceIdentifier = resourceIdentifier;
	}

	@Override
	public String getMessage() {
		return new StringBuilder("Resource ").append((this.resourceIdentifier != null) ? this.resourceIdentifier : "")
				.append(" not found")
				.toString();
	}

}
