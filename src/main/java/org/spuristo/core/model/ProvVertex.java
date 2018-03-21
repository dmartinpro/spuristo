/**
 * 
 */
package org.spuristo.core.model;

import java.util.Collection;

import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

/**
 * @author dmartin
 *
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes(value={
	    @Type(name="entity", value=Entity.class), 
	    @Type(name="activity", value=Activity.class), 
	    @Type(name="agent", value=Agent.class)
	    })
public class ProvVertex extends Thing {

    @JsonIgnore
	@From(lazy=true)
	private Collection<Relation> fromRelations;

    @JsonIgnore
	@To(lazy=true)
	private Collection<Relation> toRelations;

    public ProvVertex() {
    		super();
	}

	public ProvVertex(String key) {
		super(key);
	}

	public Collection<Relation> getFromRelations() {
		return fromRelations;
	}

	public void setFromRelations(Collection<Relation> fromRelations) {
		this.fromRelations = fromRelations;
	}

	public Collection<Relation> getToRelations() {
		return toRelations;
	}

	public void setToRelations(Collection<Relation> toRelations) {
		this.toRelations = toRelations;
	}

}
