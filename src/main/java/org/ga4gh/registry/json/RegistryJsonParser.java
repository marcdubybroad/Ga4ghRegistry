package org.ga4gh.registry.json;

import org.apache.log4j.Logger;
import org.ga4gh.registry.bean.ServerNodeBean;
import org.ga4gh.registry.util.RegistryConstants;
import org.ga4gh.registry.util.RegistryException;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import java.io.StringReader;

/**
 * Class to parse input json
 *
 * Created by mduby on 4/5/17.
 */
@Component
public class RegistryJsonParser {
    // instance variables
    private final Logger parserLog = Logger.getLogger(this.getClass().getName());

    /**
     * build the server node bean from the json
     *
     * @param jsonString
     * @return
     * @throws RegistryException
     */
    public ServerNodeBean parseJsonString(String jsonString) throws RegistryException {
        // local variables
        ServerNodeBean serverNodeBean = new ServerNodeBean();
        JsonObject jsonObject = null;
        JsonReader jsonReader = null;

        // get the json object
        try {
            jsonReader = Json.createReader(new StringReader(jsonString));
            jsonObject = jsonReader.readObject();

        } catch (JsonParsingException exception) {
            String message = "Got error parsing server node input: " + exception.getMessage();
            this.parserLog.error(message);
            throw new RegistryException(message);
        }

        // get the type
        if (jsonObject.containsKey(RegistryConstants.JsonKeys.TYPE)) {
            serverNodeBean.setType(jsonObject.getString(RegistryConstants.JsonKeys.TYPE));
        }

        // get the url
        if (jsonObject.containsKey(RegistryConstants.JsonKeys.URL)) {
            serverNodeBean.setUrl(jsonObject.getString(RegistryConstants.JsonKeys.URL));
        }

        // return
        return serverNodeBean;
    }
}
