package org.ga4gh.registry.util;

/**
 * Exception class for registry services
 *
 * Created by mduby on 3/28/17.
 */
public class RegistryException extends Exception {

    /**
     * default constructor
     *
     */
    public RegistryException() {
        super();
    }

    /**
     * constructor with text message
     *
     * @param message
     */
    public RegistryException(String message) {
        super(message);
    }
}
