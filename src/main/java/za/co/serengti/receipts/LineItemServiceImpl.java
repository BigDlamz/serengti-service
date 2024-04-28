package za.co.serengti.receipts;

import jakarta.inject.Inject;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class LineItemServiceImpl implements LineItemService {

    private final LineItemsRepository repository;
    private final LineItemMapper convertor;

    @Inject
    public LineItemServiceImpl(LineItemsRepository repository, LineItemMapper convertor) {
        this.repository = repository;
        this.convertor = convertor;
    }

    @Override
    @Transactional
    public List<LineItemDTO> save(List<LineItemDTO> lineItems, ReceiptDTO receipt) {

        lineItems.forEach(item -> item.setReceiptId(receipt.getReceiptId()));

        List<LineItem> entities = lineItems
                .stream()
                .map(item -> {
                    LineItem entity = convertor.toEntity(item);
                    entity.setReceiptId(item.getReceiptId());
                    return entity;
                })
                .toList();

        return repository.save(entities)
                .stream()
                .map(convertor::toDTO)
                .toList();
    }
}