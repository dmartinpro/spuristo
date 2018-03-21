package org.spuristo.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 */
@Configuration
@ComponentScan(basePackages= {"org.spuristo.core.api", "org.spuristo.core.configuration"})
public class SpuristoConfiguration {

}
