package com.example.demo.security;

import com.example.demo.UserClient.UserClient;
import com.example.demo.UserClient.User;
import com.google.common.net.HttpHeaders;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class ToDoAuthFilter extends OncePerRequestFilter {

    @Autowired
    UserClient userClient;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (null != token && !token.equals("")) {
            Claims claims = com.example.demo.ZuulService.ZuulService.verifyToken(token);
            User user = new User();
            user.setName((String) claims.get("name"));
            user.setId((Integer) claims.get("id"));
//            User user1 = client.verifyUser(user);
            String newToken = user.getId() + ":" + user.getName();
            RequestContext.getCurrentContext().addZuulRequestHeader(HttpHeaders.AUTHORIZATION, newToken);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()));

            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }

}
