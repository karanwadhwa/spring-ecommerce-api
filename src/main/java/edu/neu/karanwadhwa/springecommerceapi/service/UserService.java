package edu.neu.karanwadhwa.springecommerceapi.service;

import edu.neu.karanwadhwa.springecommerceapi.dao.UserDAO;
import edu.neu.karanwadhwa.springecommerceapi.dao.impl.UserDAOImpl;
import edu.neu.karanwadhwa.springecommerceapi.model.Address;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserAuthenticationException;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserNotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO = new UserDAOImpl();

    public User createUser(User user) {
        return userDAO.create(user);
    }

    public User getUserById(int userid) {
        return userDAO.findById(userid);
    }

    private User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
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
        User user = userDAO.findById(userid);
        if (!user.getUsertype().equals("customer")) {
            throw new UserNotAllowedException(user.getUsertype());
        }
        user.getAddresses().add(address);
        return new ResponseEntity<>(userDAO.update(user), HttpStatus.CREATED);
    }

    public ResponseEntity<User> updateUserProfile(User user){
        User existingData = userDAO.findById(user.getUserid());
        if(user.getEmail() == null || user.getEmail().length() == 0)
            throw new UserAuthenticationException("Invalid Email!");
        if(user.getFname() == null || user.getFname().length() == 0)
            throw new UserAuthenticationException("Firstname cannot be empty");
        existingData.setFname(user.getFname());
        existingData.setLname(user.getLname());
        existingData.setEmail(user.getEmail());

        return new ResponseEntity<>(userDAO.update(existingData), HttpStatus.OK);
    }
}
