package edu.neu.karanwadhwa.springecommerceapi.repository;

import edu.neu.karanwadhwa.springecommerceapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
