package za.co.serengti.receipts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.customers.dto.CustomerDTO;
import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.StoreDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    @JsonIgnore
    private Long id;
    private LocalDateTime timestamp;
    private POSSystemDTO posSystem;
    private StoreDTO store;
    private CustomerDTO customer;
    private List<LineItemDTO> lineItems;
    private BigDecimal totalAmountPaid;
    private Till till;
    private Cashier cashier;
    private TaxInvoice taxInvoice;
    private Marketing marketing;


}

