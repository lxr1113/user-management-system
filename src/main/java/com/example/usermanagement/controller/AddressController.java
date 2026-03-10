package com.example.usermanagement.controller;

import com.example.usermanagement.dto.Result;
import com.example.usermanagement.entity.Address;
import com.example.usermanagement.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "收货地址")
@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation("我的地址列表")
    @GetMapping
    public Result<List<Address>> list(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        return Result.success(addressService.findByUserId(userId));
    }

    @ApiOperation("获取地址详情")
    @GetMapping("/{id}")
    public Result<Address> getById(@PathVariable Integer id) {
        return Result.success(addressService.findById(id));
    }

    @ApiOperation("新增地址")
    @PostMapping
    public Result<String> add(@RequestBody Address address, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        addressService.addAddress(address, userId);
        return Result.success("添加成功");
    }

    @ApiOperation("更新地址")
    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Integer id, @RequestBody Address address) {
        address.setId(id);
        addressService.updateAddress(address);
        return Result.success("更新成功");
    }

    @ApiOperation("删除地址")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return Result.success("删除成功");
    }
}
