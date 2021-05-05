package org.example.api;

import org.example.dto.ShoppingCartDto;
import org.example.dto.user.UserDto;
import org.example.services.LineItemService;
import org.example.services.ShoppingCartService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private LineItemService lineItemService;
    @Autowired
    private UserService userService;

    @GetMapping ("/shopCart")
    public ModelAndView getShopCart (){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());

        ShoppingCartDto shoppingCartDto = userDto.getShoppingCartDto();
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        modelAndView.addObject("cart", shoppingCartDto);
        modelAndView.addObject("totalCartPrice", shoppingCartService.getTotalCartPrice(shoppingCartDto.getId()));
        modelAndView.addObject("cartPrice", shoppingCartService.getTotalCartPrice(shoppingCartDto.getId()));
        modelAndView.addObject("cartAmount", shoppingCartDto.getLineItemDto().size());
        return modelAndView;
    }

    @GetMapping ("/deleteLineItem/{shopCartId}/{lineId}")
    public String deleteLineItem (@PathVariable ("shopCartId") String shopCartId, @PathVariable ("lineId") String lineId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        ShoppingCartDto shoppingCartDto = userDto.getShoppingCartDto();

        shoppingCartService.deleteLineItemFromCart(Integer.parseInt(lineId),Integer.parseInt(shopCartId));
        return "redirect:/shopCart";
    }

    @GetMapping ("/changeLineAmount/{lineId}/{type}")
    public String changeLineAmount (@PathVariable ("lineId") String lineId,
                                          @PathVariable ("type") String type){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        ShoppingCartDto shoppingCartDto = userDto.getShoppingCartDto();

        shoppingCartService.updateLineQuantity(shoppingCartDto.getId(), Integer.parseInt(lineId), type);
        return "redirect:/shopCart";
    }
}
