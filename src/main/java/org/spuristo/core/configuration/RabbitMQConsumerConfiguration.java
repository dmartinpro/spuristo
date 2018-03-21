/**
 * 
 */
package org.spuristo.core.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author dmartin
 *
 */
@Configuration
@ComponentScan(basePackages= {"org.spuristo.core.amqp"})
public class RabbitMQConsumerConfiguration {

}
