/**
 * 
 */
package org.spuristo.core.repository;

import org.spuristo.core.model.Entity;

import com.arangodb.springframework.annotation.Param;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

/**
 * @author dmartin
 *
 */
public interface EntityRepository extends ArangoRepository<Entity> {

	public Entity findByKey(String key);

	@Query("FOR v, e, p IN 1..5 ANY @id @@edgeCol RETURN [p]")
	Object[] getPath(@Param("id") String id, @Param("@edgeCol") Class<?> edgeCollection);

	
}
