package edu.neu.karanwadhwa.springecommerceapi.dao;

import edu.neu.karanwadhwa.springecommerceapi.model.User;

public interface UserDAO {
    User createUser(User user);
    User getUserById(int userid);
    User updateUser(User user);
    void deleteUser(int userid);
}
