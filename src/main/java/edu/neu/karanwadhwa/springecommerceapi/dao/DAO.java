package edu.neu.karanwadhwa.springecommerceapi.dao;

import java.util.List;

public interface DAO<T> {
    T findById(int id);
    List<T> getAll(int userid);
    T create(T t);
    T update(T t);
    void delete(int id);
}
