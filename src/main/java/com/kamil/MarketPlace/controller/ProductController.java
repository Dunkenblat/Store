package com.kamil.MarketPlace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.MarketPlace.db.model.Product;
import com.kamil.MarketPlace.model.ProductDto;
import com.kamil.MarketPlace.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<List<Product>> getAll(@PathVariable int pageNo) {
		return new ResponseEntity<>(productService.findAll(pageNo), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDto productRequest) {
		return new ResponseEntity<>(productService.createProduct(productRequest),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateType(@PathVariable("id") long id, @Valid @RequestBody ProductDto productRequest) {
		if (productService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(productService.updateProduct(id, productRequest), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteType(@PathVariable("id") long id) {
		productService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
