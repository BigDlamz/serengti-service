package za.co.serengti.receipts;

import lombok.extern.slf4j.Slf4j;
import za.co.serengti.shoppers.ShopperMapper;
import za.co.serengti.merchants.MerchantMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Slf4j
public class ReceiptMapper {

    private final MerchantMapper merchantMapper;
    private final ShopperMapper shopperMapper;
    private final CashierMapper cashierMapper;
    private final TillMapper tillMapper;
    private final LineItemMapper lineItemMapper;

    @Inject
    public ReceiptMapper(MerchantMapper merchantMapper, ShopperMapper shopperMapper, CashierMapper cashierMapper, TillMapper tillMapper, LineItemMapper lineItemMapper) {
        this.merchantMapper = merchantMapper;
        this.shopperMapper = shopperMapper;
        this.cashierMapper = cashierMapper;
        this.tillMapper = tillMapper;
        this.lineItemMapper = lineItemMapper;
    }

    public ReceiptDTO toDTO(Receipt entity) {

        List<LineItemDTO> lineItemDTOS = new ArrayList<>();

        for (LineItem lineItem : entity.getLineItems()) {
            lineItemDTOS.add(lineItemMapper.toDTO(lineItem));
        }

        return ReceiptDTO.builder()
                .receiptId(entity.getReceiptId())
                .timestamp(entity.getTransactionDate())
                .merchant(merchantMapper.toDTO(entity.getMerchant()))
                .shopper(shopperMapper.toDTO(entity.getShopper()))
                .lineItems(lineItemDTOS)
                .till(tillMapper.toDto(entity.getTill()))
                .cashier(cashierMapper.toDTO(entity.getCashier()))
                .discountAmount(entity.getDiscountAmount())
                .subTotal(entity.getSubTotal())
                .vatRate(entity.getVatRate())
                .vatAmount(entity.getVatAmount())
                .totalDueAfterTax(entity.getTotalDueAfterTax())
                .amountPaid(entity.getAmountPaid())
                .viewed(entity.getViewed())
                .build();
    }

    public Receipt toEntity(ReceiptDTO dto) {

        List<LineItem> lineItems = new ArrayList<>();

        for (LineItemDTO lineItemDTO : dto.getLineItems()) {
            lineItems.add(lineItemMapper.toEntity(lineItemDTO));
        }

        return Receipt.builder()
                .receiptId(dto.getReceiptId())
                .transactionDate(dto.getTimestamp())
                .merchant(merchantMapper.toEntity(dto.getMerchant()))
                .shopper(shopperMapper.toEntity(dto.getShopper()))
                .lineItems(lineItems)
                .till(tillMapper.toEntity(dto.getTill()))
                .cashier(cashierMapper.toEntity(dto.getCashier()))
                .discountAmount(dto.getDiscountAmount())
                .subTotal(dto.getSubTotal())
                .vatRate(dto.getVatRate())
                .vatAmount(dto.getVatAmount())
                .totalDueAfterTax(dto.getTotalDueAfterTax())
                .amountPaid(dto.getAmountPaid())
                .viewed(dto.getViewed())
                .build();
    }
}
