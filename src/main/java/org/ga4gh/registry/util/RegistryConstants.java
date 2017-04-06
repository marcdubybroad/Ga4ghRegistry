package org.ga4gh.registry.util;

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
    }

    public static final class Lookup {
        public static final String ANONYMOUS_EMAIL          = "anonymous@broadinstitute.org";

        public static final int ENTITY_TYPE_ID_DATASET      = 1;
        public static final int ENTITY_TYPE_ID_EXPERIMENT   = 2;
    }

    public static final class RegistryType {
        public static final String MATCHMAKER               = "matchmaker";
        public static final String BEACON                   = "beacon";
        public static final String REGISTRY                 = "regsitry";
        public static final String GA4GH                    = "ga4gh";
    }
}
