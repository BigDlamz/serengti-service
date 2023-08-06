package za.co.serengti.receipts.mapper;

import za.co.serengti.customers.mapper.CustomerMapper;
import za.co.serengti.merchants.mapper.PosSystemMapper;
import za.co.serengti.merchants.mapper.StoreMapper;
import za.co.serengti.receipts.dto.LineItemDTO;
import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.entity.LineItem;
import za.co.serengti.receipts.entity.Receipt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ReceiptMapper {

    private final PromotionsMapper promotionsMapper;
    private final PosSystemMapper posSystemMapper;
    private final StoreMapper storeMapper;
    private final CustomerMapper customerMapper;
    private final CashierMapper cashierMapper;
    private final TillMapper tillMapper;
    private final LineItemMapper lineItemMapper;

    @Inject
    public ReceiptMapper(PromotionsMapper promotionsMapper, PosSystemMapper posSystemMapper, StoreMapper storeMapper, CustomerMapper customerMapper, CashierMapper cashierMapper, TillMapper tillMapper, LineItemMapper lineItemMapper) {
        this.promotionsMapper = promotionsMapper;
        this.posSystemMapper = posSystemMapper;
        this.storeMapper = storeMapper;
        this.customerMapper = customerMapper;
        this.cashierMapper = cashierMapper;
        this.tillMapper = tillMapper;
        this.lineItemMapper = lineItemMapper;
    }

    public ReceiptDTO toDto(Receipt entity) {

        List<LineItemDTO> lineItemDTOS = new ArrayList<>();
        for (LineItem lineItem : entity.getLineItems()) {
            lineItemDTOS.add(lineItemMapper.toDto(lineItem));
        }
        return ReceiptDTO.builder()
                .receiptID(entity.getReceiptID())
                .timestamp(entity.getTransactionDate())
                .posSystem(posSystemMapper.toDto(entity.getPosSystem()))
                .store(storeMapper.toDto(entity.getStore()))
                .customer(customerMapper.toDto(entity.getCustomer()))
                .lineItems(lineItemDTOS)
                .till(tillMapper.toDto(entity.getTill()))
                .cashier(cashierMapper.toDto(entity.getCashier()))
                .promotions(promotionsMapper.toDto(entity.getPromotions()))
                .discountAmount(entity.getDiscountAmount())
                .subTotal(entity.getSubTotal())
                .vatRate(entity.getVatRate())
                .vatAmount(entity.getVatAmount())
                .totalDueAfterTax(entity.getTotalDueAfterTax())
                .amountPaid(entity.getAmountPaid())
                .change_due(entity.getChange_due())
                .build();
    }
}
