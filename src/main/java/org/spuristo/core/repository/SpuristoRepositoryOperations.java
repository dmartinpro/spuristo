package org.spuristo.core.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.spuristo.core.model.Relation;
import org.spuristo.core.model.SpuristoPath;
import org.spuristo.core.model.Thing;

import com.arangodb.ArangoCursor;
import com.arangodb.entity.DocumentEntity;
import com.arangodb.entity.MultiDocumentEntity;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.springframework.core.ArangoOperations.UpsertStrategy;

public interface SpuristoRepositoryOperations {

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#query(java.lang.String,
	 * java.util.Map, com.arangodb.model.AqlQueryOptions, java.lang.Class)
	 */
	<T> ArangoCursor<T> query(String query, Map<String, Object> bindVars, AqlQueryOptions options, Class<T> entityClass)
			throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#delete(java.lang.Iterable,
	 * java.lang.Class)
	 */
	MultiDocumentEntity<? extends DocumentEntity> delete(Iterable<Object> values, Class<?> entityClass)
			throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#delete(java.lang.String,
	 * java.lang.Class)
	 */

	DocumentEntity delete(String id, Class<?> entityClass) throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#update(java.lang.Iterable,
	 * java.lang.Class)
	 */

	<T> MultiDocumentEntity<? extends DocumentEntity> update(Iterable<T> values, Class<T> entityClass)
			throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#update(java.lang.String,
	 * java.lang.Object)
	 */

	<T> DocumentEntity update(String id, T value) throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#find(java.lang.String,
	 * java.lang.Class)
	 */

	<T> Optional<T> find(String id, Class<T> entityClass) throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#find(java.lang.Iterable,
	 * java.lang.Class)
	 */

	<T> Iterable<T> find(Iterable<String> ids, Class<T> entityClass) throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#insert(java.lang.Iterable,
	 * java.lang.Class)
	 */

	<T> MultiDocumentEntity<? extends DocumentEntity> insert(Iterable<T> values, Class<T> entityClass)
			throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#insert(java.lang.Object)
	 */

	<T> DocumentEntity insert(T value) throws DataAccessException;

	SpuristoPath getGraph(Class<?> vertexClass, String vertexKey, Class<?> edgeClass, int depth);

	<T> void upsert(Relation value, UpsertStrategy strategy) throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#upsert(java.lang.Object,
	 * com.arangodb.springframework.core.ArangoOperations.UpsertStrategy)
	 */
	<T extends Thing> void upsert(T value, UpsertStrategy strategy) throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#upsert(java.lang.Iterable,
	 * com.arangodb.springframework.core.ArangoOperations.UpsertStrategy)
	 */

	<T> void upsert(Iterable<T> value, UpsertStrategy strategy) throws DataAccessException;

	/**
	 *
	 * 
	 * @see
	 * com.arangodb.springframework.core.ArangoOperations#exists(java.lang.String,
	 * java.lang.Class)
	 */

	boolean exists(String id, Class<?> entityClass) throws DataAccessException;

	/**
	 *
	 * 
	 * @see com.arangodb.springframework.core.ArangoOperations#dropDatabase()
	 */

	void dropDatabase() throws DataAccessException;

}