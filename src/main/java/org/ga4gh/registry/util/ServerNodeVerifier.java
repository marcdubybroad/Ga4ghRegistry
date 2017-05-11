package org.ga4gh.registry.util;

import org.apache.log4j.Logger;
import org.ga4gh.registry.bean.ServerNodeBean;

/**
 * Class to verify the server node data
 *
 * Created by mduby on 5/10/17.
 */
public class ServerNodeVerifier {
    // instance variables
    private final Logger verifierLog = Logger.getLogger(this.getClass().getName());

    /**
     * method to verify server node entries
     *
     * @param serverNodeBean
     * @throws RegistryException
     */
    public void verifyNode(ServerNodeBean serverNodeBean) throws RegistryException {
        // make sure url is not null
        if (serverNodeBean.getUrl() == null) {
            String message = "Got server node with null url";
            this.verifierLog.error(message);
            throw new RegistryException(message);
        }

        // make sure type is not null
        if (serverNodeBean.getType() == null) {
            String message = "Got server node with null server node type";
            this.verifierLog.error(message);
            throw new RegistryException(message);
        }

        // make sure type is correct
        if (!RegistryConstants.RegistryType.TYPE_LIST.contains(serverNodeBean.getType())) {
            String message = "Got server node with incorrect server node type: " + serverNodeBean.getType();
            this.verifierLog.error(message);
            throw new RegistryException(message);
        }
    }
}
