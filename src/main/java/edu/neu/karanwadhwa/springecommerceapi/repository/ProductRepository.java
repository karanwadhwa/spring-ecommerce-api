package edu.neu.karanwadhwa.springecommerceapi.repository;

import edu.neu.karanwadhwa.springecommerceapi.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findByName(String name);
}
