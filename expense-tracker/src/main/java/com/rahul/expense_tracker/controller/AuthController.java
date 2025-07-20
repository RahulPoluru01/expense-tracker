package com.rahul.expense_tracker.controller;

import com.rahul.expense_tracker.model.User;
import com.rahul.expense_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        User user=userRepo.findByUsername(username);
        if (user!=null) {
            return "redirect:/signup?error"; // handle error properly
        }

        User user1= new User();
        user1.setUsername(username);
        user1.setPassword(password);
      //  user1.setRole("ROLE_USER");
        userRepo.save(user1);

        return "redirect:/login?registered";
    }
}
