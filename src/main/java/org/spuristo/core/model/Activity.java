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
@JsonTypeName("activity")
public class Activity extends ProvVertex {

    Activity() {
    		this.clazz = Activity.class.getSimpleName().toLowerCase();
	}

    public Activity(String key) {
		super(key);
		this.clazz = Activity.class.getSimpleName().toLowerCase();
    }

    public Activity(String key, String label, long startTime, long endTime) {
		super(key);
		this.clazz = Activity.class.getSimpleName().toLowerCase();
		this.attributes = AttributeBuilder.init()
				.addType(Activity.ACTIVITY_ATTRIBUTE.LABEL).andObjectAsString(label)
				.addType(Activity.ACTIVITY_ATTRIBUTE.START_TIME).andObjectAsString(Long.toString(startTime))
				.addType(Activity.ACTIVITY_ATTRIBUTE.END_TIME).andObjectAsString(Long.toString(endTime))
				.build();
    }

	@Override
	public String toString() {
		return new StringBuilder(this.getClazz()).append(" ").append(super.toString()).toString();
	}

	public enum ACTIVITY_ATTRIBUTE implements PredefinedAttributeType {

		START_TIME("start_time"),
		END_TIME("end_time"),
		LABEL("label")
		;

		private final String attribute;

		private ACTIVITY_ATTRIBUTE(final String attribute) {
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
