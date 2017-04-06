package org.ga4gh.registry.json;

import org.apache.log4j.Logger;
import org.ga4gh.registry.bean.ServerNodeBean;
import org.ga4gh.registry.util.RegistryConstants;
import org.ga4gh.registry.util.RegistryException;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

/**
 * Builder class to build json objects
 *
 * Created by mduby on 4/5/17.
 */
@Component
public class RegistryJsonBuilder {
    // instance variables
    private final Logger builderLog = Logger.getLogger(this.getClass().getName());

    /**
     * builds a json object of the server node
     *
     * @param serverNodeBean
     * @return
     * @throws RegistryException
     */
    public JsonObject buildServerNodeJson(ServerNodeBean serverNodeBean) throws RegistryException {
        // local variables
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonObject jsonObject = null;

        // add in the url and type
        objectBuilder.add(RegistryConstants.JsonKeys.TYPE, serverNodeBean.getType());
        objectBuilder.add(RegistryConstants.JsonKeys.URL, serverNodeBean.getUrl());

        // build the object
        jsonObject = objectBuilder.build();

        // return
        return jsonObject;
    }

    /**
     * build a list of peer server object
     *
     * @param serverNodeBeanList
     * @return
     * @throws RegistryException
     */
    public JsonObject buildListOfPeers(List<ServerNodeBean> serverNodeBeanList) throws RegistryException {
        // local variables
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonArray jsonArray = null;
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonObject jsonObject = null;

        // build the array
        for (ServerNodeBean bean : serverNodeBeanList) {
            arrayBuilder.add(this.buildServerNodeJson(bean));
        }
        jsonArray = arrayBuilder.build();

        // add the array to the object
        objectBuilder.add(RegistryConstants.JsonKeys.PEERS, jsonArray);
        jsonObject = objectBuilder.build();

        // return
        return jsonObject;
    }
}
