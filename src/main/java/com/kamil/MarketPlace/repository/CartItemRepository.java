package com.kamil.MarketPlace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamil.MarketPlace.db.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
