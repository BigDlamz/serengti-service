package za.co.serengti.receipts;

import lombok.Builder;

@Builder
public record UpdateStatusRequest(Long receiptId, Status status) {
}