/**
 * 
 */
package org.spuristo.core.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dmartin
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableRabbit
public class RabbitMQConfiguration {

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

	@Bean
	Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public String activityQueueName() {
		return activityQueueName;
	}

	@Bean
	public String activityExchangeName() {
		return activityExchangeName;
	}

	@Bean
	public String entityQueueName() {
		return entityQueueName;
	}

	@Bean
	public String entityExchangeName() {
		return entityExchangeName;
	}

	@Bean
	public String agentQueueName() {
		return agentQueueName;
	}

	@Bean
	public String agentExchangeName() {
		return agentExchangeName;
	}

	@Bean
	public String relationQueueName() {
		return relationQueueName;
	}

	@Bean
	public String relationExchangeName() {
		return relationExchangeName;
	}

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

}
