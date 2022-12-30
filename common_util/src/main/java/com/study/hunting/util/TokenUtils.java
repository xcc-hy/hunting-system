package com.study.hunting.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    protected TokenUtils() {
    }

    //设置token过期时间为2h
    private final static long EXPIRED_TIME = 120 * 60 * 1000;

    //设置token的密钥
    private final static String SECRET = "hunting-xcc-secret-token-base-on-jwt";
    private final static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public static String getToken(Integer userId) {
        //token的Header信息
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return Jwts.builder()
                .setHeader(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_TIME))
                .claim("id", userId)
                .signWith(signatureAlgorithm, SECRET)
                .compact();
    }

    public static Claims parseToken(String jwt) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody();
    }

    public static void main(String[] args) throws Exception {
        String token = TokenUtils.getToken(12);
        System.out.println(token);
        Claims claims = TokenUtils.parseToken(token);
        System.out.println(claims.get("id"));
        System.out.println(claims.getExpiration());
    }
}
