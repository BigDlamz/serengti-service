package za.co.serengti.receipts;

import jakarta.transaction.Transactional;

import java.util.List;

public interface LineItemService {
    @Transactional
    List<LineItemDTO> save(List<LineItemDTO> lineItems, ReceiptDTO savedReceipt);
}
