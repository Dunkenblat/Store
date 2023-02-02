package com.kamil.MarketPlace.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kamil.MarketPlace.db.model.Product;
import com.kamil.MarketPlace.model.ProductDto;
import com.kamil.MarketPlace.service.ProductService;

@WebMvcTest(ProductService.class)
@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
	
	@MockBean
	private ProductService productService;
	
	@Test
	void testExistsByIdFalse() {
		// Given
		Long id = 1L;
		// When
		when(productService.existsById(id)).thenReturn(false);
		// Then
        assertEquals(productService.existsById(id), false);
        verify(productService).existsById(id);
        
	}

	@Test
	void testExistsByIdTrue() {
		// Given
		Long id = 1L;
		// When
		when(productService.existsById(id)).thenReturn(true);
		// Then
        assertEquals(productService.existsById(id), true);
        verify(productService).existsById(id);
	}
	
	@Test
	void testFindAll() {
		// Given
		Product product = Product.builder()
				.name("name")
				.description("description")
				.price(BigDecimal.TEN)
				.availableQuantity(10)
				.build();
		List<Product> products = List.of(product);
		// When
		when(productService.findAll(0)).thenReturn(products);
        assertEquals(1, productService.findAll(0).size());
        assertEquals(false, productService.findAll(0).isEmpty());
        verify(productService, times(2)).findAll(0);
	}
	
	@Test
	void testFindAllEmpty() {
		// Given
		List<Product> products = List.of();
		// When
		when(productService.findAll(0)).thenReturn(products);
        assertEquals(0, productService.findAll(0).size());
        assertEquals(true, productService.findAll(0).isEmpty());
        verify(productService, times(2)).findAll(0);
	}

	@Test
	void testSave() {
		// Given
		Product product = Product.builder()
				.name("name")
				.description("description")
				.price(BigDecimal.TEN)
				.availableQuantity(10)
				.build();
		// When
		when(productService.save(product)).thenReturn(product);
		// Then
        assertEquals(productService.save(product), product);
        verify(productService).save(product);
	}
	
	@Test
	void testCreateProduct() {
		// Given
		Long id = 1L;
		ProductDto product = ProductDto.builder()
				.name("name")
				.description("description")
				.price(BigDecimal.TEN)
				.availableQuantity(10)
				.build();
		Product product2 = Product.builder()
				.id(id)
				.name("name")
				.description("description changed")
				.price(BigDecimal.TEN)
				.availableQuantity(10)
				.build();
		// When
		when(productService.createProduct(product)).thenReturn(product2);
		// Then
        assertEquals(productService.createProduct(product), product2);
        verify(productService).createProduct(product);
	}
	
	@Test
	void testUpdateProduct() {
		// Given
		Long id = 1L;
		ProductDto product = ProductDto.builder()
				.name("name")
				.description("description")
				.price(BigDecimal.TEN)
				.availableQuantity(10)
				.build();
		Product product2 = Product.builder()
				.id(id)
				.name("name")
				.description("description changed")
				.price(BigDecimal.TEN)
				.availableQuantity(10)
				.build();
		// When
		when(productService.updateProduct(id, product)).thenReturn(product2);
		// Then
        assertEquals(productService.updateProduct(id, product), product2);
        verify(productService).updateProduct(id, product);
	}

	@Test
	void testDeleteById() {
		productService.deleteById(1L);
		verify(productService).deleteById(1L);
	}

}
