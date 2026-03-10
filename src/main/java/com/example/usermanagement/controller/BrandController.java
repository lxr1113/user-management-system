package com.example.usermanagement.controller;

import com.example.usermanagement.dto.Result;
import com.example.usermanagement.entity.Brand;
import com.example.usermanagement.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "品牌管理")
@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @ApiOperation("品牌列表")
    @GetMapping
    public Result<List<Brand>> list() {
        return Result.success(brandService.findAll());
    }

    @ApiOperation("获取品牌详情")
    @GetMapping("/{id}")
    public Result<Brand> getById(@PathVariable Integer id) {
        return Result.success(brandService.findById(id));
    }

    @ApiOperation("新增品牌")
    @PostMapping
    public Result<String> add(@RequestBody Brand brand) {
        brandService.addBrand(brand);
        return Result.success("添加成功");
    }

    @ApiOperation("更新品牌")
    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Integer id, @RequestBody Brand brand) {
        brand.setId(id);
        brandService.updateBrand(brand);
        return Result.success("更新成功");
    }

    @ApiOperation("删除品牌")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return Result.success("删除成功");
    }
}
