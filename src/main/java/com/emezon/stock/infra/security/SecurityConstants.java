package com.emezon.stock.infra.security;

import com.emezon.stock.infra.inbound.rest.constants.RestApiConstants;

public class SecurityConstants {

    public static final String[] WHITE_LIST_URL = {
            "/api/docs/**",
            "/v3/api-docs/**",
            "/api/swagger-ui/**",
            RestApiConstants.API_PING + "/**",
            RestApiConstants.API_CATEGORY + "/**",
    };

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHENTICATION_SCHEME = "Bearer ";

    public static final String ROLE_NAME_CLAIM = "roleName";

    public static final String ROLE_PREFIX = "ROLE_";

    private SecurityConstants() { }

}
