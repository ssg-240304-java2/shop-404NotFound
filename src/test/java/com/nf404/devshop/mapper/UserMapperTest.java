package com.nf404.devshop.mapper;

import com.nf404.devshop.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetAllUsers() {
        List<UserDTO> users = userMapper.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());

        // 첫 번째 사용자 정보 출력
        if (!users.isEmpty()) {
            UserDTO firstUser = users.get(0);
            System.out.println("First User: " + firstUser);
        }
    }
}
