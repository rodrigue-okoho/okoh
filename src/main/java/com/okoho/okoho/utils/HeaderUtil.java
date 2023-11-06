package com.okoho.okoho.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String APPLICATION_NAME = "GPaie";

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-GPaie-alert", message);
        headers.add("X-GPaie-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String applicationName, boolean b, String entityName,
    String id) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", id);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-GPaie-error", "error." + errorKey);
        headers.add("X-GPaie-params", entityName);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String applicationName, boolean b, String entityName,
            String id) {
                return createAlert(APPLICATION_NAME + "." + entityName + ".created", id);
    }

    public static HttpHeaders createEntityDeletionAlert(String applicationName, boolean b, String entityName,
            String id) {
        return null;
    }
}