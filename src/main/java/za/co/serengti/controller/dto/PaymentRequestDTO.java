package za.co.serengti.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.merchants.dto.ProductDTO;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequestDTO {

    long posId;
    long storeId;
    private LocalDateTime transactionDate;
    String payingCustomerIdentifier;
    List<ProductDTO> purchasedProducts;

}
