package edu.neu.karanwadhwa.springecommerceapi.repository;

import edu.neu.karanwadhwa.springecommerceapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
