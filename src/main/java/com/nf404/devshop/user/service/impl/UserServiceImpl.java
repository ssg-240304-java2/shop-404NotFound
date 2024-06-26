package com.nf404.devshop.user.service.impl;

import com.nf404.devshop.user.mapper.UserMapper;
import com.nf404.devshop.user.service.UserService;
import com.nf404.devshop.user.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        log.info("Retrieved non-deleted users: {}", users);
        if (users == null || users.isEmpty()) {
            log.warn("No non-deleted users found or users list is null");
        } else {
            log.info("Number of non-deleted users retrieved: {}", users.size());
        }
        return users;
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

//    @Override
//    public void deleteUser(String userId) {
//        userMapper.deleteUser(userId);
//    }

    @Override
    public void softDeleteUser(String userId) {
        userMapper.softDeleteUser(userId);
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

    /**
     * 사용자의 등급을 업데이트합니다.
     * 이 메서드는 public으로 선언되어 외부에서 접근 가능하며, @Transactional 어노테이션이 적용됩니다.
     * 주요 역할:
     * 1. 사용자 존재 여부를 확인합니다.
     * 2. 트랜잭션 경계를 설정합니다.
     * 3. 실제 업데이트 로직을 수행하는 내부 메서드를 호출합니다.
     *
     * 이렇게 분리함으로써 트랜잭션 관리를 명확히 하고,
     * 같은 클래스 내에서의 @Transactional 메서드 호출로 인한 문제를 방지합니다.
     */
    @Override
    @Transactional
    public void updateUserRank(String userId, int newRank) {
        UserDTO user = getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        updateUserRankInternal(user, newRank);
    }
    /**
     * 사용자의 등급을 실제로 업데이트하는 내부 메서드입니다.
     * 이 메서드는 private으로 선언되어 클래스 내부에서만 접근 가능합니다.
     * 주요 역할:
     * 1. 사용자 객체의 등급을 새로운 등급으로 설정합니다.
     * 2. 데이터베이스에 업데이트된 사용자 정보를 저장합니다.
     * 3. 등급 업데이트 결과를 로그로 기록합니다.
     *
     * 이 메서드는 updateUserRank 메서드의 트랜잭션 컨텍스트 내에서 실행되므로,
     * 별도의 @Transactional 어노테이션이 필요하지 않습니다.
     */
    private void updateUserRankInternal(UserDTO user, int newRank) {
        user.setUserRank(newRank);
        userMapper.updateUser(user);
        log.info("User rank updated: userId={}, newRank={}", user.getUserId(), newRank);
    }


}
