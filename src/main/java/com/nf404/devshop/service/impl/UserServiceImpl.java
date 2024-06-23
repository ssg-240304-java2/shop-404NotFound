package com.nf404.devshop.service.impl;

import com.nf404.devshop.dao.UserDAO;
import com.nf404.devshop.model.UserDTO;
import com.nf404.devshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 사용자 서비스 구현 클래스
 * 사용자 관련 CRUD 작업을 수행하며, 비즈니스 로직을 포함
 * 사용자 정보 조회, 등록, 수정, 삭제 및 로그인 기능을 제공
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userDAO.getAllUsers();
        log.info("Retrieved users: {}", users);
        if (users == null || users.isEmpty()) {
            log.warn("No users found or users list is null");
        } else {
            log.info("Number of users retrieved: {}", users.size());
            for (UserDTO user : users) {
                log.info("User: {}", user);
            }
        }
        return users;
    }


    @Override
    public UserDTO getUserById(String userId) {
        UserDTO user = userDAO.getUserById(userId);
        log.info("Retrieved user: {}", user);  // 객체의 내용을 로그로 출력
        if (user == null) {
            log.warn("User not found with id: {}", userId);
        } else {
            log.info("User found: id={}, name={}", user.getUserId(), user.getUserName());
        }
        return user;
    }


    @Override
    public void registerUser(UserDTO user) {
        // 비즈니스 로직 추가 예정.
        // 예: 비밀번호 암호화, 유효성 검사 등..
        // 기본 랭크 설정 (예: 1)
        user.setUserRank(1);
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
