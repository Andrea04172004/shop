//package org.example.api;
//
//import org.example.dto.ShoppingCartDto;
//import org.example.dto.user.UserDto;
//import org.example.dto.user.UserSignupRequest;
//import org.example.services.UserService;
//import org.example.utils.BusinessMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/api/v1/user")
//public class SignUpController {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private BusinessMapper businessMapper;
//
//    @PostMapping("/signup")
//    public String signup(@ModelAttribute ("user") @Valid UserDto userDto) {
//        userService.signup(userDto);
//        return "redirect:login";
//    }
//}
