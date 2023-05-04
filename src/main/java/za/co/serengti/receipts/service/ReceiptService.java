package za.co.serengti.receipts.service;

import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.service.ProductService;
import za.co.serengti.receipts.dto.LineItemDTO;
import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.entity.LineItem;
import za.co.serengti.receipts.entity.Receipt;
import za.co.serengti.receipts.repository.ReceiptRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReceiptService {

    private final LineItemRepository lineItemRepository;
    private final ReceiptRepository receiptRepository;
    private final ProductService productService;
    private final RecordMapper mapper;

    @Inject
    public ReceiptService(ReceiptRepository receiptRepository, ProductService productService, LineItemRepository lineItemRepository, RecordMapper converter) {
        this.receiptRepository = receiptRepository;
        this.productService = productService;
        this.lineItemRepository = lineItemRepository;
        this.mapper = converter;
    }

    @Transactional
    public Long save(ReceiptDTO receiptDTO) {

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
        Receipt receiptEntity = receiptRepository.findById(ID);
        return mapper.convert(receiptEntity, ReceiptDTO.class);
    }

    public List<LineItemDTO> createLineItemsFromProducts(List<ProductDTO> products) {
        return products.stream().map(productDTO ->
                LineItemDTO.builder()
                        .product(productDTO)
                        .quantity(productDTO.getQuantity()) // Set default quantity to 1 or any desired value
                        .build()
        ).collect(Collectors.toList());
    }
}
