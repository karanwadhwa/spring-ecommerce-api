package edu.neu.karanwadhwa.springecommerceapi.dao;

import edu.neu.karanwadhwa.springecommerceapi.model.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order> {
    List<Order> findByUserId(int userid);
}
