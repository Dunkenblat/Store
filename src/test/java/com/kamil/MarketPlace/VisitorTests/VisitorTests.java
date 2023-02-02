package com.kamil.MarketPlace.VisitorTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.kamil.MarketPlace.db.model.CartItem;
import com.kamil.MarketPlace.db.visitor.CartVisitor;

@WebMvcTest(CartVisitor.class)
public class VisitorTests {
	
	@MockBean
	private CartVisitor cartVisitor;
	
	@Test
	void testVisitor() {
		// Given
		CartItem cartItem = CartItem.builder()
				.name("name")
				.description("description")
				.price(BigDecimal.valueOf(1000))
				.quantityInCart(10)
				.build();
		// When
		when(cartItem.accept(cartVisitor)).thenReturn(BigDecimal.valueOf(990));
		// Then
        assertEquals(cartItem.accept(cartVisitor),BigDecimal.valueOf(990));
	}
	
}
