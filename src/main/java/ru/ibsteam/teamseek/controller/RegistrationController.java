package ru.ibsteam.teamseek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ibsteam.teamseek.domain.User;
import ru.ibsteam.teamseek.service.UserService;
import ru.ibsteam.teamseek.Exception.EmailAlreadyExistException;
import ru.ibsteam.teamseek.Exception.NicknameAlreadyExistException;


@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }


    @GetMapping("/activation/{code}")
    public String activate(Model model, @PathVariable String code) {
        if (userService.activateUser(code)) {
            model.addAttribute("message", "YOUR ACCOUNT WAS ACTIVATED");
            return "login";
        } else {
            model.addAttribute("message", "WRONG ACTIVATION CODE");
            return "login";
        }
    }

    @PostMapping("/registration")
    public String register(User user, Model model) {
        try {
            userService.addUser(user);
        } catch (EmailAlreadyExistException e) {
            model.addAttribute("message", e.getMessage());
            return "registration";
        } catch (NicknameAlreadyExistException e) {
            model.addAttribute("message", e.getMessage());
            return "registration";
        }

        model.addAttribute("message", "check your email: " + user.getEmail() + "");
       // userService.sendActivationMail(user);
        return "login";
    }


}
