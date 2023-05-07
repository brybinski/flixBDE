package com.bde.flix.security.jwt;


import com.bde.flix.security.Account.AccountDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {


    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    //TODO: remove when deployed
    private final String jwtSecretPlainText ="gVkYp3s6v9y$B&E)H@McQfTjWmZq4t7w!z%C*F-JaNdRgUkXp2r5u8x/A?D(G+Kb";

    private final Key jwtSecret = new SecretKeySpec(jwtSecretPlainText.getBytes(), "HmacSHA512");


    private long jwtExpirationMs = 2629800000L;

    @Value("${bde.flix.app.jwtCookieName}")
    private String jwtCookie;

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public ResponseCookie generateJwtCookie(AccountDetails userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
        return ResponseCookie.from(jwtCookie, jwt)
                .path("/api")
//                FIXME: Postman is not sending secure cookie
//                .secure(true)
                .maxAge(24 * 60 * 60)
                .httpOnly(true).build();
    }

    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie.from(jwtCookie, "").path("/api").build();
    }

    public String getUserNameFromJwtToken(String token) {

        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();

    }

    //TODO: maybe add iss and verification of iss for oauth
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parse(authToken);
            return true;
        } catch (SecurityException e) {
            logger.error("JWT token security violation: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("JWT token is invalid: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(jwtSecret, SignatureAlgorithm.HS512)
                .compact();
    }
}
