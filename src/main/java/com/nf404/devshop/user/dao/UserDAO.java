/**
 * 사용자 데이터 액세스 객체(DAO)를 정의합니다.
 * 이 인터페이스는 사용자 데이터에 대한 CRUD(Create, Read, Update, Delete) 작업을 제공.
 * 사용자 데이터는 UserDTO 객체로 표현됩니다.
 */
package com.nf404.devshop.user.dao;

import com.nf404.devshop.user.model.UserDTO;
import java.util.List;

public interface UserDAO {
    // 모든 사용자 조회
    List<UserDTO> getAllUsers();

    // 사용자 ID로 사용자 조회
    UserDTO getUserById(String userId);

    // 새 사용자 등록
    void insertUser(UserDTO user);

    // 사용자 정보 업데이트
    void updateUser(UserDTO user);

    // 사용자 삭제
    void deleteUser(String userId);

    // 사용자 로그인 (ID와 비밀번호로 사용자 조회)
    UserDTO loginUser(String userId, String userPw);
}
