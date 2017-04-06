package org.ga4gh.registry.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ga4gh.registry.util.RegistryConstants;

/**
 * Bean class to encapsulate the server settings
 * <br/>
 * Tried using Jackson json serialization, but didn't like cryptic error messages, so went with manual parsing. Keeping annotations anyway just in case
 *
 * Created by mduby on 4/5/17.
 */
public class ServerNodeBean {
    // instance variables
    @JsonProperty(RegistryConstants.JsonKeys.TYPE)
    private String type = null;

    @JsonProperty(RegistryConstants.JsonKeys.URL)
    private String url = null;

    public ServerNodeBean() {
        super();
    }

    public ServerNodeBean(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
