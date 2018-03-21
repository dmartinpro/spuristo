/**
 * 
 */
package org.spuristo.core.repository;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;
import org.spuristo.core.model.Relation;
import org.spuristo.core.model.Thing;

import com.arangodb.ArangoCursor;
import com.arangodb.entity.DocumentEntity;
import com.arangodb.entity.MultiDocumentEntity;
import com.arangodb.entity.UserEntity;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.model.CollectionCreateOptions;
import com.arangodb.model.DocumentCreateOptions;
import com.arangodb.model.DocumentDeleteOptions;
import com.arangodb.model.DocumentReadOptions;
import com.arangodb.model.DocumentReplaceOptions;
import com.arangodb.model.DocumentUpdateOptions;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.arangodb.springframework.core.ArangoOperations;
import com.arangodb.springframework.core.ArangoOperations.UpsertStrategy;
import com.arangodb.springframework.core.CollectionOperations;
import com.arangodb.springframework.core.UserOperations;
import com.arangodb.springframework.core.convert.ArangoConverter;
import com.arangodb.springframework.core.mapping.ArangoMappingContext;
import com.arangodb.springframework.core.mapping.ArangoPersistentEntity;
import com.arangodb.util.MapBuilder;
import com.arangodb.velocypack.VPackSlice;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dmartin
 *
 */
public class SpuristoArangoOperations {

	@Autowired
	private ArangoOperations operations;

	@Autowired
	private ObjectMapper objectMapper;
	
	private ArangoMappingContext context;

	private Log log = LogFactory.getLog(SpuristoArangoOperations.class);
	
	@PostConstruct
	private void postInit() {
		this.context = (ArangoMappingContext) operations.getConverter().getMappingContext();
	}

	public ArangoOperations getArangoOperations() {
		return operations;
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#query(java.lang.String,
	 * java.util.Map, com.arangodb.model.AqlQueryOptions, java.lang.Class)
	 */
	public <T> ArangoCursor<T> query(String query, Map<String, Object> bindVars, AqlQueryOptions options,
			Class<T> entityClass) throws DataAccessException {
		return operations.query(query, bindVars, options, entityClass);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#delete(java.lang.Iterable,
	 * java.lang.Class, com.arangodb.model.DocumentDeleteOptions)
	 */
	public MultiDocumentEntity<? extends DocumentEntity> delete(Iterable<Object> values, Class<?> entityClass,
			DocumentDeleteOptions options) throws DataAccessException {
		return operations.delete(values, entityClass, options);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#delete(java.lang.Iterable,
	 * java.lang.Class)
	 */
	public MultiDocumentEntity<? extends DocumentEntity> delete(Iterable<Object> values, Class<?> entityClass)
			throws DataAccessException {
		return operations.delete(values, entityClass);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#delete(java.lang.String,
	 * java.lang.Class, com.arangodb.model.DocumentDeleteOptions)
	 */
	public DocumentEntity delete(String id, Class<?> entityClass, DocumentDeleteOptions options)
			throws DataAccessException {
		return operations.delete(id, entityClass, options);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#delete(java.lang.String,
	 * java.lang.Class)
	 */

	public DocumentEntity delete(String id, Class<?> entityClass) throws DataAccessException {
		return operations.delete(id, entityClass);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#update(java.lang.Iterable,
	 * java.lang.Class, com.arangodb.model.DocumentUpdateOptions)
	 */

	public <T> MultiDocumentEntity<? extends DocumentEntity> update(Iterable<T> values, Class<T> entityClass,
			DocumentUpdateOptions options) throws DataAccessException {
		return operations.update(values, entityClass, options);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#update(java.lang.Iterable,
	 * java.lang.Class)
	 */

	public <T> MultiDocumentEntity<? extends DocumentEntity> update(Iterable<T> values, Class<T> entityClass)
			throws DataAccessException {
		return operations.update(values, entityClass);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#update(java.lang.String,
	 * java.lang.Object, com.arangodb.model.DocumentUpdateOptions)
	 */

	public <T> DocumentEntity update(String id, T value, DocumentUpdateOptions options) throws DataAccessException {
		return operations.update(id, value, options);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#update(java.lang.String,
	 * java.lang.Object)
	 */

	public <T> DocumentEntity update(String id, T value) throws DataAccessException {
		return operations.update(id, value);
	}

	/**
	 *
	 * 
	 * @see com.arangodb.springframework.core.ArangoOperations#replace(java.lang.
	 * Iterable, java.lang.Class, com.arangodb.model.DocumentReplaceOptions)
	 */

	public <T> MultiDocumentEntity<? extends DocumentEntity> replace(Iterable<T> values, Class<T> entityClass,
			DocumentReplaceOptions options) throws DataAccessException {
		return operations.replace(values, entityClass, options);
	}

	/**
	 *
	 * 
	 * @see com.arangodb.springframework.core.ArangoOperations#replace(java.lang.
	 * Iterable, java.lang.Class)
	 */

	public <T> MultiDocumentEntity<? extends DocumentEntity> replace(Iterable<T> values, Class<T> entityClass)
			throws DataAccessException {
		return operations.replace(values, entityClass);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#replace(java.lang.String,
	 * java.lang.Object, com.arangodb.model.DocumentReplaceOptions)
	 */

	public <T> DocumentEntity replace(String id, T value, DocumentReplaceOptions options) throws DataAccessException {
		return operations.replace(id, value, options);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#replace(java.lang.String,
	 * java.lang.Object)
	 */

	public <T> DocumentEntity replace(String id, T value) throws DataAccessException {
		return operations.replace(id, value);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#find(java.lang.String,
	 * java.lang.Class, com.arangodb.model.DocumentReadOptions)
	 */

	public <T> Optional<T> find(String id, Class<T> entityClass, DocumentReadOptions options)
			throws DataAccessException {
		return operations.find(id, entityClass, options);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#find(java.lang.String,
	 * java.lang.Class)
	 */

	public <T> Optional<T> find(String id, Class<T> entityClass) throws DataAccessException {
		return operations.find(id, entityClass);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#findAll(java.lang.Class)
	 */

	public <T> Iterable<T> findAll(Class<T> entityClass) throws DataAccessException {
		return operations.findAll(entityClass);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#find(java.lang.Iterable,
	 * java.lang.Class)
	 */

	public <T> Iterable<T> find(Iterable<String> ids, Class<T> entityClass) throws DataAccessException {
		return operations.find(ids, entityClass);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#insert(java.lang.Iterable,
	 * java.lang.Class, com.arangodb.model.DocumentCreateOptions)
	 */

	public <T> MultiDocumentEntity<? extends DocumentEntity> insert(Iterable<T> values, Class<T> entityClass,
			DocumentCreateOptions options) throws DataAccessException {
		return operations.insert(values, entityClass, options);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#insert(java.lang.Iterable,
	 * java.lang.Class)
	 */

	public <T> MultiDocumentEntity<? extends DocumentEntity> insert(Iterable<T> values, Class<T> entityClass)
			throws DataAccessException {
		return operations.insert(values, entityClass);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#insert(java.lang.Object,
	 * com.arangodb.model.DocumentCreateOptions)
	 */

	public <T> DocumentEntity insert(T value, DocumentCreateOptions options) throws DataAccessException {
		return operations.insert(value, options);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#insert(java.lang.Object)
	 */

	public <T> DocumentEntity insert(T value) throws DataAccessException {
		return operations.insert(value);
	}

	public <T> T findPath(Class<?> vertexClass, String vertexKey, Class<?> edgeClass, Class<T> returnTypeClass, int depth) {

		final ArangoPersistentEntity<?> vertex = context.getPersistentEntity(vertexClass);
		final String vertexCollection = vertex.getCollection();
		final ArangoPersistentEntity<?> edge = context.getPersistentEntity(edgeClass);
		final String edgeCollection = edge.getCollection();

		Map<String, Object> bindVars = new MapBuilder()
				.put("Id", new StringBuilder(vertexCollection).append("/").append(vertexKey).toString())
				.put("@edgeCollection", edgeCollection)
				.put("depth", depth)
				.get();

		final ArangoCursor<VPackSlice> cursor = operations.query(
				"FOR v, e, p IN 1..@depth ANY @Id @@edgeCollection RETURN [p]", bindVars, null, VPackSlice.class);

		T path = null;
		if (cursor.hasNext()) {
			VPackSlice obj = cursor.next();
			if (obj.isArray()) {
				final VPackSlice value = obj.get(0);
				String json = operations.driver().util().deserialize(value, String.class);
				try {
					path = objectMapper.readValue(json, returnTypeClass);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return path;
	}
	
	
	public <T extends Thing> Thing findThingByKey(T value) throws DataAccessException {
		final ArangoPersistentEntity<?> persistentEntity = context.getPersistentEntity(value.getClass());
		String collection = persistentEntity.getCollection();
		String keyFieldName = persistentEntity.getKeyProperty().get().getFieldName();
		String query = new StringBuilder("FOR t IN ").append(collection).append(" FILTER t.").append(keyFieldName).append(" == @key RETURN t").toString();

		Map<String, Object> bindVars = new MapBuilder().put("key", value.getKey()).get();

		ArangoCursor<Thing> cursor = operations.query(query, bindVars, null, Thing.class);
		if (cursor.hasNext()) {
			return cursor.next();
		} else {
			return null;
		}

	}

	public <T> void upsert(Relation value, UpsertStrategy strategy) throws DataAccessException {
		log.info("Upserting relation: " + value.toString());
		
		boolean fromFound = false;
		boolean toFound = false;
		// If relation from document doesn't have any id, try to find it in the database, otherwise create it
		if (StringUtils.isEmpty(value.getFrom().getId())) {
			log.info("Relation " + value.toString() + " From attribute doesn't have an ID.");
			Thing fromThing = findThingByKey(value.getFrom());
			if (fromThing == null) {
				log.info("Relation " + value.toString() + " From attribute NOT found in database. Upserting it.");
				upsert(value.getFrom(), strategy);
			} else {
				log.info("Relation " + value.toString() + " From attribute found in database. Using its ID.");
				fromFound = true;
				value.getFrom().setId(fromThing.getId());
				value.getFrom().setRevision(fromThing.getRevision());
			}
		}

		if (StringUtils.isEmpty(value.getTo().getId())) {
			log.info("Relation " + value.toString() + " To attribute doesn't have an ID.");
			Thing toThing = findThingByKey(value.getTo());
			if (toThing == null) {
				log.info("Relation " + value.toString() + " To attribute NOT found in database. Upserting it.");
				upsert(value.getTo(), strategy);
			} else {
				log.info("Relation " + value.toString() + " To attribute found in database. Using its ID.");
				toFound = true;
				value.getTo().setId(toThing.getId());
				value.getTo().setRevision(toThing.getRevision());
			}
		}

		try {
			log.info("Relation: " + value.toString() + " is trying to be upserted");
			if (fromFound && toFound) {
				throw new Exception("Suspicion of existence");
			} else {
				operations.upsert(value, strategy);
			}
			log.info("Relation: " + value.toString() + " SUCCESSFULLY upserted");
		} catch (Exception e) {
			log.info("Relation upsertion encountered an error: " + e.getMessage() + ", try to recover from the database...");
			final ArangoPersistentEntity<?> persistentEntity = context.getPersistentEntity(value.getClass());
			final String collection = persistentEntity.getCollection();
			final String fromFieldName = persistentEntity.getPersistentProperty(From.class).getFieldName();
			final String toFieldName = persistentEntity.getPersistentProperty(To.class).getFieldName();

			final String query = new StringBuilder("FOR t IN ")
					.append(collection)
					.append(" FILTER t.")
					.append(fromFieldName)
					.append(" == @from AND t.")
					.append(toFieldName)
					.append(" == @to AND t.type == @type RETURN t")
					.toString();

			final Map<String, Object> bindVars = new MapBuilder()
					.put("from", value.getFrom().getId())
					.put("to", value.getTo().getId())
					.put("type", value.getType())
					.get();

			final ArangoCursor<Thing> cursor = operations.query(query, bindVars, null, Thing.class);
			if (cursor.hasNext()) {
				log.info("Relation " + value.toString() + " recovered from the database, upserting it again");
				Thing t = cursor.next();
				value.setId(t.getId());
				value.setKey(t.getKey());
				value.setRevision(t.getRevision());
				operations.upsert(value, strategy);
				log.info("Relation " + value.toString() + " recovered and SUCCESSFULLY upserted");
			} else {
				log.info("Relation " + value.toString() + " wasn't found in the database, trying to insert it");
				operations.insert(value);
				log.info("Relation " + value.toString() + " wasn't found in the database, SUCCESSFULLY inserted");
			}

		}
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#upsert(java.lang.Object,
	 * com.arangodb.springframework.core.ArangoOperations.UpsertStrategy)
	 */
	public <T extends Thing> void upsert(T value, UpsertStrategy strategy) throws DataAccessException {
		log.info("Upserting " + value.toString() + " ...");
		try {
			operations.upsert(value, strategy);
			log.info("Thing " + value.toString() + " SUCCESSFULLY upserted");
		} catch (Exception e) {
			log.info("Thing " + value.toString() + " upsertion encountered an error: " + e.getMessage() + ", try to recover from the database...");
			Thing thing = (Thing) value;

			final ArangoPersistentEntity<?> persistentEntity = context.getPersistentEntity(value.getClass());
			String collection = persistentEntity.getCollection();

			boolean isEdge = (persistentEntity.findAnnotation(Edge.class) != null);
			String query = null;
			Map<String, Object> bindVars = null;
			if (isEdge) {
				log.info("Thing " + thing.toString() + " is an Edge");
				/**
				 * Several cases here: - From and To have ids: just try to upsert the relation -
				 * From or To doesn't have any id: try to find if it already exists with the
				 * key: - It exists: get the Id, and upsert the relation - It doesn't exist:
				 * First, upsert the Thing, then upsert the relation
				 */

				final String fromFieldName = persistentEntity.getPersistentProperty(From.class).getFieldName();
				final String toFieldName = persistentEntity.getPersistentProperty(To.class).getFieldName();

				query = new StringBuilder("FOR t IN ")
						.append(collection)
						.append(" FILTER t.")
						.append(fromFieldName)
						.append(" == @from AND t.")
						.append(toFieldName)
						.append(" == @to AND t.type == @type RETURN t")
						.toString();

				final Relation rel = (Relation) thing;

				if (!StringUtils.isEmpty(rel.getFrom().getId()) && !StringUtils.isEmpty(rel.getTo().getId())) {
					bindVars = new MapBuilder().put("from", rel.getFrom().getId()).put("to", rel.getTo().getId())
							.put("type", rel.getType()).get();
				}
			} else {
				log.info("Thing " + thing.toString() + " is an Document");
				String key = persistentEntity.getKeyProperty().get().getFieldName();
				query = "FOR t IN " + collection + " FILTER t." + key + " == @key RETURN t";
				bindVars = new MapBuilder().put("key", thing.getKey()).get();
			}

			if (bindVars == null) {
				log.info("bindVars for Thing " + thing.toString() + " was null");
				return;
			}

			ArangoCursor<Thing> cursor = operations.query(query, bindVars, null, Thing.class);
			if (cursor.hasNext()) {
				log.info("Thing " + thing.toString() + " recovered from the database, trying to upsert");
				Thing t = cursor.next();
				thing.setId(t.getId());
				thing.setKey(t.getKey());
				thing.setRevision(t.getRevision());
				operations.upsert(value, strategy);
				log.info("Thing " + thing.toString() + " recovered from the database and SUCCESSFULLY upserted");
			} else {
				log.info("Thing " + thing.toString() + " NOT recovered from the database. Trying to insert");
				operations.insert(value);
				log.info("Thing " + thing.toString() + " NOT recovered from the database. SUCCESSFULLY inserted");
			}
		}
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#upsert(java.lang.Iterable,
	 * com.arangodb.springframework.core.ArangoOperations.UpsertStrategy)
	 */

	public <T> void upsert(Iterable<T> value, UpsertStrategy strategy) throws DataAccessException {
		operations.upsert(value, strategy);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#exists(java.lang.String,
	 * java.lang.Class)
	 */

	public boolean exists(String id, Class<?> entityClass) throws DataAccessException {
		return operations.exists(id, entityClass);
	}

	/**
	 *
	 * 
	 * @see com.arangodb.springframework.core.ArangoOperations#dropDatabase()
	 */

	public void dropDatabase() throws DataAccessException {
		operations.dropDatabase();
	}

	/**
	 *
	 * 
	 * @see com.arangodb.springframework.core.ArangoOperations#collection(java.lang.
	 * Class)
	 */

	public CollectionOperations collection(Class<?> entityClass) throws DataAccessException {
		return operations.collection(entityClass);
	}

	/**
	 *
	 * 
	 * @see com.arangodb.springframework.core.ArangoOperations#collection(java.lang.
	 * String)
	 */

	public CollectionOperations collection(String name) throws DataAccessException {
		return operations.collection(name);
	}

	/**
	 *
	 * 
	 * @see com.arangodb.springframework.core.ArangoOperations#collection(java.lang.
	 * String, com.arangodb.model.CollectionCreateOptions)
	 */

	public CollectionOperations collection(String name, CollectionCreateOptions options) throws DataAccessException {
		return operations.collection(name, options);
	}

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#user(java.lang.String)
	 */

	public UserOperations user(String username) {
		return operations.user(username);
	}

	/**
	 *
	 * 
	 * @see com.arangodb.springframework.core.ArangoOperations#getUsers()
	 */

	public Iterable<UserEntity> getUsers() throws DataAccessException {
		return operations.getUsers();
	}

	/**
	 *
	 * 
	 * @see com.arangodb.springframework.core.ArangoOperations#getConverter()
	 */

	public ArangoConverter getConverter() {
		return operations.getConverter();
	}

}
