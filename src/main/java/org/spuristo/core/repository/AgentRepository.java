/**
 * 
 */
package org.spuristo.core.repository;

import org.spuristo.core.model.Agent;

import com.arangodb.springframework.repository.ArangoRepository;

/**
 * @author dmartin
 *
 */
public interface AgentRepository extends ArangoRepository<Agent> {

	public Agent findByKey(String key);

}
