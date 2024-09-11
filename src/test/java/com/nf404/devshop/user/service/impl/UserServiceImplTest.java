package com.nf404.devshop.user.service.impl;

import com.nf404.devshop.user.mapper.UserMapper;
import com.nf404.devshop.user.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Given
        List<UserDTO> mockUsers = Arrays.asList(
                new UserDTO(1, "user1", "password1", "User 1", "address1", "phone1", "2023-05-01", 1, false),
                new UserDTO(2, "user2", "password2", "User 2", "address2", "phone2", "2023-05-02", 2, false)
        );
        when(userMapper.getAllUsers()).thenReturn(mockUsers);

        // When
        List<UserDTO> result = userService.getAllUsers();

        // Then
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUserId());
        assertEquals("User 2", result.get(1).getUserName());
        verify(userMapper, times(2)).getAllUsers();
    }

    @Test
    void testGetUserById() {
        // Given
        UserDTO mockUser = new UserDTO(1, "user1", "password1", "User 1", "address1", "phone1", "2023-05-01", 1, false);
        when(userMapper.getUserById("user1")).thenReturn(mockUser);

        // When
        UserDTO result = userService.getUserById("user1");

        // Then
        assertNotNull(result);
        assertEquals("user1", result.getUserId());
        assertEquals("User 1", result.getUserName());
        verify(userMapper, times(2)).getUserById("user1");
    }

    @Test
    void testRegisterUser() {
        // Given
        UserDTO newUser = new UserDTO(0, "newUser", "password", "New User", "address", "phone", "2023-05-03", 1, false);

        // When
        userService.registerUser(newUser);

        // Then
        verify(userMapper, times(1)).insertUser(newUser);
        assertEquals(1, newUser.getUserRank()); // Check if default rank is set
    }

    @Test
    void testUpdateUser() {
        // Given
        UserDTO user = new UserDTO(1, "user1", "password1", "Updated User", "new address", "new phone", "2023-05-01", 2, false);

        // When
        userService.updateUser(user);

        // Then
        verify(userMapper, times(1)).updateUser(user);
    }

//    @Test
//    void testDeleteUser() {
//        // Given
//        String userId = "user1";
//
//        // When
//        userService.deleteUser(userId);
//
//        // Then
//        verify(userMapper, times(1)).deleteUser(userId);
//    }

    @Test
    void testSoftDeleteUser() {
        // Given
        String userId = "user1";

        // When
        userService.softDeleteUser(userId);

        // Then
        verify(userMapper, times(1)).softDeleteUser(userId);
    }

    @Test
    void testLoginUser() {
        // Given
        String userId = "user1";
        String userPw = "password1";
        UserDTO mockUser = new UserDTO(1, userId, userPw, "User 1", "address1", "phone1", "2023-05-01", 1, false);
        when(userMapper.loginUser(userId, userPw)).thenReturn(mockUser);

        // When
        UserDTO result = userService.loginUser(userId, userPw);

        // Then
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        verify(userMapper, times(2)).loginUser(userId, userPw);
    }

    @Test
    void testGetFilteredUsers() {
        // Given
        String userId = "user1";
        String userName = "User 1";
        Integer userRank = 1;
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 31);

        List<UserDTO> mockFilteredUsers = Arrays.asList(
                new UserDTO(1, userId, "password1", userName, "address1", "phone1", "2023-05-01", userRank, false)
        );
        when(userMapper.selectFilteredUsers(any(Map.class))).thenReturn(mockFilteredUsers);

        // When
        List<UserDTO> result = userService.getFilteredUsers(userId, userName, userRank, startDate, endDate);

        // Then
        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getUserId());
        verify(userMapper, times(1)).selectFilteredUsers(any(Map.class));
    }

    @Test
    void testUpdateUserRank() {
        // Given
        String userId = "user1";
        int newRank = 2;
        UserDTO mockUser = new UserDTO(1, userId, "password1", "User 1", "address1", "phone1", "2023-05-01", 1, false);
        when(userMapper.getUserById(userId)).thenReturn(mockUser);

        // When
        userService.updateUserRank(userId, newRank);

        // Then
        verify(userMapper, times(2)).getUserById(userId);
        verify(userMapper, times(1)).updateUser(any(UserDTO.class));
        assertEquals(newRank, mockUser.getUserRank());
    }
}
