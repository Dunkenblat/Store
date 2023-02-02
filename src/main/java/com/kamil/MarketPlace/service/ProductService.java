package com.kamil.MarketPlace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.MarketPlace.db.model.Product;
import com.kamil.MarketPlace.model.ProductDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface ProductService {

	public boolean existsById(Long id);
	
	public Optional<Product> findById(Long id);
	
	public List<Product> findAll(int pageNo);

	public Product save(Product product);
	
	public Product createProduct(ProductDto productDto);
	
	public Product updateProduct(Long id, ProductDto productDto);
	
	public void deleteById(Long id);
}
