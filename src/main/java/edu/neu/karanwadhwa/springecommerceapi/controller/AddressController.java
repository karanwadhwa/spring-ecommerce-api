package edu.neu.karanwadhwa.springecommerceapi.controller;

import edu.neu.karanwadhwa.springecommerceapi.model.Address;
import edu.neu.karanwadhwa.springecommerceapi.service.AddressService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/user/create-address")
    public Address createAddress(Address address){
        return service.createAddress(address);
    }

    @PostMapping("/user/address/{id}")
    public Address getAddressById(@PathVariable int id){
        return service.getAddressById(id);
    }

    @PutMapping("/user/update-address")
    public Address updateAddress(@RequestBody Address address){
        return service.updateAddress(address);
    }

    @DeleteMapping("/user/delete-address/{id}")
    public String deleteAddressById(@PathVariable int id){
        return service.deleteAddress(id);
    }
}
