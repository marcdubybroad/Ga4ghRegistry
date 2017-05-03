package org.ga4gh.registry.util;

import java.util.Arrays;
import java.util.List;

/**
 * Constants class to centrally hold all constant values
 *
 * Created by mduby on 3/28/17.
 */
public class RegistryConstants {

    public static final class JsonKeys {
        public static final String MESSAGE                  = "message";
        public static final String STATUS                   = "status";
        public static final String IS_ERROR                 = "is_error";
        public final static String ERROR_MESSAGE            = "error_message";
        public static final String NUMBER_RECORDS           = "numRecords";
        public static final String VALUE                    = "value";
        public static final String NAME                     = "name";
        public static final String VERSION                  = "version";

        public static final String TYPE                     = "type";
        public static final String URL                      = "url";
        public static final String PARENT                   = "parent";
        public static final String PEERS                    = "peers";
    }

    public static final class RegistryType {
        public static final String MATCHMAKER               = "matchmaker";
        public static final String BEACON                   = "beacon";
        public static final String REGISTRY                 = "registry";
        public static final String GA4GH                    = "ga4gh";

        public static final List<String> TYPE_LIST          = Arrays.asList(MATCHMAKER, BEACON, REGISTRY, GA4GH);
    }
}
