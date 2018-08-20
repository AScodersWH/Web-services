package com.example.demo.ZuulService;

import com.example.demo.UserClient.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashMap;
@Service
public class ZuulService {

    public static String generateJwtToken(HashMap hashMap) {
        return Jwts.builder()
                .addClaims(hashMap)
                .signWith(SignatureAlgorithm.HS512, "kitty")
                .compact();
    }

    public static Claims verifyToken(String token) {
        return Jwts
                .parser()
                .setSigningKey("kitty")
                .parseClaimsJws(token)
                .getBody();

    }

//    public User verifyUser(String token) {
//        Claims body = Jwts.parser().setSigningKey("kitty".getBytes())
//                .parseClaimsJws(token)
//                .getBody();
//
//        String id = (String) body.get("id");
//        String username = (String) body.get("name");
//        if(verify(id,username)) {
//            User user = new User();
//            user.setId(Integer.valueOf(id));
//            user.setName(username);
//            return user;
//        }
//        else return null;
//    }

}