package org.ga4gh.registry.service;

import org.apache.log4j.Logger;
import org.ga4gh.registry.bean.ServerNodeBean;
import org.ga4gh.registry.datastore.simple.SimpleRegistryDAO;
import org.ga4gh.registry.json.RegistryJsonBuilder;
import org.ga4gh.registry.json.RegistryJsonParser;
import org.ga4gh.registry.util.RegistryConstants;
import org.ga4gh.registry.util.RegistryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

/**
 * Service class to retrieve registry data based on input
 *
 * Created by mduby on 3/28/17.
 */
@Service
public class RegistryService {
    // instance variables
    private final Logger serviceLog = Logger.getLogger(this.getClass().getName());

    @Autowired
    SimpleRegistryDAO simpleRegistryDAO;

    @Autowired
    RegistryJsonParser registryJsonParser;

    /**
     * returns a list of peers, filtered by type if the type parameter is not null; will return all types otherwise
     *
     * @param type
     * @return
     */
    public JsonObject getListOfPeers(String type) {
        // local variables
        JsonObject resultObject = null;
        List<ServerNodeBean> serverNodeBeanList = null;

        // throw error for now
        try {
            // if null type
            if (type == null) {
                serverNodeBeanList = this.simpleRegistryDAO.getAllServerNodes();

            } else {
                serverNodeBeanList = this.simpleRegistryDAO.getAllServerNodesOfType(type);
            }

            // get the json list
            RegistryJsonBuilder jsonBuilder = new RegistryJsonBuilder();
            resultObject = jsonBuilder.buildListOfPeers(serverNodeBeanList);

        } catch (RegistryException exception) {
            resultObject = this.buildErrorObject(exception);
        }

        // return
        return resultObject;
    }

    /**
     * add a server node
     *
     * @param serverNodeBean
     * @return
     */
    public JsonObject addServerNode(ServerNodeBean serverNodeBean) {
        // local variables
        JsonObject resultObject = null;

        // call the DAO
        try {
            this.simpleRegistryDAO.addServerNode(serverNodeBean);

            // if success
            resultObject = this.buildSuccessObject("created server node with url: " + serverNodeBean.getUrl());

        } catch (RegistryException exception) {
            resultObject = this.buildErrorObject(exception);
        }

        // if works, return success
        return resultObject;
    }

    /**
     * add a server node from a json string
     *
     * @param jsonString
     * @return
     */
    public JsonObject addServerNodeFromJsonString(String jsonString) {
        // local variables
        JsonObject resultObject = null;
        ServerNodeBean serverNodeBean = null;

        // call the DAO
        try {
            // get the server node from the json
            serverNodeBean = this.registryJsonParser.parseJsonString(jsonString);

            // add the server node
            resultObject = this.addServerNode(serverNodeBean);

        } catch (RegistryException exception) {
            resultObject = this.buildErrorObject(exception);
        }

        // if works, return success
        return resultObject;
    }

    /**
     * update a server node from a json string
     *
     * @param jsonString
     * @return
     */
    public JsonObject updateServerNodeFromJsonString(String jsonString) {
        // local variables
        JsonObject resultObject = null;
        ServerNodeBean serverNodeBean = null;

        // call the DAO
        try {
            // get the server node from the json
            serverNodeBean = this.registryJsonParser.parseJsonString(jsonString);

            // update the server node
            this.simpleRegistryDAO.updateServerNode(serverNodeBean);

            // if success
            resultObject = this.buildSuccessObject("updated server node with url: " + serverNodeBean.getUrl());

        } catch (RegistryException exception) {
            resultObject = this.buildErrorObject(exception);
        }

        // if works, return success
        return resultObject;
    }

    /**
     * build a success message for updating requests
     *
     * @param message
     * @return
     */
    public JsonObject buildSuccessObject(String message) {
        // local variables
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject errorObject = null;

        // create the error
        builder.add(RegistryConstants.JsonKeys.IS_ERROR, false);
        builder.add(RegistryConstants.JsonKeys.MESSAGE, message);

        // return
        errorObject = builder.build();
        return errorObject;
    }


    /**
     * returns a json error object reflecting the exception thrown
     *
     * @param exception
     * @return
     */
    public JsonObject buildErrorObject(RegistryException exception) {
        // local variables
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject errorObject = null;

        // create the error
        builder.add(RegistryConstants.JsonKeys.IS_ERROR, true);
        builder.add(RegistryConstants.JsonKeys.ERROR_MESSAGE, exception.getMessage());

        // return
        errorObject = builder.build();
        return errorObject;
    }


}
