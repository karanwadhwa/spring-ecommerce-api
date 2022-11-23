package edu.neu.karanwadhwa.springecommerceapi.service;

import edu.neu.karanwadhwa.springecommerceapi.model.Address;
import edu.neu.karanwadhwa.springecommerceapi.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public Address createAddress(Address address){
        return repository.save(address);
    }

    public Address getAddressById(int id){
        return repository.findById(id).orElse(null);
    }

    public Address updateAddress(Address newAddress){
        Address oldAddress = getAddressById(newAddress.getId());
        oldAddress.setApt(newAddress.getApt());
        oldAddress.setCity(newAddress.getCity());
        oldAddress.setCountry(newAddress.getCountry());
        oldAddress.setStreet(newAddress.getStreet());
        oldAddress.setPin(newAddress.getPin());
        return repository.save(oldAddress);
    }

    public String deleteAddress(int id){
        repository.deleteById(id);
        return "Address deleted: #"+id;
    }
}
