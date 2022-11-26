package edu.neu.karanwadhwa.springecommerceapi.controller;

import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/auth/signup")
    public User signup(@RequestBody User user){
        return service.createUser(user);
    }

    @GetMapping("/auth/test")
    public @ResponseBody String test(){
        return "Auth test";
    }
}
