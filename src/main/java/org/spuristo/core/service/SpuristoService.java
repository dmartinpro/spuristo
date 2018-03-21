package org.spuristo.core.service;

import org.spuristo.core.model.Activity;
import org.spuristo.core.model.Agent;
import org.spuristo.core.model.Entity;
import org.spuristo.core.model.Relation;
import org.spuristo.core.model.SpuristoPath;

public interface SpuristoService {

	public SpuristoPath getPathForEntity(String key);

	public Entity addEntity(Entity entity);

	public Activity addActivity(Activity activity);

	public Agent addAgent(Agent agent);

	public Relation addRelation(Relation relation);

	public Activity findActivityByKey(String key);

	public Entity findEntityByKey(String key);

	public Agent findAgentByKey(String key);

}
