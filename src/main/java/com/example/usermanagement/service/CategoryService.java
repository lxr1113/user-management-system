package com.example.usermanagement.service;

import com.example.usermanagement.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findByParentId(Integer parentId);
    Category findById(Integer id);
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Integer id);
}
