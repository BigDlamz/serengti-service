package za.co.serengti.receipt.service.response;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.dto.ReceiptDetailsDTO;

@Data
@Builder
public class RetrieveReceiptResponse {
    private ReceiptDetailsDTO receipt;
}
