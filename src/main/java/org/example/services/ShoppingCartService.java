package org.example.services;

import org.example.dto.LineItemDto;
import org.example.dto.ShoppingCartDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {
    public ShoppingCartDto createShopCart(ShoppingCartDto shoppingCartDto);
    public ShoppingCartDto updateShopCart(Integer lineId, ShoppingCartDto shoppingCartDto);
    public ShoppingCartDto findById(Integer shopCartId);
    public void deleteShopCart(Integer shopCartId);
    public List<ShoppingCartDto> findAllShopCarts();
    public ShoppingCartDto deleteLineItemFromCart (Integer lineId, Integer shopCartId);
    public ShoppingCartDto addProductToShopCart (Integer productId, Integer shopCartId);
    public ShoppingCartDto updateLineQuantity (Integer shopCartId,Integer lineId, String type);
    public String getTotalCartPrice (Integer shopCartId);
}
