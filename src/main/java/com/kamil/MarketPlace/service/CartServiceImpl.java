package com.kamil.MarketPlace.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.MarketPlace.db.model.Cart;
import com.kamil.MarketPlace.db.model.CartItem;
import com.kamil.MarketPlace.db.model.Product;
import com.kamil.MarketPlace.db.visitor.CartVisitor;
import com.kamil.MarketPlace.exception.BadRequestException;
import com.kamil.MarketPlace.repository.CartItemRepository;
import com.kamil.MarketPlace.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{

	private CartRepository cartRepository;
	
	private CartItemRepository cartItemRepository;
	
	private ProductService productService;
	
	private CartVisitor visitor;
	
	public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, 
			ProductService productService, CartVisitor visitor) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.productService = productService;
		this.visitor = visitor;
	}
	
	@Override
	public Optional<Cart> findById(Long id) {
		return cartRepository.findById(id);
	}

	@Override
	public Cart save(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public Cart createCart() {
		return save(Cart.builder()
				.totalPrice(BigDecimal.ZERO)
				.build());
	}

	@Override
	public Cart addProductToCart(Long cartId, Long productId, int quantity) {
		Cart cart = findById(cartId).get();
		Product product = productService.findById(productId).get();
		
		if(cart.getCartItems().size() >= 3) {
			throw new BadRequestException("You cannot put more than 3 products in your shopping cart");
		}
		if(product.getAvailableQuantity() < quantity ) {
			throw new BadRequestException("the number of available products is less than " + quantity);
		}
		
		product.setAvailableQuantity(product.getAvailableQuantity() - quantity);
		CartItem item = cartItemRepository.save(CartItem.builder()
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
				.quantityInCart(quantity)
				.cart(cart)
				.build());
		
		List<CartItem> items = cart.getCartItems();
		items.add(item);
		cart.setCartItems(items);
		
		BigDecimal priceOfCart = cart.getCartItems().stream()
				.map(cartItem -> cartItem.accept(visitor)).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		
		cart.setTotalPrice(priceOfCart);
		save(cart);
		
		return cart;
	}
	
	@Override
	public void deleteProductFromCart(Long cartId, Long itemId) {
		Cart cart = findById(cartId).get();
		CartItem cartItem = cart.getCartItems().stream().filter(t -> t.getId() == itemId).findFirst().orElse(null);
		if (cartItem != null) {
			cart.getCartItems().remove(cartItem);
			cartItemRepository.deleteById(itemId);

		}
		save(cart);
	}

}
