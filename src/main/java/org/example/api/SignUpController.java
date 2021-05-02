package org.example.api;

import org.example.dto.user.UserDto;
import org.example.dto.user.UserSignupRequest;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class SignUpController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ModelAndView signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", registerUser(userSignupRequest));
        return modelAndView;
    }

    private UserDto registerUser(UserSignupRequest userSignupRequest) {
        UserDto userDto = new UserDto()
                .setEmail(userSignupRequest.getEmail())
                .setPassword(userSignupRequest.getPassword())
                .setFirstName(userSignupRequest.getFirstName())
                .setLastName(userSignupRequest.getLastName());
        return userService.signup(userDto);
    }
}
