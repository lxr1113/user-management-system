package com.example.usermanagement.service;

import com.example.usermanagement.entity.Brand;
import java.util.List;

public interface BrandService {
    List<Brand> findAll();
    Brand findById(Integer id);
    void addBrand(Brand brand);
    void updateBrand(Brand brand);
    void deleteBrand(Integer id);
}
