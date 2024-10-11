package com.plazoleta.plazoleta.infraestructure.configuration.security;

public class JwtSecurityConstants {
    public static String SECRET_KEY = "3nKAe0ytwn6XSOf/7mI7mmyiRrdVvcl4YVy9kG6ChaI=";
    public static final String CLAIM_SUBJECT_KEY = "sub";
    public static final String CLAIM_EXPIRATION_KEY = "exp";
    public static final String CLAIM_EXPEDITION_KEY = "iat";
    public final static String CLAIM_KEY_USERID = "userid";
    public final static String CLAIM_KEY_ROLE = "role";
    public final static String CLAIM_KEY_USERNAME = "username";


    public static final Long ACCESS_TOKEN_DURATION_MINUTES = 900L;
    public static final Long REFRESH_TOKEN_DURATION_MINUTES = 1400L;
    public static final String KEY_ROLE_CLAIM = "role";

}
