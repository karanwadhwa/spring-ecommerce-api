package edu.neu.karanwadhwa.springecommerceapi.dao;

import edu.neu.karanwadhwa.springecommerceapi.model.Product;

import java.util.List;

public interface ProductDAO extends DAO<Product> {
    void saveAll(List<Product> products);
    List<Product> getAll();
    List<Product> findByName(String keyword);
    List<Product> findBySellerId(int sellerId);
}
