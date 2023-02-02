package com.kamil.MarketPlace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamil.MarketPlace.db.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
