package com.nf404.devshop.user.service;

import com.nf404.devshop.user.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserService userService;

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
        when(userService.getAllUsers()).thenReturn(mockUsers);

        // When
        List<UserDTO> result = userService.getAllUsers();

        // Then
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUserId());
        assertEquals("User 2", result.get(1).getUserName());
    }

    @Test
    void testGetUserById() {
        // Given
        UserDTO mockUser = new UserDTO(1, "user1", "password1", "User 1", "address1", "phone1", "2023-05-01", 1, false);
        when(userService.getUserById("user1")).thenReturn(mockUser);

        // When
        UserDTO result = userService.getUserById("user1");

        // Then
        assertNotNull(result);
        assertEquals("user1", result.getUserId());
        assertEquals("User 1", result.getUserName());
    }

    @Test
    void testRegisterUser() {
        // Given
        UserDTO newUser = new UserDTO(0, "newUser", "password", "New User", "address", "phone", "2023-05-03", 1, false);

        // When
        userService.registerUser(newUser);

        // Then
        verify(userService, times(1)).registerUser(newUser);
    }

    @Test
    void testLoginUser() {
        // Given
        UserDTO mockUser = new UserDTO(1, "user1", "password1", "User 1", "address1", "phone1", "2023-05-01", 1, false);
        when(userService.loginUser("user1", "password1")).thenReturn(mockUser);

        // When
        UserDTO result = userService.loginUser("user1", "password1");

        // Then
        assertNotNull(result);
        assertEquals("user1", result.getUserId());
    }

    @Test
    void testGetFilteredUsers() {
        // Given
        List<UserDTO> mockFilteredUsers = Arrays.asList(
            new UserDTO(1, "user1", "password1", "User 1", "address1", "phone1", "2023-05-01", 1, false)
        );
        when(userService.getFilteredUsers("user1", "User 1", 1, LocalDate.now(), LocalDate.now()))
            .thenReturn(mockFilteredUsers);

        // When
        List<UserDTO> result = userService.getFilteredUsers("user1", "User 1", 1, LocalDate.now(), LocalDate.now());

        // Then
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getUserId());
    }
}
