/**
 * 
 */
package org.spuristo.core.model;

import org.spuristo.core.common.AttributeBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author dmartin
 *
 */
@JsonAutoDetect
@JsonTypeName("agent")
public class Agent extends ProvVertex {

	Agent() {
		this.clazz = Agent.class.getSimpleName().toLowerCase();
	}

	public Agent(String key) {
		this(key, null);
	}

	public Agent(String key, TYPE type) {
		super(key);
		this.clazz = Agent.class.getSimpleName().toLowerCase();
		if (type != null) {
			this.attributes = AttributeBuilder.init()
					.addType(Agent.AGENT_ATTRIBUTE.TYPE).andObjectAsString(type.toString())
					.build();
		}
	}

	@Override
	public String toString() {
		return new StringBuilder(this.getClazz()).append(" ").append(super.toString()).toString();
	}

	public enum TYPE {
		PERSON("person"), ORGANIZATION("organization"), SOFTWARE("software");

		private final String type;

		private TYPE(final String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return type;
		}

	}

	public enum AGENT_ATTRIBUTE implements PredefinedAttributeType {

		TYPE("type"),
		FIRSTNAME("firstname"),
		LASTNAME("lastname")
		;

		private final String attribute;

		private AGENT_ATTRIBUTE(final String attribute) {
			this.attribute = attribute;
		}

		public String getName() {
			return this.attribute;
		}

		@Override
		public String toString() {
			return attribute;
		}

	}

}
