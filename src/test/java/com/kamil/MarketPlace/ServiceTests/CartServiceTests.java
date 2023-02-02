package com.kamil.MarketPlace.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kamil.MarketPlace.db.model.Cart;
import com.kamil.MarketPlace.service.CartService;

@WebMvcTest(CartService.class)
@ExtendWith(MockitoExtension.class)
public class CartServiceTests {

	@MockBean
	private CartService cartService;
	
	@Test
	void testFindById() {
		// Given
		Long id = 1L;
		Cart cart = Cart
				.builder()
				.build();
		// When
		when(cartService.findById(id)).thenReturn(Optional.of(cart));
		// Then
        assertEquals(cartService.findById(id), Optional.of(cart));
	}
	
	@Test
	void testFindByIdEmpty() {
		// Given
		Long id = 1L;
		// When
		when(cartService.findById(id)).thenReturn(Optional.empty());
		// Then
        assertEquals(cartService.findById(id), Optional.empty());
        verify(cartService).findById(id);
	}
	
	@Test
	void testSave() {
		// Given
		Cart cart = Cart
				.builder()
				.build();
		// When
		when(cartService.save(cart)).thenReturn(cart);
		// Then
        assertEquals(cartService.save(cart), cart);
        verify(cartService).save(cart);
	}
	
	@Test
	void testCreateCart() {
		// Given
		Cart cart = Cart
				.builder()
				.build();
		// When
		when(cartService.createCart()).thenReturn(cart);
		// Then
        assertEquals(cartService.createCart(), cart);
        verify(cartService).createCart();
	}
	
	@Test
	void testAddProductToCart() {
		// Given
		Long id = 1L;
		Cart cart = Cart
				.builder()
				.build();
		// When
		when(cartService.addProductToCart(id, id, 1)).thenReturn(cart);
		// Then
        assertEquals(cartService.addProductToCart(id, id, 1), cart);
        verify(cartService).addProductToCart(id, id, 1);
	}
	
	@Test
	void testDeleteProductFromCart() {
		cartService.deleteProductFromCart(1L, 1L);
		verify(cartService).deleteProductFromCart(1L, 1L);
	}
	
}
