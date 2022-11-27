package edu.neu.karanwadhwa.springecommerceapi.repository;

import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}