package com.example.usermanagement.service;

import com.example.usermanagement.dto.PageParam;
import com.example.usermanagement.dto.PageResult;
import com.example.usermanagement.entity.Product;

public interface ProductService {
    PageResult<Product> findByPage(PageParam param);
    Product findById(Integer id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Integer id);
}
