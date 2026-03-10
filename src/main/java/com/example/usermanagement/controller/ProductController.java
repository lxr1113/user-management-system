package com.example.usermanagement.controller;

import com.example.usermanagement.dto.PageParam;
import com.example.usermanagement.dto.PageResult;
import com.example.usermanagement.dto.Result;
import com.example.usermanagement.entity.Product;
import com.example.usermanagement.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("商品列表（分页）")
    @GetMapping
    public Result<PageResult<Product>> page(PageParam param) {
        return Result.success(productService.findByPage(param));
    }

    @ApiOperation("商品详情")
    @GetMapping("/{id}")
    public Result<Product> getById(@ApiParam("商品ID") @PathVariable Integer id) {
        return Result.success(productService.findById(id));
    }

    @ApiOperation("新增商品")
    @PostMapping
    public Result<String> add(@RequestBody Product product) {
        productService.addProduct(product);
        return Result.success("添加成功");
    }

    @ApiOperation("更新商品")
    @PutMapping("/{id}")
    public Result<String> update(@ApiParam("商品ID") @PathVariable Integer id, @RequestBody Product product) {
        product.setId(id);
        productService.updateProduct(product);
        return Result.success("更新成功");
    }

    @ApiOperation("删除商品")
    @DeleteMapping("/{id}")
    public Result<String> delete(@ApiParam("商品ID") @PathVariable Integer id) {
        productService.deleteProduct(id);
        return Result.success("删除成功");
    }
}
