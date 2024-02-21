package za.co.serengti.receipts.mapper;

import lombok.extern.slf4j.Slf4j;
import za.co.serengti.shoppers.mapper.ShopperMapper;
import za.co.serengti.merchants.mapper.PosSystemMapper;
import za.co.serengti.merchants.mapper.StoreMapper;
import za.co.serengti.receipts.dto.LineItemDTO;
import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.entity.LineItem;
import za.co.serengti.receipts.entity.Receipt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Slf4j
public class ReceiptMapper {

    private final PromotionsMapper promotionsMapper;
    private final PosSystemMapper posSystemMapper;
    private final StoreMapper storeMapper;
    private final ShopperMapper shopperMapper;
    private final CashierMapper cashierMapper;
    private final TillMapper tillMapper;
    private final LineItemMapper lineItemMapper;

    @Inject
    public ReceiptMapper(PromotionsMapper promotionsMapper, PosSystemMapper posSystemMapper, StoreMapper storeMapper, ShopperMapper shopperMapper, CashierMapper cashierMapper, TillMapper tillMapper, LineItemMapper lineItemMapper) {
        this.promotionsMapper = promotionsMapper;
        this.posSystemMapper = posSystemMapper;
        this.storeMapper = storeMapper;
        this.shopperMapper = shopperMapper;
        this.cashierMapper = cashierMapper;
        this.tillMapper = tillMapper;
        this.lineItemMapper = lineItemMapper;
    }

    public ReceiptDTO toDto(Receipt entity) {
        List<LineItemDTO> lineItemDTOS = new ArrayList<>();
        for (LineItem lineItem : entity.getLineItems()) {
            lineItemDTOS.add(lineItemMapper.toDto(lineItem));
        }

        log.info("Receipt ID: {}", entity.getReceiptId());
        return ReceiptDTO.builder()
                .receiptId(entity.getReceiptId())
                .timestamp(entity.getTransactionDate())
                .posSystem(posSystemMapper.toDto(entity.getPosSystem()))
                .store(storeMapper.toDto(entity.getStore()))
                .user(shopperMapper.toDto(entity.getShopper()))
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
                .changeDue(entity.getChangeDue())
                .viewed(entity.getViewed())
                .build();
    }
}
