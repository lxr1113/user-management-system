package com.example.usermanagement.service.impl;

import com.example.usermanagement.entity.Category;
import com.example.usermanagement.mapper.CategoryMapper;
import com.example.usermanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public List<Category> findByParentId(Integer parentId) {
        return categoryMapper.findByParentId(parentId);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("分类名称不能为空");
        }
        if (category.getParentId() == null) {
            category.setParentId(0);
        }
        if (category.getParentId() == 0) {
            category.setLevel(1);
        } else {
            Category parent = categoryMapper.findById(category.getParentId());
            if (parent != null) {
                category.setLevel(parent.getLevel() + 1);
            }
        }
        categoryMapper.insert(category);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        if (category.getId() == null) {
            throw new IllegalArgumentException("分类ID不能为空");
        }
        categoryMapper.update(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Integer id) {
        List<Category> children = categoryMapper.findByParentId(id);
        if (children != null && !children.isEmpty()) {
            throw new RuntimeException("请先删除子分类");
        }
        categoryMapper.delete(id);
    }
}
