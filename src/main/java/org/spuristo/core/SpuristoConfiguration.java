package org.spuristo.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author dmartin
 *
 */
@Configuration
@ComponentScan(basePackages= {"org.spuristo.core.api", "org.spuristo.core.configuration", "org.spuristo.core.runner"})
public class SpuristoConfiguration {

}
