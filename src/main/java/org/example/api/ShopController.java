package org.example.api;

import org.example.dto.user.UserDto;
import org.example.services.ProductService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ShopController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView mainPage() {
        return new ModelAndView("index");
    }

//    @GetMapping("/header")
//    public ModelAndView header() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDto userDto = userService.findUserByEmail(authentication.getName());
//        if (userDto != null){
//            ModelAndView modelAndView = new ModelAndView("header");
//            modelAndView.addObject("user", userDto);
//        }
//        return new ModelAndView("header");
//    }
}
