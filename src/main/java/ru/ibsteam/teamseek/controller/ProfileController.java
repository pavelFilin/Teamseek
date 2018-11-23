package ru.ibsteam.teamseek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ibsteam.teamseek.domain.User;
import ru.ibsteam.teamseek.service.UserService;

@Controller
@RequestMapping("user")
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("person", user);
        return "profile";
    }

    @PostMapping("profile/dashboard")
    public String mutateDashboard(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String password,
            @RequestParam(required = false, defaultValue = "") String name,
            Model model
    ) {
        if (!StringUtils.isEmpty(password)) {
            userService.changePassword(user, password);
        }

        if (!StringUtils.isEmpty(name)) {
            userService.changeName(user, name);
        }

        model.addAttribute("person", user);
        return "profile";
    }
}
