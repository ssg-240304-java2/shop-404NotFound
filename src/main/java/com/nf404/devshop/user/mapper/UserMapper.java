package com.nf404.devshop.user.mapper;

import com.nf404.devshop.user.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDTO> getAllUsers();

    /**
     * 사용자 ID로 사용자 정보를 조회
     * 
     * @param userId 사용자 ID
     * @return 사용자 정보
     */
    UserDTO getUserById(@Param("userId") String userId);

    /**
     * 새로운 사용자 정보를 생성
     * 
     * @param user 생성할 사용자 정보
     */
    void insertUser(UserDTO user);

    /**
     * 사용자 정보를 수정
     * 
     * @param user 수정할 사용자 정보
     */
    void updateUser(UserDTO user);

//    /**
//     * 사용자 정보를 삭제
//     *
//     * @param userId 삭제할 사용자 ID
//     */
//    void deleteUser(@Param("userId") String userId);

    /**
     * 사용자 ID와 비밀번호로 사용자 정보를 조회
     * 
     * @param userId 사용자 ID
     * @param userPw 사용자 비밀번호
     * @return 사용자 정보
     */
    UserDTO loginUser(@Param("userId") String userId, @Param("userPw") String userPw);

    List<UserDTO> selectFilteredUsers(Map<String, Object> params);

    void softDeleteUser(String userId);
}
