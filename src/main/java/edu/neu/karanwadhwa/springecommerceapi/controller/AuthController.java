package edu.neu.karanwadhwa.springecommerceapi.controller;

import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/auth/signup")
    public User signup(@RequestBody User user){
        return service.createUser(user);
    }


    @PostMapping("/auth/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> requestBody){
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        return service.loginUser(email, password);
    }
}
