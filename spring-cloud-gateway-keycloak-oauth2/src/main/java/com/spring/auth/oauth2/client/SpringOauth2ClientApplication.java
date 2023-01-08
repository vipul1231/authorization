package com.spring.auth.oauth2.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication(exclude = ReactiveUserDetailsServiceAutoConfiguration.class)
public class SpringOauth2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOauth2ClientApplication.class, args);
    }

}
