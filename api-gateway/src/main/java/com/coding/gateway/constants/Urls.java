package com.coding.gateway.constants;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Urls {
    public static final String LOGIN_ENDPOINT = "/login";
    public static final String LOGOUT_ENDPOINT = "/logout";

    public static final String AUTH_ENDPOINT = "/**";

    private static final List<String> ANONYMOUS_ENDPOINTS = new LinkedList<>();

    static {
        ANONYMOUS_ENDPOINTS.add(LOGIN_ENDPOINT);
        ANONYMOUS_ENDPOINTS.add(LOGOUT_ENDPOINT);
    }

    private Urls() {
    }

    public static List<String> getAnonymousEndpoints() {
        return Collections.unmodifiableList(ANONYMOUS_ENDPOINTS);
    }

}
