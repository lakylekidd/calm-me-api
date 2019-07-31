package com.stress.stress.helpers;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.stress.stress.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHelper {
    public static String key = "kladjfl;akuoefuaw98ru9p84fryuha9oie8ufhj983y4htgs89uy4hej9g8s34yuhtjg98as4yugjp84uojg9ser";

    //Sample method to construct a JWT
    public static String CreateJWT(User user) {   

        long ttlMillis = 36000;
        
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
    
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(TokenHelper.key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Create additional claims
        Claims claims = Jwts.claims();
        claims.put("first_name", user.getFirstname());
        claims.put("last_name", user.getLastname());
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
    
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
            .setIssuedAt(now)
            .setSubject(user.getId().toString())
            .setIssuer("http://localhost:8080")
            .setClaims(claims)
            .signWith(signingKey, signatureAlgorithm);
    
        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
        long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
    
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    //Sample method to validate and read the JWT
    public static void ParseJWT(String jwt) {    
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()         
        .setSigningKey(DatatypeConverter.parseBase64Binary(TokenHelper.key))
        .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }
}