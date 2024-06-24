package com.nf404.devshop.controller;

import com.nf404.devshop.model.UserDTO;
import com.nf404.devshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 관리를 위한 컨트롤러 클래스.
 * 사용자 목록 조회, 등록, 수정, 삭제 등의 기능을 제공
 * 또한 사용자 로그인 기능도 포함하고 있따.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Integer userRank,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {

        List<UserDTO> users;
        try {
            if (userId != null || userName != null || userRank != null || startDate != null || endDate != null) {
                // 검색 조건이 있는 경우
                users = userService.getFilteredUsers(userId, userName, userRank, startDate, endDate);
            } else {
                // 검색 조건이 없는 경우 (기존 로직)
                users = userService.getAllUsers();
            }

            if (users == null) {
                users = new ArrayList<>(); // null 대신 빈 리스트 사용
            }
            log.info("Retrieved users count: {}", users.size());
        } catch (Exception e) {
            log.error("Error fetching users: ", e);
            users = new ArrayList<>();
            model.addAttribute("errorMessage", "사용자 정보를 가져오는 중 오류가 발생했습니다.");
        }

        log.info("Users list size: {}", users.size());
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO user) {
        userService.registerUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/edit/{userId}")
    public String showEditForm(@PathVariable String userId, Model model) {
        log.info("Attempting to edit user with ID: {}", userId);
        UserDTO user = userService.getUserById(userId);
        if (user == null) {
            log.warn("User not found with ID: {}", userId);
            return "redirect:/users/list";
        }
        log.info("User found: {}", user);  // 추가된 로그
        model.addAttribute("user", user);
        return "user/edit";
    }


    @PostMapping("/edit")
    public String updateUser(@ModelAttribute UserDTO updatedUser, RedirectAttributes redirectAttributes) {
        log.info("Updating user: {}", updatedUser);
        try {
            // 기존 사용자 정보를 가져옵니다.
            UserDTO existingUser = userService.getUserById(updatedUser.getUserId());
            if (existingUser == null) {
                throw new IllegalArgumentException("User not found");
            }

            // 랭크를 제외한 다른 필드들만 업데이트합니다.
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setUserPw(updatedUser.getUserPw());
            existingUser.setUserAddr(updatedUser.getUserAddr());
            existingUser.setUserPhone(updatedUser.getUserPhone());
            // userRank는 업데이트하지 않습니다.

            userService.updateUser(existingUser);
            redirectAttributes.addFlashAttribute("successMessage", "사용자 정보가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            log.error("Error updating user: ", e);
            redirectAttributes.addFlashAttribute("errorMessage", "사용자 정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/users/list";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "redirect:/users/list";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String userId, @RequestParam String userPw, Model model) {
        UserDTO user = userService.loginUser(userId, userPw);
        if (user != null) {
            // 로그인 성공 로직
            return "redirect:/users/list";
        } else {
            // 로그인 실패 로직
            model.addAttribute("error", "Invalid username or password");
            return "user/login";
        }
    }
}
