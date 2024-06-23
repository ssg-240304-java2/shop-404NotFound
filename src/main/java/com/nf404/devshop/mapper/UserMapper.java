package com.nf404.devshop.mapper;

import com.nf404.devshop.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(@Param("userId") String userId);
    void insertUser(UserDTO user);
    void updateUser(UserDTO user);
    void deleteUser(@Param("userId") String userId);
    UserDTO loginUser(@Param("userId") String userId, @Param("userPw") String userPw);
}
