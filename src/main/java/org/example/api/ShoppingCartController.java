package org.example.api;

import org.example.services.LineItemService;
import org.example.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping ("/shopCart")
    public ModelAndView getShopCart (){
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        modelAndView.addObject("cart", shoppingCartService.findById(1));
        modelAndView.addObject("totalCartPrice", shoppingCartService.getTotalCartPrice(1));
        return modelAndView;
    }

    @GetMapping ("/deleteLineItem/{shopCartId}/{lineId}")
    public ModelAndView deleteLineItem (@PathVariable ("shopCartId") String shopCartId, @PathVariable ("lineId") String lineId){
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        modelAndView.addObject("cart", shoppingCartService.deleteLineItemFromCart(Integer.parseInt(lineId),Integer.parseInt(shopCartId)));
        modelAndView.addObject("totalCartPrice", shoppingCartService.getTotalCartPrice(Integer.parseInt(shopCartId)));

        return modelAndView;
    }

    @GetMapping ("/changeLineAmount/{shopCartId}/{lineId}/{type}")
    public ModelAndView changeLineAmount (@PathVariable ("shopCartId") String shopCartId,
                                          @PathVariable ("lineId") String lineId,
                                          @PathVariable ("type") String type){
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        modelAndView.addObject("cart", shoppingCartService.updateLineQuantity(Integer.parseInt(shopCartId), Integer.parseInt(lineId), type));
        modelAndView.addObject("totalCartPrice", shoppingCartService.getTotalCartPrice(Integer.parseInt(shopCartId)));

        return modelAndView;
    }
}
