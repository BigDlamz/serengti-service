package za.co.serengti.receipts.service;

import lombok.extern.slf4j.Slf4j;
import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.receipts.dto.CashierDTO;
import za.co.serengti.receipts.dto.LineItemDTO;
import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.entity.Cashier;
import za.co.serengti.receipts.entity.Receipt;
import za.co.serengti.receipts.entity.Till;
import za.co.serengti.receipts.repository.*;
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
    private final TillRepository tillRepository;
    private final CashierRepository cashierRepository;
    private final PromotionsRepository promotionsRepository;
    private final RecordMapper mapper;

    @Inject
    public ReceiptService(ReceiptRepository receiptRepository, LineItemsRepository lineItemRepository, TillRepository tillRepository, CashierRepository cashierRepository, PromotionsRepository promotionsRepository, RecordMapper converter) {
        this.receiptRepository = receiptRepository;
        this.lineItemRepository = lineItemRepository;
        this.tillRepository = tillRepository;
        this.cashierRepository = cashierRepository;
        this.promotionsRepository = promotionsRepository;
        this.mapper = converter;
    }

    public ReceiptDTO find(Long ID) {
        log.info("Finding receipt with ID: {}", ID);
        Receipt receipt = receiptRepository.findById(ID);
        return mapper.convert(receipt, ReceiptDTO.class);
    }

    @Transactional
    public Long save(ReceiptDTO receiptDTO) {

        log.info("Saving receipt: {}", receiptDTO);
        Receipt receipt = mapper.convert(receiptDTO, Receipt.class);

        receiptRepository.save(receipt);

        Cashier savedCashier = cashierRepository.save(receipt.getCashier());

        Till till = receipt.getTill();
        till.setPosSystem(receipt.getPosSystem());
        till.setStore(receipt.getStore());

        Till savedTill = tillRepository.save(till);

        tillRepository.persist(receipt.getTill());

        promotionsRepository.persist(receipt.getPromotions());

        receipt.getPurchasedItems().
                forEach(lineItem -> {
            lineItem.setReceipt(receipt);
            lineItemRepository.persist(lineItem);
        });

        return receiptRepository.save(receipt);
    }

    public List<LineItemDTO> createLineItems(List<ProductDTO> products) {
        return products.stream().map(prod ->
                LineItemDTO.builder()
                        .product(prod)
                        .quantity(prod.getQuantity())
                        .build()
        ).collect(Collectors.toList());
    }
}
