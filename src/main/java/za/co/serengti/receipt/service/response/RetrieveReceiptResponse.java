package za.co.serengti.receipt.service.response;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.dto.ReceiptDetails;

@Data
@Builder
public class RetrieveReceiptResponse {
    private ReceiptDetails receipt;
}
