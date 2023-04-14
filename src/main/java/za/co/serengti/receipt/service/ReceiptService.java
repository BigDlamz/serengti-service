package za.co.serengti.receipt.service;


import za.co.serengti.receipt.service.request.ReceiptRequest;
import za.co.serengti.receipt.service.response.RetrieveReceiptResponse;

public interface ReceiptService {

     void save(ReceiptRequest request);
     RetrieveReceiptResponse retrieveReceipt(Long customerID);

}
