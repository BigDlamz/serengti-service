package za.co.serengti.receipt.service.response;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.dto.ReceiptDetailsDTO;

import java.util.Set;

@Builder
@Data
public class RetrieveMultipleReceiptsResponse {

    private Set<ReceiptDetailsDTO> receipts;
}
