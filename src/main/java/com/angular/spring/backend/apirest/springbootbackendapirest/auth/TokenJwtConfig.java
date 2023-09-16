package com.angular.spring.backend.apirest.springbootbackendapirest.auth;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class TokenJwtConfig {

    public final static Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public final static String TOKEN_PREFIX = "Bearer";
    public final static String HEADER_AUTHORIZATION = "Authorization";
    public final static String CONTENT_TYPE = "application/json";
}
