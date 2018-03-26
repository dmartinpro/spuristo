/**
 * 
 */
package org.spuristo.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arangodb.springframework.core.ArangoOperations;

/**
 * @author dmartin
 *
 */
@Service
public class AdminSpuristoServiceImpl implements AdminSpuristoService {

	@Autowired
	private ArangoOperations operations;
	
	/**
	 * @see org.spuristo.core.service.AdminSpuristoService#dropDatabase()
	 */
	@Override
	public boolean dropDatabase() {
		operations.dropDatabase();
		return true;
	}

}
