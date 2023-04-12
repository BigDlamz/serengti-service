package za.co.serengti.receipt.service;


import za.co.serengti.receipt.service.request.GenerateReceiptRequest;
import za.co.serengti.receipt.service.response.RetrieveReceiptResponse;

public interface ReceiptService {

     void generateReceipt(GenerateReceiptRequest request);
     RetrieveReceiptResponse retrieveReceipt(Long customerID);

}
