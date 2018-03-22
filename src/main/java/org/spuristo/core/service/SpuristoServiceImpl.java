/**
 * 
 */
package org.spuristo.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.spuristo.core.model.Activity;
import org.spuristo.core.model.Agent;
import org.spuristo.core.model.Entity;
import org.spuristo.core.model.Relation;
import org.spuristo.core.model.SpuristoPath;
import org.spuristo.core.repository.ActivityRepository;
import org.spuristo.core.repository.AgentRepository;
import org.spuristo.core.repository.EntityRepository;
import org.spuristo.core.repository.SpuristoArangoOperations;

import com.arangodb.springframework.core.ArangoOperations.UpsertStrategy;

/**
 * @author dmartin
 *
 */
@Service
public class SpuristoServiceImpl implements SpuristoService {

	@Autowired
	private SpuristoArangoOperations operations;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private EntityRepository entityRepository;

	@Autowired
	private AgentRepository agentRepository;

	public SpuristoPath getPathForEntity(String key) {
		SpuristoPath path = operations.getGraph(Entity.class, key, Relation.class, 2);
		return path;
	}

	@Override
	public Activity findActivityByKey(String key) {
		Assert.notNull(key, "Key can't be null");

		return activityRepository.findByKey(key);
	}

	@Override
	public Entity findEntityByKey(final String key) {
		Assert.notNull(key, "Key can't be null");

		return entityRepository.findByKey(key);
	}

	@Override
	public Agent findAgentByKey(String key) {
		Assert.notNull(key, "Key can't be null");

		return agentRepository.findByKey(key);
	}

	@Override
	public Entity addEntity(final Entity entity) {
		Assert.notNull(entity, "Entity can't be null");
		Assert.notNull(entity.getKey(), "Entity MUST have a non null key");

		operations.upsert(entity, UpsertStrategy.UPDATE);
		return entity;
	}

	@Override
	public Activity addActivity(final Activity activity) {
		Assert.notNull(activity, "Activity can't be null");
		Assert.notNull(activity.getKey(), "Activity MUST have a non null key");

		operations.upsert(activity, UpsertStrategy.UPDATE);
		return activity;
	}

	@Override
	public Agent addAgent(final Agent agent) {
		Assert.notNull(agent, "Agent can't be null");
		Assert.notNull(agent.getKey(), "Agent MUST have a non null key");

		operations.upsert(agent, UpsertStrategy.UPDATE);
		return agent;
	}

	@Override
	public Relation addRelation(final Relation relation) {
		Assert.notNull(relation, "Relation can't be null");
		Assert.notNull(relation.getFrom(), "Relation MUST have a non null From attribute");
		Assert.notNull(relation.getTo(), "Relation MUST have a non null To attribute");

		operations.upsert(relation, UpsertStrategy.UPDATE);
		return relation;
	}

}
