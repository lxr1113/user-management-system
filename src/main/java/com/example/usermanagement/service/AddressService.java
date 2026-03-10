package com.example.usermanagement.service;

import com.example.usermanagement.entity.Address;
import java.util.List;

public interface AddressService {
    List<Address> findByUserId(Integer userId);
    Address findById(Integer id);
    void addAddress(Address address, Integer userId);
    void updateAddress(Address address);
    void deleteAddress(Integer id);
}
