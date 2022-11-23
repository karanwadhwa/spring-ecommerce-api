package edu.neu.karanwadhwa.springecommerceapi.repository;

import edu.neu.karanwadhwa.springecommerceapi.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findByName(String name);
}
