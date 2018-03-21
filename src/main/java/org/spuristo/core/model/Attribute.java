/**
 * 
 */
package org.spuristo.core.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author dmartin
 *
 */
@JsonAutoDetect
public class Attribute {

	private String name;

	private Map<String, String> value = new HashMap<>();

	public Attribute() {
	}

	public Attribute(String name, Map<String, String> value) {
		this.name = name;
		this.value = value;
	}

	public Attribute(PredefinedAttributeType attributeType, Map<String, String> value) {
		this(attributeType.getName(), value);
	}

	public String getName() {
		return name;
	}

	public void setNamee(String name) {
		this.name = name;
	}

	public Map<String, String> getValue() {
		return value;
	}

	public void setValue(Map<String, String> value) {
		this.value = value;
	}

	public static enum GENERIC_ATTRIBUTE implements PredefinedAttributeType {

		TYPE("type"),
		LABEL("label"),
		ROLE("role"),
		LOCATION("location"),
		VALUE("value"),
		CONFIGURATION("configuration"),
		PROV_KEY("key"),
		OTHER("other");

		private final String type;

		GENERIC_ATTRIBUTE(final String type) {
			this.type = type;
		}

	    @Override
	    public String toString() {
	        return type;
	    }

		@Override
		public String getName() {
			return type;
		}

	}

}
