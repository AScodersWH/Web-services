package com.example.demo.UserClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-service")
public interface UserClient {
    @PostMapping(value = "/verify")
    User verifyUser(User user);
    @PostMapping(value = "/login")
    User login(User user);
}
