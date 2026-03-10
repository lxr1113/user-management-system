package com.example.usermanagement.service.impl;

import com.example.usermanagement.entity.Address;
import com.example.usermanagement.mapper.AddressMapper;
import com.example.usermanagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> findByUserId(Integer userId) {
        return addressMapper.findByUserId(userId);
    }

    @Override
    public Address findById(Integer id) {
        return addressMapper.findById(id);
    }

    @Override
    @Transactional
    public void addAddress(Address address, Integer userId) {
        if (address.getReceiverName() == null || address.getReceiverName().trim().isEmpty()) {
            throw new IllegalArgumentException("收货人不能为空");
        }
        if (address.getPhone() == null || address.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("联系电话不能为空");
        }
        if (address.getDetailAddress() == null || address.getDetailAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("详细地址不能为空");
        }
        address.setUserId(userId);
        if (address.getIsDefault() == null) {
            address.setIsDefault(0);
        }
        if (address.getIsDefault() == 1) {
            addressMapper.clearDefault(userId);
        }
        addressMapper.insert(address);
    }

    @Override
    @Transactional
    public void updateAddress(Address address) {
        if (address.getId() == null) {
            throw new IllegalArgumentException("地址ID不能为空");
        }
        if (address.getIsDefault() != null && address.getIsDefault() == 1) {
            Address existing = addressMapper.findById(address.getId());
            if (existing != null) {
                addressMapper.clearDefault(existing.getUserId());
            }
        }
        addressMapper.update(address);
    }

    @Override
    @Transactional
    public void deleteAddress(Integer id) {
        addressMapper.delete(id);
    }
}
