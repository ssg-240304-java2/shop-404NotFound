package com.nf404.devshop.user.controller;

import com.nf404.devshop.user.service.UserService;
import com.nf404.devshop.user.model.UserDTO;
import jakarta.servlet.http.HttpSession;
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
import java.util.stream.Collectors;

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

    // 이하 템플릿에 적용될 객체들

    @GetMapping("/list")
    public String listUsers(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Integer userRank,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            Model model) {

        List<UserDTO> users = userService.getFilteredUsers(userId, userName, userRank, startDate, endDate);
        users = users.stream().filter(user -> !user.isDeleted()).collect(Collectors.toList());
        model.addAttribute("users", users);
        return "user/user_list";
    }



//      이전 기능
//    @GetMapping("/list")
//    public String listUsers(
//            @RequestParam(required = false) String userId,
//            @RequestParam(required = false) String userName,
//            @RequestParam(required = false) Integer userRank,
//            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
//            Model model) {
//        log.info("Search parameters - userId: {}, userName: {}, userRank: {}, startDate: {}, endDate: {}",
//                userId, userName, userRank, startDate, endDate);
//
//        List<UserDTO> users;
//        try {
//            if (userId != null || userName != null || userRank != null || startDate != null || endDate != null) {
//                // 검색 조건이 있는 경우
//                users = userService.getFilteredUsers(userId, userName, userRank, startDate, endDate);
//            } else {
//                // 검색 조건이 없는 경우 (기존 로직)
//                users = userService.getAllUsers();
//            }
//
//            if (users == null) {
//                users = new ArrayList<>(); // null 대신 빈 리스트 사용
//            }
//            log.info("Retrieved users count: {}", users.size());
//        } catch (Exception e) {
//            log.error("Error fetching users: ", e);
//            users = new ArrayList<>();
//            model.addAttribute("errorMessage", "사용자 정보를 가져오는 중 오류가 발생했습니다.");
//        }
//
//        log.info("Users list size: {}", users.size());
//        model.addAttribute("users", users);
//        return "user/user_list";
//    }

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
        try {
            UserDTO existingUser = userService.getUserById(updatedUser.getUserId());
            if (existingUser == null) {
                throw new IllegalArgumentException("User not found");
            }

            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setUserPw(updatedUser.getUserPw());
            existingUser.setUserAddr(updatedUser.getUserAddr());
            existingUser.setUserPhone(updatedUser.getUserPhone());

            userService.updateUser(existingUser);
            redirectAttributes.addFlashAttribute("successMessage", "사용자 정보가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "사용자 정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/users/list";
    }

    // get 메서드 삭제
    // @GetMapping("/delete/{userId}")
    // public String deleteUser(@PathVariable String userId) {
    //     userService.deleteUser(userId);
    //     return "redirect:/users/list";
    // }

    // POST 메서드로 변경
    @PostMapping("/delete")
    public String softDeleteUser(@RequestParam String userId, RedirectAttributes redirectAttributes) {
        try {
            userService.softDeleteUser(userId);
            redirectAttributes.addFlashAttribute("successMessage", "사용자가 성공적으로 비활성화되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "사용자 비활성화 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/users/list";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String userId, @RequestParam String userPw, HttpSession session, Model model) {
        try {
            UserDTO user = userService.loginUser(userId, userPw);
            if (user != null) {
                // 로그인 성공 로직
                session.setAttribute("loggedInUser", user);
                return "redirect:/users/list";
            } else {
                // 로그인 실패 로직
                model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
                return "user/login";
            }
        } catch (Exception e) {
            // 예외 처리
            model.addAttribute("error", "로그인 처리 중 오류가 발생했습니다.");
            return "user/login";
        }
    }

    // 회원 등급 수정 메서드
    @PostMapping("/updateRank")
    public String updateUserRank(@RequestParam String userId, @RequestParam int newRank, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUserRank(userId, newRank);
            redirectAttributes.addFlashAttribute("successMessage", "회원 등급이 성공적으로 변경되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "회원 등급 변경 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/users/list";
    }

}
