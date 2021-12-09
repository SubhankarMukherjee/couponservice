package com.connecttosubh.web;

import com.connecttosubh.model.User;
import com.connecttosubh.repository.UserRepo;
import com.connecttosubh.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private SecurityService service;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/showReg")
    public String showRegistrationPage() {
        return "registerUser";
    }
    @PostMapping("/registerUser")
    public String registration(User user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "/login";
    }
    @PostMapping("/login")
    public String login(String email, String password) {
        boolean result = service.login(email, password);
        if (result) {
            return "index";
        }

        return "login";

    }

}
