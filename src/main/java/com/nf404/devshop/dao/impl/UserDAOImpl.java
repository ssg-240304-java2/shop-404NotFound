package com.nf404.devshop.dao.impl;

import com.nf404.devshop.dao.UserDAO;
import com.nf404.devshop.mapper.UserMapper;
import com.nf404.devshop.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 사용자 데이터 액세스 객체 구현
 * 이 클래스는 사용자 데이터에 대한 CRUD 작업을 수행.
 * 사용자 데이터는 UserMapper를 통해 데이터베이스에서 관리가 될듯.
 */
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
