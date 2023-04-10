package za.co.serengti.receipt.service;


import za.co.serengti.receipt.dto.ReceiptDTO;

import java.sql.Timestamp;
import java.util.Set;

public interface ReceiptService {

     void createReceipt(ReceiptDTO receiptVO);
     ReceiptDTO retrieveReceipt(Long customerID);
     Set<ReceiptDTO> retrieveReceipts(Long customerID, Timestamp fromDate, Timestamp toDate);

}
