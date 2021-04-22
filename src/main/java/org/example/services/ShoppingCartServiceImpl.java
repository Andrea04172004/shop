package org.example.services;

import org.example.domain.LineItemEntity;
import org.example.domain.ProductEntity;
import org.example.domain.ShoppingCartEntity;
import org.example.dto.LineItemDto;
import org.example.dto.ShoppingCartDto;
import org.example.exeptions.ProductException;
import org.example.repositories.LineItemRepository;
import org.example.repositories.ProductRepository;
import org.example.repositories.ShoppingCartRepository;
import org.example.utils.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private LineItemService lineItemService;
    @Autowired
    private LineItemRepository lineItemRepository;
    @Autowired
    private ProductRepository productRepository;
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
        LineItemEntity lineItemEntity = lineItemRepository.findById(lineId).orElseThrow();

        if (shoppingCartEntity == null) {
            throw new RuntimeException("Cart with such id not found");
        }

        List<LineItemEntity> lineItemEntities = shoppingCartEntity.getLineItemEntities();
        lineItemEntities.remove(lineItemEntity);
        shoppingCartEntity.setLineItemEntities(lineItemEntities);
        shoppingCartRepository.save(shoppingCartEntity);

        lineItemService.deleteLineItem(lineId);

        return businessMapper.convertToCartDto(shoppingCartEntity);
    }

    @Override
    public ShoppingCartDto addProductToShopCart(Integer productId, Integer shopCartId) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findById(shopCartId).orElseThrow();
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow();
        if (productEntity == null) {
            throw new ProductException("Product with id " + productId + "not found");
        }

        List<LineItemEntity> lineItemEntities = shoppingCartEntity.getLineItemEntities();
        for (LineItemEntity line : lineItemEntities) {
            if (line.getProduct().getName().equals(productEntity.getName())) {
                line.setQuantity(line.getQuantity() + 1);
                lineItemRepository.save(line);
            }
        }

        if (!lineItemEntities.contains(lineItemRepository.findByProduct(productEntity))) {
            LineItemEntity lineItemEntity = LineItemEntity.builder()
                    .quantity(1)
                    .product(productEntity).build();
            lineItemRepository.save(lineItemEntity);
            lineItemEntities.add(lineItemEntity);
        }

        shoppingCartEntity.setLineItemEntities(lineItemEntities);
        shoppingCartRepository.save(shoppingCartEntity);
        return businessMapper.convertToCartDto(shoppingCartEntity);
    }

    @Override
    public ShoppingCartDto updateLineQuantity(Integer shopCartId, Integer lineId, String type) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findById(shopCartId).orElseThrow();

        List<LineItemEntity> lineItemEntities = shoppingCartEntity.getLineItemEntities();
        for (LineItemEntity line : lineItemEntities) {
            if (line.getId().equals(lineId)) {
                switch (type) {
                    case "plus": {
                        line.setQuantity(line.getQuantity() + 1);
                        break;
                    }
                    case "minus": {
                        if (line.getQuantity() > 1) {
                            line.setQuantity(line.getQuantity() - 1);
                            break;
                        }
                        break;
                    }
                }
            }
            lineItemRepository.save(line);
        }
        shoppingCartEntity.setLineItemEntities(lineItemEntities);
        shoppingCartRepository.save(shoppingCartEntity);
        return businessMapper.convertToCartDto(shoppingCartEntity);
    }

    @Override
    public String getTotalCartPrice(Integer shopCartId) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findById(shopCartId).orElseThrow();
        List<LineItemEntity> lineItemEntities = shoppingCartEntity.getLineItemEntities();

        double totalSum = 0.0;
        for (LineItemEntity line: lineItemEntities) {
            totalSum = totalSum+(line.getQuantity()*line.getProduct().getPrice());
        }
        DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
        return decimalFormat.format(totalSum);
    }
}
