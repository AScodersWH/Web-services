package com.example.demo.UserClient;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
@RestController
@Slf4j
public class UserAPI {

    @PostMapping("/login")
    public String login(@RequestBody User user){
        HashMap<String, Object> claims = new HashMap<>();
        log.info("info logs");
        claims.put( user.getName(),user.getPassword());
            String token = Jwts.builder()
                    .addClaims(claims)
                    .signWith(SignatureAlgorithm.HS512, "kitty".getBytes())
                    .compact();
            return token;
    }

}
