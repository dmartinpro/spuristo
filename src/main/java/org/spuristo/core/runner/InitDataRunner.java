/**
 * 
 */
package org.spuristo.core.runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.spuristo.core.common.AttributeBuilder;
import org.spuristo.core.model.Activity;
import org.spuristo.core.model.Agent;
import org.spuristo.core.model.Attribute;
import org.spuristo.core.model.Entity;
import org.spuristo.core.model.Relation;

/**
 * Load a set of sample data, through RabbitMQ.
 * 
 * In order to activate this runner, just add the command line parameter: --init-data
 * 
 * @author dmartin
 *
 */
@Component
@Order(value=10)
public class InitDataRunner implements ApplicationRunner {

	private Log log = LogFactory.getLog(InitDataRunner.class);
	private static final String FLAG = "init-data";

	@Value("${spring.rabbitmq.exchanges.activity}")
	String activityExchangeName;
	@Value("${spring.rabbitmq.exchanges.entity}")
	String entityExchangeName;
	@Value("${spring.rabbitmq.exchanges.agent}")
	String agentExchangeName;
	@Value("${spring.rabbitmq.exchanges.relation}")
	String relationExchangeName;

	@Value("${spring.rabbitmq.queues.activity}")
	String activityQueueName;
	@Value("${spring.rabbitmq.queues.entity}")
	String entityQueueName;
	@Value("${spring.rabbitmq.queues.agent}")
	String agentQueueName;
	@Value("${spring.rabbitmq.queues.relation}")
	String relationQueueName;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		boolean containsInitOption = args.containsOption(FLAG);
		if (containsInitOption) {
			log.info(String.format("%s flag detected, loading sample data", FLAG));
			this.run();
		}
	}

	private void run() {
		Map<String, String> map = new HashMap<>();
		map.put("abc", "ABC");
		map.put("def", "DEF");
		map.put("ghi", "GHI");
		map.put("klm", "KLM");

		Agent user1 = new Agent("Jean", Agent.TYPE.PERSON);
		user1.addAttributes(
				AttributeBuilder.init()
						.addType(Agent.AGENT_ATTRIBUTE.FIRSTNAME).andObjectAsString("Jean")
						.addType(Agent.AGENT_ATTRIBUTE.LASTNAME).andObjectAsString("DUPONT")
						.build());

		Agent sparkJob = new Agent("sparkJob1", Agent.TYPE.SOFTWARE);
		sparkJob.addAttributes(
				AttributeBuilder.init()
						.addType(Attribute.GENERIC_ATTRIBUTE.LABEL).andObjectAsString("sfsdfd")
						.addType(Attribute.GENERIC_ATTRIBUTE.OTHER).andObjectAsString("attribute2")
						.build());

		Relation relAgents = new Relation(sparkJob, "executedBy", user1);
		rabbitTemplate.convertAndSend(relationExchangeName, relationQueueName, relAgents);

		
		List<Entity> entities = new ArrayList<>();

		Entity source = new Entity("source_dataset");
		rabbitTemplate.convertAndSend(entityExchangeName, entityQueueName, source);

		for (int i = 0; i < 200; i++) {
			Entity ent = new Entity("entity-a" + i);
			ent.setAttributes(AttributeBuilder.init().addType(Attribute.GENERIC_ATTRIBUTE.ROLE).andMapValue(map)
					.addType(Attribute.GENERIC_ATTRIBUTE.LABEL).andObjectAsString("attribute1-" + i)
					.addType(Attribute.GENERIC_ATTRIBUTE.LOCATION).andObjectAsString("attribute2-" + i)
					.addType(Attribute.GENERIC_ATTRIBUTE.VALUE).andObjectAsString("attribute3-" + i).build());
			entities.add(ent);
			rabbitTemplate.convertAndSend(entityExchangeName, entityQueueName, ent);
			Relation sourceRel = new Relation(source, Relation.TYPE.COMPOSED_OF, ent);
			rabbitTemplate.convertAndSend(relationExchangeName, relationQueueName, sourceRel);
		}

		Activity activity = new Activity("extract_training_and_test_dataset__1", "extract_training_and_test_dataset",
				LocalDateTime.now().minusMinutes(4).toDate().getTime(), LocalDateTime.now().minusMinutes(2).toDate().getTime());

		Map<String, String> config = new HashMap<>();
		config.put("ratio", "0.8");
		config.put("somethingelse", "12345");
		activity.addAttributes(AttributeBuilder.init().addType(Attribute.GENERIC_ATTRIBUTE.CONFIGURATION)
				.andMapValue(config).build());

		rabbitTemplate.convertAndSend(activityExchangeName, activityQueueName, activity);

		Entity trainDataSet = new Entity("train_dataset");
		rabbitTemplate.convertAndSend(entityExchangeName, entityQueueName, trainDataSet);
		Entity testDataSet = new Entity("train_dataset");
		rabbitTemplate.convertAndSend(entityExchangeName, entityQueueName, testDataSet);

		for (int i = 0; i < 200; i++) {
			Entity ent = entities.get(i);
			Relation rel = null;
			if (i < 200 * 0.8) {
				rel = new Relation(trainDataSet, Relation.TYPE.COMPOSED_OF, ent);
			} else {
				rel = new Relation(testDataSet, Relation.TYPE.COMPOSED_OF, ent);
			}
			rabbitTemplate.convertAndSend(relationExchangeName, relationQueueName, rel);
		}

		Relation rel1 = new Relation(trainDataSet, Relation.TYPE.DERIVED_FROM, source);
		rabbitTemplate.convertAndSend(relationExchangeName, relationQueueName, rel1);
		Relation rel2 = new Relation(testDataSet, Relation.TYPE.DERIVED_FROM, source);
		rabbitTemplate.convertAndSend(relationExchangeName, relationQueueName, rel2);
		Relation rel3 = new Relation(trainDataSet, Relation.TYPE.WAS_GENERATED_BY, activity);
		rabbitTemplate.convertAndSend(relationExchangeName, relationQueueName, rel3);
		Relation rel4 = new Relation(testDataSet, Relation.TYPE.WAS_GENERATED_BY, activity);
		rabbitTemplate.convertAndSend(relationExchangeName, relationQueueName, rel4);

		Relation rel5 = new Relation(activity, Relation.TYPE.USED, source);
		rabbitTemplate.convertAndSend(relationExchangeName, relationQueueName, rel5);

		Relation rel6 = new Relation(activity, Relation.TYPE.WAS_ASSOCIATED_WITH, sparkJob);
		rabbitTemplate.convertAndSend(relationExchangeName, relationQueueName, rel6);

		Activity trainModel = new Activity("train_logistic_regression__1", "train_logistic_regression",
				LocalDateTime.now().minusMinutes(2).toDate().getTime(), LocalDateTime.now().minusMinutes(1).toDate().getTime());
		rabbitTemplate.convertAndSend(activityExchangeName, activityQueueName, trainModel);

		Entity model = new Entity("model__1");
		rabbitTemplate.convertAndSend(entityExchangeName, entityQueueName, model);

		Relation relModel1 = new Relation(model, Relation.TYPE.WAS_GENERATED_BY, trainModel);
		rabbitTemplate.convertAndSend(relationExchangeName, relationQueueName, relModel1);
	
	}
}
