package edu.neu.karanwadhwa.springecommerceapi.dao;

import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import edu.neu.karanwadhwa.springecommerceapi.model.User;

public interface UserDAO extends DAO<User> {
    Order createOrder(int userid, Order order);
    User findByEmail(String email);
}
