package org.ga4gh.registry.controller;

import org.apache.log4j.Logger;
import org.ga4gh.registry.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.json.JsonObject;

/**
 * Concrete controller class to handle RREST requests; this class will only serve for forwarding json to format checkers and services
 *
 * Created by mduby on 8/9/16.
 */
@RestController
public class RegistryController {
    // instance variables
    private final Logger controllerLog = Logger.getLogger(this.getClass().getName());

    @Autowired
    RegistryService registryService;

    /**
     * services the /peers GET REST call
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/peers", method = RequestMethod.GET, produces = "application/json")
    public String getListOfPeers(@RequestParam(value = "type", required = false) String type) {
        String resultString = null;
        JsonObject resultObject = null;

        // log
        this.controllerLog.info("Got list of peers with type: " + type);

        // get the result object
        resultObject = this.registryService.getListOfPeers(type);

        // return
        return resultObject.toString();
    }

    /**
     * services the /peers GET POST call
     *
     * @param inputString
     * @return
     */
    @RequestMapping(value = "/peers", method = RequestMethod.POST, produces = "application/json")
    public String addServerNode(@RequestBody String inputString) {
        JsonObject resultObject = null;

        // log
        this.controllerLog.info("Got /peers POST input server node with json: " + inputString);

        // get the result object
        resultObject = this.registryService.addServerNodeFromJsonString(inputString);

        // return
        return resultObject.toString();
    }

    /**
     * services the /peers GET POST call
     *
     * @param inputString
     * @return
     */
    @RequestMapping(value = "/peers", method = RequestMethod.PUT, produces = "application/json")
    public String updateServerNode(@RequestBody String inputString) {
        JsonObject resultObject = null;

        // log
        this.controllerLog.info("Got /peers PUT input server node with json: " + inputString);

        // get the result object
        resultObject = this.registryService.updateServerNodeFromJsonString(inputString);

        // return
        return resultObject.toString();
    }

}