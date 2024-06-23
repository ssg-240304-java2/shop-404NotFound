package com.nf404.devshop.mapper;


import com.nf404.devshop.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDTO> getAllUsers();
}
