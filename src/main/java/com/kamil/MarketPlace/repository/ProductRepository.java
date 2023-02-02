package com.kamil.MarketPlace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamil.MarketPlace.db.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
