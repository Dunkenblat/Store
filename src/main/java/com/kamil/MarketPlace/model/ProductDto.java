package com.kamil.MarketPlace.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDto {

	@JsonProperty("name")
	@NotNull(message = "name is required.")
	@Size(min = 3, max = 20, message = "Name should have min 3 and max 20 characters.")
	private String name;
	
	@JsonProperty("description")
	@NotNull(message = "description is required.")
	@Size(min = 3, max = 100, message = "Name should have min 3 and max 100 characters.")
	private String description;

	@JsonProperty("price")
	@NotNull(message = "price is required.")
	private BigDecimal price;
	
	@JsonProperty("available_quantity")
	@NotNull(message = "quantity is required.")
	private Integer availableQuantity;
}
