/**
 * 
 */
package org.spuristo.core.model;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author dmartin
 *
 */
@JsonAutoDetect
@JsonTypeName("relation")
//@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="_clazz")
@Edge(value="relations")
public class Relation extends Thing {

	@From(lazy = true)
    @JsonAlias(value= {"from", "_from"})
	private ProvVertex from;

	@To(lazy = true)
    @JsonAlias(value= {"to", "_to"})
	private ProvVertex to;

	private String type;

	public Relation() {
		this.clazz = Relation.class.getSimpleName().toLowerCase();
	}

	/**
	 * Create a relation between to vertices, qualified by a descriptor.
	 * The parameters' order should make the relation creation intuitive:<br>
	 * A new relation between something linked in a specific way described by the descriptor to something else.
	 * @param from the "source" vertex
	 * @param type the relation descriptor
	 * @param to the "destination" vertex
	 */
	public Relation(ProvVertex from, String type, ProvVertex to) {
		this.clazz = Relation.class.getSimpleName().toLowerCase();
		this.from = from;
		this.to = to;
		this.type = type;
	}

	/**
	 * Constructor using the RelationTypes enum.
	 * @param from
	 * @param type
	 * @param to
	 */
	public Relation(ProvVertex from, TYPE type, ProvVertex to) {
		this(from, type.toString(), to);
	}

	public ProvVertex getFrom() {
		return from;
	}

	public void setFrom(ProvVertex from) {
		this.from = from;
	}

	public ProvVertex getTo() {
		return to;
	}

	public void setTo(ProvVertex to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return new StringBuilder(this.getClazz()).append(" ").append(super.toString())
				.append(", From: ").append((this.getFrom() != null) ? this.getFrom().toString() : "null")
				.append(", To: ").append((this.getTo() != null) ? this.getTo().toString() : "null")
				.toString();
	}

	public enum TYPE {

		USED("used"),
		REVISION_OF("wasRevisionOf"),
		DERIVED_FROM("wasDerivedFrom"),
		ACTED_ON_BEHALF_OF("actedOnBehalfOf"),
		WAS_ATTRIBUTED_TO("wasAttributedTo"),
		WAS_ASSOCIATED_WITH("wasAssociatedWith"),
		WAS_GENERATED_BY("wasGeneratedBy")
		;

		private final String type;

		TYPE(final String type) {
			this.type = type;
		}

	    @Override
	    public String toString() {
	        return type;
	    }

	}

}
