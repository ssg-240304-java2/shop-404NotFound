package com.nf404.devshop.dao.impl;

import com.nf404.devshop.dao.UserDAO;
import com.nf404.devshop.mapper.UserMapper;
import com.nf404.devshop.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public UserDTO getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public void insertUser(UserDTO user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(UserDTO user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(String userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public UserDTO loginUser(String userId, String userPw) {
        return userMapper.loginUser(userId, userPw);
    }
}
