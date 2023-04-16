package za.co.serengti.receipt.service.response;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.dto.ReceiptDetails;

import java.util.Set;

@Builder
@Data
public class RetrieveMultipleReceiptsResponse {

    private Set<ReceiptDetails> receipts;
}
