package com.nf404.devshop.user.service;

import com.nf404.devshop.user.model.UserDTO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 관리 서비스 인터페이스
 * 
 * 사용자 정보 조회, 등록, 수정, 삭제 등의 기능을 제공합니다.
 * 사용자 로그인 기능도 포함됩니다.
 * 사용자 정보 필터링 기능도 제공합니다.
 */
public interface UserService {
    /**
     * 모든 사용자 정보를 가져옴
     * 
     * @return 모든 사용자 정보를 담은 UserDTO 리스트
     */
    @Transactional(readOnly = true)
    List<UserDTO> getAllUsers();

    /**
     * 사용자 ID로 특정 사용자 정보를 조회
     * 
     * @param userId 조회할 사용자의 ID
     * @return 조회된 사용자 정보
     */
    @Transactional(readOnly = true)
    UserDTO getUserById(String userId);

    /**
     * 새로운 사용자를 등록
     * 
     * @param user 등록할 사용자 정보
     */
    @Transactional
    void registerUser(UserDTO user);

    /**
     * 기존 사용자 정보를 업데이트
     * 
     * @param user 업데이트할 사용자 정보
     */
    @Transactional
    void updateUser(UserDTO user);

    /**
     * 특정 사용자를 삭제
     * 
     * @param userId 삭제할 사용자의 ID
     */
    @Transactional
    void deleteUser(String userId);

    /**
     * 사용자 로그인 처리
     * 
     * @param userId 로그인할 사용자의 ID
     * @param userPw 로그인할 사용자의 비밀번호
     * @return 로그인된 사용자 정보
     */

    void softDeleteUser(String userId);
    UserDTO loginUser(String userId, String userPw);

    /**
     * 조건에 맞는 사용자 목록을 필터링하여 조회
     * 
     * @param userId    사용자 ID 필터
     * @param userName  사용자 이름 필터
     * @param userRank  사용자 등급 필터
     * @param startDate 시작 날짜 필터
     * @param endDate   종료 날짜 필터
     * @return 필터링된 사용자 목록
     */
    List<UserDTO> getFilteredUsers(String userId, String userName, Integer userRank, LocalDate startDate,
            LocalDate endDate);

    void updateUserRank(String userId, int newRank);
}

