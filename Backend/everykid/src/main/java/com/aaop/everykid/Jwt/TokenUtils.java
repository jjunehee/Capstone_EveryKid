package com.aaop.everykid.Jwt;


import com.aaop.everykid.entity.Parent;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TokenUtils {

    private final String SECRET_KEY = "secretKey";
    private final String REFRESH_KEY = "refreshKey";
    private final String DATA_KEY = "PID";

    public String generateJwtToken(Parent parent) {
        return Jwts.builder()
                .setSubject(parent.getPID())
                .setHeader(createHeader())
                .setClaims(createClaims(parent))
                .setExpiration(createExpireDate(1000 * 60 * 5))
                .signWith(SignatureAlgorithm.HS256, createSigningKey(SECRET_KEY))
                .compact();
    }
    public String saveRefreshToken(Parent parent) {
        return Jwts.builder()
                .setSubject(parent.getPID())
                .setHeader(createHeader())
                .setClaims(createClaims(parent))
                .setExpiration(createExpireDate(1000 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, createSigningKey(REFRESH_KEY))
                .compact();
    }

    public boolean isValidToken(String token) {
        System.out.println("isValidToken is : " +token);
        try {
            Claims accessClaims = getClaimsFormToken(token);
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            System.out.println("Access pID: " + accessClaims.get("pID"));
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.println("Token Expired PID : " + exception.getClaims().getSubject());
            return false;
        } catch (JwtException exception) {
            System.out.println("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            System.out.println("Token is null");
            return false;
        }
    }
    public boolean isValidRefreshToken(String token) {
        try {
            Claims accessClaims = getClaimsToken(token);
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            System.out.println("Access PID: " + accessClaims.get("userId"));
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.println("Token Expired PID : " + exception.getClaims().getSubject());
            return false;
        } catch (JwtException exception) {
            System.out.println("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            System.out.println("Token is null");
            return false;
        }
    }
    private Date createExpireDate(long expireDate) {
        long curTime = System.currentTimeMillis();
        return new Date(curTime + expireDate);
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "ACCESS_TOKEN");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private Map<String, Object> createClaims(Parent parent) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(DATA_KEY, parent.getPID());
        return claims;
    }

    private Key createSigningKey(String key) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private Claims getClaimsFormToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
    }
    private Claims getClaimsToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(REFRESH_KEY))
                .parseClaimsJws(token)
                .getBody();
    }
}
