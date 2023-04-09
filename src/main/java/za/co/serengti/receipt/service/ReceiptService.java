package za.co.serengti.receipt.service;

import za.co.serengti.receipt.repository.ReceiptRepository;
import za.co.serengti.receipt.vo.ReceiptVO;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Set;


public class ReceiptService implements ReceiptServiceImpl {

    @Inject
    ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public void createReceipt(ReceiptVO receiptVO) {
   // receiptRepository.persist(receiptVO);
    }

    @Override
    public ReceiptVO retrieveReceipt(Long customerID) {
        //TODO receiptRepository.findById(customerID);
        return null;
    }

    @Override
    public Set<ReceiptVO> retrieveReceipts(Long customerID, Timestamp fromDate, Timestamp toDate) {
        return null;
    }
}
