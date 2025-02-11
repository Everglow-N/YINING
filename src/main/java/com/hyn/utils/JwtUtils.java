package com.hyn.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    public static String createJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,"houyiningdepasswordforjwt123123123aaaasssssdddddfdfdfagdafas")
                .setExpiration(new Date(System.currentTimeMillis()+(7*24*60*60*1000)))
                .compact();
        return jwt;
    }

    public static Map<String, Object> parseJwt(String jwt) {
        Map<String,Object> claims =  Jwts.parser()
                .setSigningKey("houyiningdepasswordforjwt123123123aaaasssssdddddfdfdfagdafas")
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
