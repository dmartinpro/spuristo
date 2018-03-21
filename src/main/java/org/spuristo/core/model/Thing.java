/**
 * 
 */
package org.spuristo.core.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Key;
import com.arangodb.springframework.annotation.Rev;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author dmartin
 *
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
//@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="_clazz")
@JsonAutoDetect
@JsonSubTypes(value={
    @Type(name="entity", value=Entity.class), 
    @Type(name="activity", value=Activity.class), 
    @Type(name="agent", value=Agent.class),
    @Type(name="relation", value=Relation.class)
    })
@Document
public class Thing {

	@Id
    @JsonIgnore
    @JsonAlias(value= {"id", "_id"})
	private String id;

	@Key
    @JsonAlias(value= {"key", "_key"})
	private String key;

	@Rev
    @JsonIgnore
    @JsonAlias(value= {"revision", "_rev", "rev"})
	private String revision;

	// Should be fed with the class name to describe in a Thing collection what kind of entity each object is.
    @JsonAlias(value= {"clazz", "_clazz"})
	String clazz;

    protected List<Attribute> attributes;


	public Thing() {
	}

	public Thing(String key) {
		if (key != null) {
			this.key = key;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getClazz() {
		return this.clazz;
	}

	public void setClazz(String _clazz) {
		this.clazz = _clazz;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return new StringBuilder("[id: ").append(this.getId()).append(", key: ").append(this.getKey()).append(", rev: ").append(this.getRevision()).append("]").toString();
	}

}
