package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.PageParam;
import com.example.usermanagement.dto.PageResult;
import com.example.usermanagement.entity.Product;
import com.example.usermanagement.mapper.ProductMapper;
import com.example.usermanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageResult<Product> findByPage(PageParam param) {
        Long current = param.getCurrent();
        Long size = param.getSize();
        String keyword = param.getKeyword();
        Integer categoryId = param.getCategoryId();
        Integer brandId = param.getBrandId();
        
        Long offset = (current - 1) * size;
        List<Product> records = productMapper.findByPage(keyword, categoryId, brandId, offset, size);
        Long total = productMapper.countByPage(keyword, categoryId, brandId);
        
        return PageResult.of(records, total, current, size);
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("商品名称不能为空");
        }
        if (product.getPrice() == null) {
            throw new IllegalArgumentException("商品价格不能为空");
        }
        if (product.getStatus() == null) {
            product.setStatus(1);
        }
        if (product.getStock() == null) {
            product.setStock(0);
        }
        if (product.getSales() == null) {
            product.setSales(0);
        }
        productMapper.insert(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("商品ID不能为空");
        }
        productMapper.update(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        productMapper.delete(id);
    }
}
