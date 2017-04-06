package org.ga4gh.registry.service;

import org.apache.log4j.Logger;
import org.ga4gh.registry.util.RegistryConstants;
import org.ga4gh.registry.util.RegistryException;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Service class to retrieve registry data based on input
 *
 * Created by mduby on 3/28/17.
 */
@Service
public class RegistryService {
    // instance variables
    private final Logger serviceLog = Logger.getLogger(this.getClass().getName());

    public JsonObject getListOfPeers(String email) {
        // local variables
        JsonObject resultObject = null;

        // throw error for nnow
        try {
            throw new RegistryException("getListPfPeers not implemented yet");

        } catch (RegistryException exception) {
            resultObject = this.buildErrorObject(exception);
        }

        // return
        return resultObject;
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
