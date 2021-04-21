package org.example.services;

import org.example.domain.LineItemEntity;
import org.example.domain.ShoppingCartEntity;
import org.example.dto.LineItemDto;
import org.example.dto.ShoppingCartDto;
import org.example.repositories.ShoppingCartRepository;
import org.example.utils.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private LineItemService lineItemService;
    @Autowired
    private BusinessMapper businessMapper;
    @Override
    public ShoppingCartDto createShopCart(ShoppingCartDto shoppingCartDto) {
        ShoppingCartEntity shoppingCartEntity = businessMapper.convertToCartEntity(shoppingCartDto);
        shoppingCartRepository.save(shoppingCartEntity);
        return businessMapper.convertToCartDto(shoppingCartEntity);
    }

    @Override
    public ShoppingCartDto updateShopCart(Integer lineId, ShoppingCartDto shoppingCartDto) {
        return null;
    }

    @Override
    public ShoppingCartDto findById(Integer shopCartId) {
        return businessMapper.convertToCartDto(shoppingCartRepository.findById(shopCartId).orElseThrow());
    }

    @Override
    public void deleteShopCart(Integer shopCartId) {
        shoppingCartRepository.deleteById(shopCartId);
    }

    @Override
    public List<ShoppingCartDto> findAllShopCarts() {
        return businessMapper.convertCollectionToListGen(shoppingCartRepository.findAll(), businessMapper.toCartDto);
    }

    @Override
    public ShoppingCartDto deleteLineItemFromCart(Integer lineId, Integer shopCartId) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findById(shopCartId).orElseThrow();

        if (shoppingCartEntity == null ){
            throw new RuntimeException("Cart with such id not found");
        }

        List<LineItemEntity> lineItemEntities = shoppingCartEntity.getLineItemEntities();
        lineItemEntities.remove(businessMapper.convertToLineItemEntity(lineItemService.findById(lineId)));
        shoppingCartEntity.setLineItemEntities(lineItemEntities);
        shoppingCartRepository.save(shoppingCartEntity);
        lineItemService.deleteLineItem(lineId);
        return businessMapper.convertToCartDto(shoppingCartEntity);
    }
}
