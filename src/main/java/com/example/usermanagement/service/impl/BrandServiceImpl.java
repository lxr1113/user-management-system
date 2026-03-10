package com.example.usermanagement.service.impl;

import com.example.usermanagement.entity.Brand;
import com.example.usermanagement.mapper.BrandMapper;
import com.example.usermanagement.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.findById(id);
    }

    @Override
    @Transactional
    public void addBrand(Brand brand) {
        if (brand.getName() == null || brand.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("品牌名称不能为空");
        }
        brandMapper.insert(brand);
    }

    @Override
    @Transactional
    public void updateBrand(Brand brand) {
        if (brand.getId() == null) {
            throw new IllegalArgumentException("品牌ID不能为空");
        }
        brandMapper.update(brand);
    }

    @Override
    @Transactional
    public void deleteBrand(Integer id) {
        brandMapper.delete(id);
    }
}
