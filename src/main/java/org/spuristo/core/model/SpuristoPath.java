/**
 * 
 */
package org.spuristo.core.model;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Wrapper to return a full path with flatten representation of entities (Activity, Agent, Entity, Relation)
 *
 * @author dmartin
 *
 */
public class SpuristoPath {

	private Collection<PathRelation> edges;
	private Collection<PathVertex> vertices;

	public SpuristoPath() {
	}

	public Collection<PathRelation> getEdges() {
		return edges;
	}

	public void setEdges(Collection<PathRelation> edges) {
		this.edges = edges;
	}

	public Collection<PathVertex> getVertices() {
		return vertices;
	}

	public void setVertices(Collection<PathVertex> vertices) {
		this.vertices = vertices;
	}

	@JsonAutoDetect
	public static final class PathRelation {
	
		@JsonAlias({"id", "_id"})
		public String id;
		@JsonAlias({"key", "_key"})
		public String key;
		@JsonAlias({"from", "_from", "source"})
		public String source;
		@JsonAlias({"to", "_to", "target"})
		public String target;
		public String type;
		public List<Attribute> attributes;

	}

	public static final class PathVertex {
		@JsonAlias({"clazz", "_clazz"})
		public String clazz;
		@JsonAlias({"id", "_id"})
		public String id;
		@JsonAlias({"key", "_key"})
		public String key;
		public List<Attribute> attributes;		
	}
}
