package za.co.serengti.receipt.dto;

public record ReceiptItemDTO(Long id, Long receiptId, ProductDTO product, Integer quantity) {}
