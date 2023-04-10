package za.co.serengti.receipt.dto;

import java.math.BigDecimal;

public record ProductDTO(Long id, String name, String sku, BigDecimal price, Integer quantity) {}
