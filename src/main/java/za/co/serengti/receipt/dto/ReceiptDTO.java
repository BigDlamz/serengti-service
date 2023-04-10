package za.co.serengti.receipt.dto;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

public record ReceiptDTO(
        Long id,
        String receiptId,
        StoreDTO store,
        POSSystemDTO posSystem,
        CustomerDTO customer,
        LocalDateTime timestamp,
        BigDecimal totalAmountPaid,
        List<ReceiptItemDTO> receiptItems) {}
