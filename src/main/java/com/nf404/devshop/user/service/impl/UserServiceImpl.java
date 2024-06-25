package com.nf404.devshop.user.service.impl;

import com.nf404.devshop.user.mapper.UserMapper;
import com.nf404.devshop.user.service.UserService;
import com.nf404.devshop.user.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 사용자 서비스 구현 클래스
 * 사용자 관련 CRUD 작업을 수행하며, 비즈니스 로직을 포함
 * 사용자 정보 조회, 등록, 수정, 삭제 및 로그인 기능을 제공
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userMapper.getAllUsers();
        log.info("Retrieved users: {}", users);
        if (users == null || users.isEmpty()) {
            log.warn("No users found or users list is null");
        } else {
            log.info("Number of users retrieved: {}", users.size());
            for (UserDTO user : users) {
                log.info("User: {}", user);
            }
        }
        return userMapper.getAllUsers();
    }


    @Override
    public UserDTO getUserById(String userId) {
        UserDTO user = userMapper.getUserById(userId);
        log.info("Retrieved user: {}", user);  // 객체의 내용을 로그로 출력
        if (user == null) {
            log.warn("User not found with id: {}", userId);
        } else {
            log.info("User found: id={}, name={}", user.getUserId(), user.getUserName());
        }
        return userMapper.getUserById(userId);
    }


    @Override
    public void registerUser(UserDTO user) {
        // 비즈니스 로직 추가 예정.
        // 예: 비밀번호 암호화, 유효성 검사 등..
        // 기본 랭크 설정 (예: 1)
        user.setUserRank(1);
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(UserDTO user) {
        // 비즈니스 로직 추가 예정.
        // 예: 변경된 필드만 업데이트하는 로직 등
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(String userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public UserDTO loginUser(String userId, String userPw) {
        // 비즈니스 로직 추가 예정.
        // 예: 비밀번호 암호화 확인, 로그인 시도 횟수 체크 등
        log.info("Attempting login for user: {}", userId);
        UserDTO user = userMapper.loginUser(userId, userPw);
        if (user == null) {
            log.info("Login failed for user: {}", userId);
        } else {
            log.info("Login successful for user: {}", userId);
        }
        return userMapper.loginUser(userId, userPw);
    }

    @Override
    public List<UserDTO> getFilteredUsers(String userId, String userName, Integer userRank, LocalDate startDate, LocalDate endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userName", userName);
        params.put("userRank", userRank);
        params.put("startDate", startDate != null ? startDate.toString() : null);
        params.put("endDate", endDate != null ? endDate.toString() : null);

        log.debug("Filtered users params: {}", params);
        return userMapper.selectFilteredUsers(params);
    }



}
