package za.co.serengti.receipt.service;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.domain.Receipt;

@Data
@Builder
public class RetrieveReceiptResponse {
    private Receipt receipt;
}
