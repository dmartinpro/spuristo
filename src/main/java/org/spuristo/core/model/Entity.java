/**
 * 
 */
package org.spuristo.core.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author dmartin
 *
 */
@JsonAutoDetect
@JsonTypeName("entity")
public class Entity extends ProvVertex {

    Entity() {
		this.clazz = Entity.class.getSimpleName().toLowerCase();
	}

    public Entity(String key) {
    		super(key);
    		this.clazz = Entity.class.getSimpleName().toLowerCase();
    }

	@Override
	public String toString() {
		return new StringBuilder(this.getClazz()).append(" ").append(super.toString()).toString();
	}

	public enum ENTITY_ATTRIBUTE implements PredefinedAttributeType {

		LOCATION("location"),
		CHECKSUM("checksum")
		;

		private final String attribute;

		private ENTITY_ATTRIBUTE(final String attribute) {
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
