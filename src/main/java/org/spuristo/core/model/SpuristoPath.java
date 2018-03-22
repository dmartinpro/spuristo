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
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PathRelation other = (PathRelation) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

	}

	@JsonAutoDetect
	public static final class PathVertex {
		@JsonAlias({"clazz", "_clazz"})
		public String clazz;
		@JsonAlias({"id", "_id"})
		public String id;
		@JsonAlias({"key", "_key"})
		public String key;
		public List<Attribute> attributes;
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PathVertex other = (PathVertex) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		
	}

	@JsonAutoDetect
	public static final class VertexEdgePair {
		@JsonAlias({"vertex", "v"})
		public PathVertex vertex;
		@JsonAlias({"relation", "e"})
		public PathRelation relation;
	}

}
