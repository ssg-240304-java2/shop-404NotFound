package com.nf404.devshop.controller;

import com.nf404.devshop.model.UserDTO;
import com.nf404.devshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 관리를 위한 컨트롤러 클래스.
 * 사용자 목록 조회, 등록, 수정, 삭제 등의 기능을 제공
 * 또한 사용자 로그인 기능도 포함하고 있따.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
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
        UserDTO user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute UserDTO user) {
        userService.updateUser(user);
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
