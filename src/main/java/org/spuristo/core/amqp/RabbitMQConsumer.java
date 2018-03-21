/**
 * 
 */
package org.spuristo.core.amqp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.spuristo.core.model.Activity;
import org.spuristo.core.model.Agent;
import org.spuristo.core.model.Entity;
import org.spuristo.core.model.Relation;
import org.spuristo.core.service.SpuristoService;

/**
 * @author dmartin
 *
 */
@Service
public class RabbitMQConsumer {

	private static final Log log = LogFactory.getLog(RabbitMQConsumer.class);

	@Autowired
	private SpuristoService service;

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(name = "#{@entityQueueName}", durable="true", autoDelete="false"),
			exchange = @Exchange(value = "#{@entityExchangeName}", durable="true", autoDelete="true"),
			key="#{@entityQueueName}",
			ignoreDeclarationExceptions="true"
		))
	public void handleEntity(Entity entity) {
		log.info("Received <" + entity.toString() + ">");
		service.addEntity(entity);
	}

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(name = "#{@activityQueueName}", durable="true", autoDelete="false"),
			exchange = @Exchange(value = "#{@activityExchangeName}", durable="true"),
			key="#{@activityQueueName}",
			ignoreDeclarationExceptions="true"
		))
	public void handleActivity(Activity activity) {
		log.info("Received <" + activity.toString() + ">");
		service.addActivity(activity);
	}

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(name = "#{@agentQueueName}", durable="true", autoDelete="false"),
			exchange = @Exchange(value = "#{@agentExchangeName}", durable="true"),
			key="#{@agentQueueName}",
			ignoreDeclarationExceptions="true"
		))
	public void handleAgent(Agent agent) {
		log.info("Received <" + agent.toString() + ">");
		service.addAgent(agent);
	}

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(name = "#{@relationQueueName}", durable="true", autoDelete="false"),
			exchange = @Exchange(value = "#{@relationExchangeName}", durable="true"),
			key="#{@relationQueueName}",
			ignoreDeclarationExceptions="true"
		))
	public void handleRelation(Relation relation) {
		log.info("Received <" + relation.toString() + ">");
		service.addRelation(relation);
	}

}
