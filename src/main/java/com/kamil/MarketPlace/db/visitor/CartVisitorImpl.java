package com.kamil.MarketPlace.db.visitor;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.kamil.MarketPlace.db.model.CartItem;

@Component
public class CartVisitorImpl implements CartVisitor{
	
	@Override
    public BigDecimal visit(CartItem cartItem) {
        BigDecimal price = BigDecimal.ZERO;
        if (cartItem.getQuantityInCart() > 5) {
            price = cartItem.getPrice().subtract(BigDecimal.TEN);
        } else {
        	price = cartItem.getPrice();
        }
        return price;
    }
	
}
