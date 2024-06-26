package com.nf404.devshop.user.mapper;

import com.nf404.devshop.user.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Sql("/test-data.sql")
    void testGetAllUsers() {
        List<UserDTO> users = userMapper.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    @Sql("/test-data.sql")
    void testGetUserById() {
        UserDTO user = userMapper.getUserById("user1");
        assertNotNull(user);
        assertEquals("user1", user.getUserId());
    }

    @Test
    void testInsertUser() {
        UserDTO newUser = new UserDTO(0, "newUser", "password", "New User", "address", "phone", "2023-05-03", 1, false);
        userMapper.insertUser(newUser);

        UserDTO insertedUser = userMapper.getUserById("newUser");
        assertNotNull(insertedUser);
        assertEquals("New User", insertedUser.getUserName());
    }

    @Test
    @Sql("/test-data.sql")
    void testUpdateUser() {
        UserDTO user = userMapper.getUserById("user1");
        user.setUserName("Updated Name");
        userMapper.updateUser(user);

        UserDTO updatedUser = userMapper.getUserById("user1");
        assertEquals("Updated Name", updatedUser.getUserName());
    }

    @Test
    @Sql("/test-data.sql")
    void testLoginUser() {
        UserDTO user = userMapper.loginUser("user1", "password1");
        assertNotNull(user);
        assertEquals("user1", user.getUserId());
    }

    @Test
    @Sql("/test-data.sql")
    void testSelectFilteredUsers() {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", "User");
        List<UserDTO> filteredUsers = userMapper.selectFilteredUsers(params);
        assertFalse(filteredUsers.isEmpty());
        assertTrue(filteredUsers.stream().allMatch(user -> user.getUserName().contains("User")));
    }

    @Test
    @Sql("/test-data.sql")
    void testSoftDeleteUser() {
        userMapper.softDeleteUser("user1");
        UserDTO softDeletedUser = userMapper.getUserById("user1");
        assertTrue(softDeletedUser.isDeleted());
    }
}
