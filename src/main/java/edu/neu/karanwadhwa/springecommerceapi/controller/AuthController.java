package edu.neu.karanwadhwa.springecommerceapi.controller;

import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/auth/signup")
    public User signup(@RequestParam String fname, @RequestParam String lname, @RequestParam String email, @RequestParam String password, @RequestParam String usertype){
        User user = new User(fname,lname, email, password, usertype);
        return userRepository.save(user);
    }

    @GetMapping("/auth/test")
    public @ResponseBody String test(){
        return "Auth test";
    }
}
