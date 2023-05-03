package za.co.serengti.receipts.service;

import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.entity.Receipt;
import za.co.serengti.receipts.repository.ReceiptRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final RecordMapper mapper;

    @Inject
    public ReceiptService(ReceiptRepository receiptRepository, RecordMapper converter) {
        this.receiptRepository = receiptRepository;
        this.mapper = converter;
    }

    @Transactional
    public Long save(ReceiptDTO receiptDTO) {
        Receipt receipt = mapper.convert(receiptDTO, Receipt.class);
        return receiptRepository.save(receipt);
    }

    public ReceiptDTO find(Long ID) {
        Receipt receiptEntity = receiptRepository.findById(ID);
              return mapper.convert(receiptEntity, ReceiptDTO.class);
    }
}
