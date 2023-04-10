package za.co.serengti.receipt.service;

import za.co.serengti.receipt.EntityRecordMapper;
import za.co.serengti.receipt.dto.ReceiptDTO;
import za.co.serengti.receipt.entity.Receipt;
import za.co.serengti.receipt.repository.ReceiptRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Set;

@ApplicationScoped
public class ReceiptServiceImpl implements ReceiptService {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private EntityRecordMapper entityRecordMapper;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository, EntityRecordMapper entityRecordMapper) {
        this.receiptRepository = receiptRepository;
        this.entityRecordMapper = entityRecordMapper;
    }


    @Override
    public void createReceipt(ReceiptDTO receiptVO) {
        receiptRepository.persist(entityRecordMapper.map(receiptVO, Receipt.class));
    }

    @Override
    public ReceiptDTO retrieveReceipt(Long receiptID) {
        Receipt receipt = receiptRepository.findById(receiptID);
        return entityRecordMapper.map(receipt, ReceiptDTO.class);
    }

    @Override
    public Set<ReceiptDTO> retrieveReceipts(Long customerID, Timestamp fromDate, Timestamp toDate) {
        throw new UnsupportedOperationException();
    }
}
