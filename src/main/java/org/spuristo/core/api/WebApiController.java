/**
 * 
 */
package org.spuristo.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.spuristo.core.model.Activity;
import org.spuristo.core.model.Agent;
import org.spuristo.core.model.Entity;
import org.spuristo.core.model.SpuristoPath;
import org.spuristo.core.service.SpuristoService;

/**
 * API entry points to search, get entities from the data store
 * 
 * @author dmartin
 *
 */
@RestController
public class WebApiController {

	@Autowired
	private SpuristoService service;

    @RequestMapping(path = "/activity")
    public Activity activity(@RequestParam(value="key") String key) {
    		if (StringUtils.isEmpty(key)) {
    			throw new BadRequestException();
    		}

    		final Activity activity = service.findActivityByKey(key);
    		if (activity == null) {
    			throw new ResourceNotFoundException(key);
    		}

    		return activity;
    }

    @RequestMapping(path = "/activity", method = RequestMethod.POST)
    public Activity postActivity(@RequestBody Activity activity) {
    		return service.addActivity(activity);
    }


    @RequestMapping(path = "/entity")
    public Entity entity(@RequestParam(value="key") String key) {
    		if (StringUtils.isEmpty(key)) {
    			throw new BadRequestException();
    		}

    		final Entity entity = service.findEntityByKey(key);
    		if (entity == null) {
    			throw new ResourceNotFoundException(key);
    		}

    		return entity;
    }

    @RequestMapping(path = "/entity", method = RequestMethod.POST)
    public Entity postEntity(@RequestBody Entity entity) {
    		return service.addEntity(entity);
    }

    @RequestMapping(path = "/agent")
    public Agent agent(@RequestParam(value="key") String key) {
    		if (StringUtils.isEmpty(key)) {
    			throw new BadRequestException();
    		}

    		final Agent agent = service.findAgentByKey(key);
    		if (agent == null) {
    			throw new ResourceNotFoundException(key);
    		}

    		return agent;
    }

    @RequestMapping(path = "/agent", method = RequestMethod.POST)
    public Agent postAgent(@RequestBody Agent agent) {
    		return service.addAgent(agent);
    }

    @RequestMapping(path = "/path", method = RequestMethod.GET)
    public SpuristoPath getPath(@RequestParam(value="key") String key) {
    		return service.getPathForEntity(key);
    }

}
