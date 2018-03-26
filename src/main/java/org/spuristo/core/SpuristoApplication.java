package org.spuristo.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author dmartin
 *
 */
@SpringBootApplication
public class SpuristoApplication {

	private static final Log log = LogFactory.getLog(SpuristoApplication.class);

	public static void main(final String... args) {
		log.info("\n\nSpuristo starting...\n"
				+ "Optional parameters are:\n"
				+ " --init-data        Load some sample data\n"
				+ " --drop-database    Drop all collections in the database\n");

		SpringApplication.run(SpuristoApplication.class, args);
	}

}