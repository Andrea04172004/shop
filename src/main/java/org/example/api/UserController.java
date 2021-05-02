package org.example.api;

import org.dom4j.rule.Mode;
import org.example.domain.user.UserEntity;
import org.example.dto.user.UserDto;

import org.example.dto.user.UserSignupRequest;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/signUp")
    public ModelAndView getSignUp() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("user", new UserDto());
        return modelAndView;
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") @Valid UserDto userDto) {
        userService.signup(userDto);
        return "redirect:login";
    }

    @GetMapping("/getUserByEmail/{email}")
    public UserDto getLoginPage(@PathVariable("email") String email) {
        return userService.findUserByEmail(email);
    }


    @GetMapping ("/profile")
    public ModelAndView getProfilePage (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(authentication.getName());
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", userDto);
        return modelAndView;
    }
}
