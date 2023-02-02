package com.kamil.MarketPlace.ControllerTests;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

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
import com.kamil.MarketPlace.controller.ProductController;
import com.kamil.MarketPlace.db.model.Product;
import com.kamil.MarketPlace.model.ProductDto;
import com.kamil.MarketPlace.service.ProductService;

@WebMvcTest(ProductController.class)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {
	
	@MockBean
	private ProductService productService;
	
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
		Product product = Product.builder()
				.name("name")
				.description("description")
				.price(BigDecimal.TEN)
				.availableQuantity(10)
				.build();
		// when
		when(productService.save(product)).thenReturn(product);
		// then
		mockMvc.perform(post("/api/products", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
	@Test
	void testUpdateProduct() throws Exception {
		// given
		long id = 1L;
		ProductDto productDto = ProductDto.builder()
				.name("name")
				.description("description")
				.price(BigDecimal.TEN)
				.availableQuantity(10)
				.build();
		Product product = Product.builder()
				.name("name")
				.description("description")
				.price(BigDecimal.TEN)
				.availableQuantity(10)
				.build();
		// when
		when(productService.updateProduct(id, productDto)).thenReturn(product);
		// then
		mockMvc.perform(put("/api/products/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	void testGetAll() throws Exception {
		// given
		Product product = Product.builder()
				.name("name")
				.description("description")
				.price(BigDecimal.TEN)
				.availableQuantity(10)
				.build();
		List<Product> products = List.of(product);
		// when
		when(productService.findAll(0)).thenReturn(products);
		// then
		mockMvc.perform(get("/api/products"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(products.size()))
				.andDo(print());
	}
	
	@Test
	void testFindAllNotFound() throws Exception {
		// given
		List<Product> products = List.of();
		// when
		when(productService.findAll(0)).thenReturn(products);
		// then
		mockMvc.perform(get("/api/products"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}
	
	@Test
	void testDeleteProduct() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(productService).deleteById(id);
		// then
		mockMvc.perform(delete("/api/products/{id}", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

}
