package com.example.usermanagement.mapper;

import com.example.usermanagement.entity.Address;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AddressMapper {

    @Select("SELECT * FROM address WHERE user_id = #{userId} ORDER BY is_default DESC, id DESC")
    List<Address> findByUserId(Integer userId);

    @Select("SELECT * FROM address WHERE id = #{id}")
    Address findById(Integer id);

    @Insert("INSERT INTO address(user_id, receiver_name, phone, province, city, district, detail_address, is_default) " +
            "VALUES(#{userId}, #{receiverName}, #{phone}, #{province}, #{city}, #{district}, #{detailAddress}, #{isDefault})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Address address);

    @Update("UPDATE address SET receiver_name=#{receiverName}, phone=#{phone}, province=#{province}, " +
            "city=#{city}, district=#{district}, detail_address=#{detailAddress}, is_default=#{isDefault} WHERE id=#{id}")
    int update(Address address);

    @Delete("DELETE FROM address WHERE id = #{id}")
    int delete(Integer id);

    @Update("UPDATE address SET is_default = 0 WHERE user_id = #{userId}")
    int clearDefault(Integer userId);
}
