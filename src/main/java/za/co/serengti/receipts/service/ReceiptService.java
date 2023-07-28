package za.co.serengti.receipts.service;

import lombok.extern.slf4j.Slf4j;
import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.receipts.dto.LineItemDTO;
import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.entity.Receipt;
import za.co.serengti.receipts.repository.LineItemsRepository;
import za.co.serengti.receipts.repository.ReceiptRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class ReceiptService {

    private final LineItemsRepository lineItemRepository;
    private final ReceiptRepository receiptRepository;
    private final RecordMapper mapper;

    @Inject
    public ReceiptService(ReceiptRepository receiptRepository, LineItemsRepository lineItemRepository, RecordMapper converter) {
        this.receiptRepository = receiptRepository;
        this.lineItemRepository = lineItemRepository;
        this.mapper = converter;
    }

    @Transactional
    public Long save(ReceiptDTO receiptDTO) {

        log.info("Saving receipt: {}", receiptDTO);

        Receipt receipt = mapper.convert(receiptDTO, Receipt.class);
        receiptRepository.save(receipt);

        receipt.getLineItems().
                forEach(lineItem -> {
            lineItem.setReceipt(receipt);
            lineItemRepository.persist(lineItem);
        });
        return receiptRepository.save(receipt);
    }

    public ReceiptDTO find(Long ID) {

        log.info("Finding receipt with ID: {}", ID);

        Receipt receipt = receiptRepository.findById(ID);
        return mapper.convert(receipt, ReceiptDTO.class);
    }

    public List<LineItemDTO> createLineItems(List<ProductDTO> products) {
        return products.stream().map(prod ->
                LineItemDTO.builder()
                        .product(prod)
                        .quantity(null)
                        .build()
        ).collect(Collectors.toList());
    }
}
