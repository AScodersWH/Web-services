package com.example.demo.UserClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "todo-service")

public interface TodoClient {
    @PostMapping(value = "/todos/**")
    User verifyUser(User user);
}
