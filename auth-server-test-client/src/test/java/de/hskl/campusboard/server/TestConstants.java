/*
 *
 * Copyright (c) 2016 Hochschule Kaiserslautern
 * Standort Zweibruecken
 * All Rights Reserved
 *
 */
package de.hskl.campusboard.server;

/**
 * @author jean-luc
 *
 */
public class TestConstants
{
    //TODO Should be loaded from property-file

    public static final String TEST_DOMAIN_AUTH_BASE = "https://campusboard-auth.eu-gb.mybluemix.net";
    public static final String TEST_DOMAIN_RESOURCE_BASE = "https://campusboard-auth.eu-gb.mybluemix.net";

    public static final String TEST_TOKEN_ENDPOINT = TEST_DOMAIN_AUTH_BASE + "/api/auth/token";

    public static final String TEST_RESOURCE_USER_ENDPOINT = TEST_DOMAIN_RESOURCE_BASE + "/user/v1";

    private static final String GENERELL_TEST_RESOURCE_ENDPOINT = TEST_DOMAIN_RESOURCE_BASE + "/api/resource";
    public static final String TEST_RESOURCE_CANTEEN_ENDPOINT_V1 = GENERELL_TEST_RESOURCE_ENDPOINT + "/v1/canteen";
    public static final String TEST_RESOURCE_TIMETABLE_ENDPOINT_V1 = GENERELL_TEST_RESOURCE_ENDPOINT + "/v1/timetable";

    public static final String TEST_CLIENT_ID = "314";
    public static final String TEST_CLIENT_SECRET = "123456";
    public static final String TEST_USERNAME = "julian.neuhaus";
    public static final String TEST_USER_PASSWORD = "test123";

    public static final String INVALID_CLIENT_ID = "invalid_client_id";
    public static final String INVALID_CLIENT_SECRET = "invalid_client_secret";
    public static final String INVALID_USERNAME = "invalid_username";
    public static final String INVALID_USER_PASSWORD = "invalid_user_password";
    public static final String INVALID_ACCESS_TOKEN = "invalid_access_token";
}
