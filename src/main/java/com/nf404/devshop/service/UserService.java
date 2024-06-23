package com.nf404.devshop.service;

import com.nf404.devshop.model.UserDTO;
import java.util.List;

public interface UserService {
    /**
     * 모든 사용자 정보를 가져옴
     * 
     * @return 모든 사용자 정보를 담은 UserDTO 리스트
     */
    List<UserDTO> getAllUsers();
    UserDTO getUserById(String userId);
    void registerUser(UserDTO user);
    void updateUser(UserDTO user);
    void deleteUser(String userId);
    UserDTO loginUser(String userId, String userPw);
}
