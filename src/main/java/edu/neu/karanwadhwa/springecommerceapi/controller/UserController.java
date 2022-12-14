package edu.neu.karanwadhwa.springecommerceapi.controller;

import edu.neu.karanwadhwa.springecommerceapi.model.Address;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user/{userid}")
    public User getUserById(@PathVariable int userid){
        return service.getUserById(userid);
    }

    @PostMapping("/user/{userid}/address/add")
    public ResponseEntity<User> addUserAddress(@PathVariable int userid, @RequestBody Address address){
        return service.addUserAddress(userid, address);
    }

    @PostMapping("/user/profile/update")
    public ResponseEntity<User> updateUserProfile(@RequestBody User user){
        return service.updateUserProfile(user);

    }
}
