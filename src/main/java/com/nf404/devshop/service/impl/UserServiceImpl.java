package com.nf404.devshop.service.impl;

import com.nf404.devshop.dao.UserDAO;
import com.nf404.devshop.model.UserDTO;
import com.nf404.devshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 사용자 서비스 구현 클래스
 * 사용자 관련 CRUD 작업을 수행하며, 비즈니스 로직을 포함
 * 사용자 정보 조회, 등록, 수정, 삭제 및 로그인 기능을 제공
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public UserDTO getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public void registerUser(UserDTO user) {
        // 비즈니스 로직 추가 예정.
        // 예: 비밀번호 암호화, 유효성 검사 등..
        userDAO.insertUser(user);
    }

    @Override
    public void updateUser(UserDTO user) {
        // 비즈니스 로직 추가 예정.
        // 예: 변경된 필드만 업데이트하는 로직 등
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(String userId) {
        userDAO.deleteUser(userId);
    }

    @Override
    public UserDTO loginUser(String userId, String userPw) {
        // 비즈니스 로직 추가 예정.
        // 예: 비밀번호 암호화 확인, 로그인 시도 횟수 체크 등
        return userDAO.loginUser(userId, userPw);
    }
}
