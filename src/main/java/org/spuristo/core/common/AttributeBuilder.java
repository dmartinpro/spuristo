package org.spuristo.core.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spuristo.core.model.PredefinedAttributeType;
import org.spuristo.core.model.Attribute;

/**
 * Builder aimed at facilitating the construction of Attributes lists.
 * <p>
 * Something like:
 * <code>
 * <pre>
 * AttributeBuilder.init()
 *     .addType("a type").andObjectAsString(oneObjectInstance)
 *     .build();
 * </pre>
 * </code>
 * returns a List containing in this case something like (in JSON):
 * <code>
 * <pre>
 * {
 *    "type": "a type",
 *    "value": {
 *      "_default": "oneObjectInstance content"
 *    }
 *  }
 * </pre>
 * </code>
 * 
 * @author dmartin
 *
 */
public class AttributeBuilder {

	private static final String DEFAULT = "_default";

	private List<Attribute> attributes = new ArrayList<>();

	private String type;

	private AttributeBuilder() {
	}

	public static AttributeBuilder init() {
		return new AttributeBuilder();
	}

	public AttributeBuilder addType(String type) {
		this.type = type;
		return this;
	}

	public AttributeBuilder addType(PredefinedAttributeType type) {
		this.type = type.getName();
		return this;
	}

	public AttributeBuilder andObjectAsString(Object value) {
		final Map<String, String> map = new HashMap<>();
		map.put(DEFAULT, (String) value);

		attributes.add(new Attribute(this.type, map));
		return this;
	}

	public AttributeBuilder andMapValue(Map<String, String> value) {
		attributes.add(new Attribute(this.type, value));
		return this;
	}

	public List<Attribute> build() {
		return attributes;
	}

}
