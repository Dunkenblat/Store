package com.kamil.MarketPlace.db.visitor;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import com.kamil.MarketPlace.db.model.CartItem;

@Component
public interface CartVisitor {
	
	BigDecimal visit(CartItem cartItem);
}
