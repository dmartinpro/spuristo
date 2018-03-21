/**
 * 
 */
package org.spuristo.core.repository;

import org.spuristo.core.model.Activity;

import com.arangodb.springframework.repository.ArangoRepository;

/**
 * @author dmartin
 *
 */
public interface ActivityRepository extends ArangoRepository<Activity> {

	public Activity findByKey(String key);

}
