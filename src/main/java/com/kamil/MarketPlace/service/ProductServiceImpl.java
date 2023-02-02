package com.kamil.MarketPlace.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.kamil.MarketPlace.db.model.Product;
import com.kamil.MarketPlace.exception.ResourceNotFoundException;
import com.kamil.MarketPlace.model.ProductDto;
import com.kamil.MarketPlace.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public boolean existsById(Long id) {
		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Product with id = " + id);
		}
		return productRepository.existsById(id);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> findAll(int pageNo) {
        return productRepository.findAll(PageRequest.of(pageNo, 3)).toList();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product createProduct(ProductDto productDto) {
		return save(Product.builder()
			.name(productDto.getName())
			.description(productDto.getDescription())
			.price(productDto.getPrice())
			.availableQuantity(productDto.getAvailableQuantity())
			.build());
	}

	@Override
	public Product updateProduct(Long id, ProductDto productDto) {
		Product product = findById(id).get();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setAvailableQuantity(productDto.getAvailableQuantity());
		save(product);
		return product;
	}
	
	@Override
	public void deleteById(Long id) {
		existsById(id);
		productRepository.deleteById(id);
	}
	
}
