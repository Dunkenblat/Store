package com.kamil.MarketPlace.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.kamil.MarketPlace.db.model.Cart;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface CartService {

	public Optional<Cart> findById(Long id);

	public Cart save(Cart cart);
	
	public Cart createCart();
	
	public Cart addProductToCart(Long cartId, Long productId, int quantity);
	
	public void deleteProductFromCart(Long cartId, Long productId);
}
