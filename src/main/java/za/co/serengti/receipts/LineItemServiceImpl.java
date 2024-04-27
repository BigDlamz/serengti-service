package za.co.serengti.receipts;

import jakarta.inject.Inject;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class LineItemServiceImpl implements LineItemService {

    private final LineItemsRepository lineItemsRepository;
    private final LineItemMapper lineItemMapper;

    @Inject
    public LineItemServiceImpl(LineItemsRepository lineItemsRepository, LineItemMapper lineItemMapper) {
        this.lineItemsRepository = lineItemsRepository;
        this.lineItemMapper = lineItemMapper;
    }

    @Override
    @Transactional
    public List<LineItemDTO> save(List<LineItemDTO> lineItems, ReceiptDTO savedReceipt) {

        lineItems.forEach(item -> item.setReceipt(savedReceipt));

        List<LineItem> entities = lineItems.stream()
                .map(lineItemMapper::toEntity)
                .toList();

        return lineItemsRepository.save(entities).stream()
                .map(lineItemMapper::toDTO)
                .toList();

    }
}