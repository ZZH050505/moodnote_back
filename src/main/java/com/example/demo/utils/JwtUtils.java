package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final String SIGN_KEY = "aXRoZWltYQ=="; // 签名密钥
    private static final Long EXPIRE_TIME = 51200000L; // 过期时间(毫秒)，这里设置为12小时

    /**
     * 生成JWT令牌
     * @param claims 自定义载荷信息
     * @return JWT令牌字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY) // 设置签名算法和密钥
                .addClaims(claims) // 添加载荷信息
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME)) // 设置过期时间
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌字符串
     * @return Claims对象，包含令牌中的载荷信息
     */
    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(SIGN_KEY) // 设置签名密钥
                .parseClaimsJws(jwt)
                .getBody();
    }
}
