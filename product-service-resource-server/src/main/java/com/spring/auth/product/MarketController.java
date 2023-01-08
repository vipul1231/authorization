package com.spring.auth.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class MarketController {

    public Map<Integer,User> marketMap = new HashMap<>();

    @PostConstruct
    public void init() {
        marketMap.put(12, User.builder().id(12).name("Vipul").password("Vipul").build());
    }

    @GetMapping("/market/{id}")
    @RolesAllowed({"user_read"})
    public String getUser(@PathVariable Integer id, Principal principal) {
        log.info("User trying to access the service: {}", principal.getName());
        return "Response from market service: "+marketMap.get(id);
    }


    @PostMapping("/market")
    @RolesAllowed({"user_read"})
    public User submitUser(User user, Principal principal) {
        marketMap.put(user.id, user);
        log.info("Response from market service: "+principal.getName());
        return user;
    }

}
