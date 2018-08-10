package com.example.demo.UserClient;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
@RestController
public class UserAPI {
    @Autowired
    UserClient userClient;

    @PostMapping("/api/login")
    public String login(@RequestBody User user1) throws ChangeSetPersister.NotFoundException {
        HashMap<String, Object> claims = new HashMap<>();
        User user = userClient.verifyUser(user1);
        claims.put(user.getId().toString(), user.getName());
            String token = Jwts.builder()
                    .addClaims(claims)
                    .signWith(SignatureAlgorithm.HS512, "kitty".getBytes())
                    .compact();
            return token;
    }

}
