package edu.neu.karanwadhwa.springecommerceapi.service;

import edu.neu.karanwadhwa.springecommerceapi.model.Address;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user){
        return repository.save(user);
    }

    public User getUserById(int userid){
        return repository.findById(userid).orElse(null);
    }

    public User addUserAddress(int userid, Address address){
        User user = getUserById(userid);
        Collection<Address> newAddresses = user.getAddresses();
        newAddresses.add(address);
        user.setAddresses(newAddresses);
        return repository.save(user);
    }
}
