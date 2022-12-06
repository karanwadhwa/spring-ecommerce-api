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
        return userDAO.createUser(user);
    }

    public User getUserById(int userid) {
        return userDAO.getUserById(userid);
    }

//    private User getUserByEmail(String email) {
//        return repository.findByEmail(email);
//    }

//    public ResponseEntity<User> loginUser(String email, String password) {
//        User user = getUserByEmail(email);
//        try {
//            if (!user.getPassword().equals(password)) throw new UserAuthenticationException("Invalid password!");
//        } catch (NullPointerException ex) {
//            throw new UserAuthenticationException("User not found!");
//        }
//
//        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
//    }

    public ResponseEntity<User> addUserAddress(int userid, Address address) {
        User user = userDAO.getUserById(userid);
        if (!user.getUsertype().equals("customer")) {
            throw new UserNotAllowedException(user.getUsertype());
        }
        user.getAddresses().add(address);
        return new ResponseEntity<>(userDAO.updateUser(user), HttpStatus.CREATED);
    }
}
