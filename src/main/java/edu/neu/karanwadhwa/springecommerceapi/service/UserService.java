package edu.neu.karanwadhwa.springecommerceapi.service;

import edu.neu.karanwadhwa.springecommerceapi.model.Address;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.repository.UserRepository;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserAuthenticationException;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserNotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public User getUserById(int userid) {
        return repository.findById(userid).orElse(null);
    }

    private User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public ResponseEntity<User> loginUser(String email, String password) {
        User user = getUserByEmail(email);
        try {
            if (!user.getPassword().equals(password)) throw new UserAuthenticationException("Invalid password!");
        } catch (NullPointerException ex) {
            throw new UserAuthenticationException("User not found!");
        }

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<User> addUserAddress(int userid, Address address) {
        User user = getUserById(userid);
        if (!user.getUsertype().equals("customer")) {
            throw new UserNotAllowedException(user.getUsertype());
        }
        Collection<Address> newAddresses = user.getAddresses();
        newAddresses.add(address);
        user.setAddresses(newAddresses);
        return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
    }
}
