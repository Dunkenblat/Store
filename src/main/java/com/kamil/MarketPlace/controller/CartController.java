package com.kamil.MarketPlace.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.MarketPlace.db.model.Cart;
import com.kamil.MarketPlace.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

	private CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping("")
	public ResponseEntity<Cart> createCart() {
		return new ResponseEntity<>(cartService.createCart(), HttpStatus.CREATED);
	}

	@PutMapping("/{cartId}/products/{productId}/{quantity}")
	public ResponseEntity<Cart> addProduct(@PathVariable(value = "cartId") Long cartId,
			@PathVariable(value = "productId") Long productId, @PathVariable(value = "quantity") int quantity) {
		return new ResponseEntity<>(cartService.addProductToCart(cartId, productId, quantity), HttpStatus.OK);
	}

	@DeleteMapping("/{cartId}/products/{productId}")
	public ResponseEntity<HttpStatus> removeProduct(@PathVariable(value = "cartId") Long cartId,
			@PathVariable(value = "productId") Long productId) {
		cartService.deleteProductFromCart(cartId, productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
