package za.co.serengti.receipts.service;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipts.domain.Receipt;

@Data
@Builder
public class RetrieveReceiptResponse {
    private Receipt receipt;
}
