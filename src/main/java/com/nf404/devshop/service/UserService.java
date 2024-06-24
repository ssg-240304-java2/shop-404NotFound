package com.nf404.devshop.service;

import com.nf404.devshop.model.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(String userId);
    void registerUser(UserDTO user);
    void updateUser(UserDTO user);
    void deleteUser(String userId);
    UserDTO loginUser(String userId, String userPw);
}
