/**
 * 
 */
package org.spuristo.core.runner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.spuristo.core.service.AdminSpuristoService;

/**
 * Drop all data in the database
 * 
 * In order to activate this runner, just add the command line parameter: --drop-database
 * 
 * @author dmartin
 *
 */
@Component
@Order(value=1)
public class DropDatabaseRunner implements ApplicationRunner {

	private Log log = LogFactory.getLog(DropDatabaseRunner.class);

	private static final String FLAG = "drop-database";
	
	@Autowired
	private AdminSpuristoService adminService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		boolean containsInitOption = args.containsOption(FLAG);
		if (containsInitOption) {
			log.info(String.format("%s flag detected, dropping data", FLAG));
			this.run();
		}
	}

	private void run() {
		adminService.dropDatabase();
	}
}
