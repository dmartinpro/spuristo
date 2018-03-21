package org.spuristo.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.spuristo.core.repository.SpuristoArangoOperations;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDB.Builder;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;

/**
 * 
 */
@Configuration
@EnableArangoRepositories(basePackages = { "org.spuristo.core" })
@ComponentScan(basePackages="org.spuristo.core.api")
public class ArangoConfiguration extends AbstractArangoConfiguration {

	@Value("${arangodb.host}")
	private String host;
	@Value("${arangodb.port:8529}")
	private int port;
	@Value("${arangodb.user}")
	private String user;
	@Value("${arangodb.password}")
	private String password;

	@Override
	public Builder arango() {
		return new ArangoDB.Builder().host(host, port).user(user).password(password);
	}

	@Bean
	public SpuristoArangoOperations ops() {
		return new SpuristoArangoOperations();
	}

	@Override
	public String database() {
		return "spuristo";
	}

}
