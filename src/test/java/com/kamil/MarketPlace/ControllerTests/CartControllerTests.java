package com.kamil.MarketPlace.ControllerTests;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamil.MarketPlace.controller.CartController;
import com.kamil.MarketPlace.db.model.Cart;
import com.kamil.MarketPlace.service.CartService;

@WebMvcTest(CartController.class)
@ExtendWith(MockitoExtension.class)
public class CartControllerTests {

	@MockBean
	private CartService cartService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private WebApplicationContext context;
    
    @BeforeEach
    public void setup() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
	@Test
	void testCreateCart() throws Exception {
		// given
		long id = 1L;
		Cart cart = Cart
				.builder()
				.build();
		// when
		when(cartService.save(cart)).thenReturn(cart);
		// then
		mockMvc.perform(post("/api/carts", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cart)))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
	@Test
	void testAddProductToCart() throws Exception {
		// given
		long id = 1L;
		Cart cart = Cart
				.builder()
				.build();
		// when
		when(cartService.addProductToCart(id, id, 1)).thenReturn(cart);
		// then
		mockMvc.perform(put("/api/carts/{cartId}/products/{productId}/{quantity}", id, id, 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cart)))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	void testDeleteProductFromCart() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(cartService).deleteProductFromCart(id, id);
		// then
		mockMvc.perform(delete("/api/carts/{id}/products/{id}", id, id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}
	
}
