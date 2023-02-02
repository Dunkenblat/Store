package com.kamil.MarketPlace.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartDto {
	
	@NotNull
	private BigDecimal totalPrice;
}
