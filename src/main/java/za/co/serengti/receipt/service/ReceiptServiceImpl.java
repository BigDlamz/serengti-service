package za.co.serengti.receipt.service;


import za.co.serengti.receipt.vo.ReceiptVO;

import java.sql.Timestamp;
import java.util.Set;

public interface ReceiptServiceImpl {

     void createReceipt(ReceiptVO receiptVO);
     ReceiptVO retrieveReceipt(Long customerID);
     Set<ReceiptVO> retrieveReceipts(Long customerID, Timestamp fromDate, Timestamp toDate);

}
