package com.example.usermanagement.controller;

import com.example.usermanagement.dto.Result;
import com.example.usermanagement.entity.Category;
import com.example.usermanagement.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品分类")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("分类列表")
    @GetMapping
    public Result<List<Category>> list() {
        return Result.success(categoryService.findAll());
    }

    @ApiOperation("根据父ID获取子分类")
    @GetMapping("/children")
    public Result<List<Category>> children(@RequestParam Integer parentId) {
        return Result.success(categoryService.findByParentId(parentId));
    }

    @ApiOperation("获取分类详情")
    @GetMapping("/{id}")
    public Result<Category> getById(@PathVariable Integer id) {
        return Result.success(categoryService.findById(id));
    }

    @ApiOperation("新增分类")
    @PostMapping
    public Result<String> add(@RequestBody Category category) {
        categoryService.addCategory(category);
        return Result.success("添加成功");
    }

    @ApiOperation("更新分类")
    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Integer id, @RequestBody Category category) {
        category.setId(id);
        categoryService.updateCategory(category);
        return Result.success("更新成功");
    }

    @ApiOperation("删除分类")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return Result.success("删除成功");
    }
}
