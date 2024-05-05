package za.co.serengti.receipts;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import za.co.serengti.merchants.MerchantDTO;
import za.co.serengti.merchants.MerchantService;
import za.co.serengti.payments.PaymentRequest;
import za.co.serengti.shoppers.ShopperDTO;
import za.co.serengti.shoppers.ShopperService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static za.co.serengti.receipts.FinanceService.ZA_VAT_RATE;

@ApplicationScoped
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final LineItemService itemService;
    private final MerchantService merchantService;
    private final ShopperService shopperService;
    private final CashierService cashierService;
    private final FinanceService financeService;
    private final TillService tillService;
    private final ReceiptMapper converter;

    @Inject
    public ReceiptServiceImpl(MerchantService merchantService, ShopperService shopperService, CashierService cashierService, TillService tillService, LineItemService itemService, ReceiptRepository receiptRepository, FinanceService financeService, ReceiptMapper converter) {
        this.merchantService = merchantService;
        this.shopperService = shopperService;
        this.cashierService = cashierService;
        this.tillService = tillService;
        this.itemService = itemService;
        this.receiptRepository = receiptRepository;
        this.financeService = financeService;
        this.converter = converter;

    }

    @Override
    @Transactional
    public ReceiptDTO save(ReceiptDTO receipt) {

        Receipt entity = converter.toEntity(receipt);
        Receipt saved = receiptRepository.save(entity);
        return converter.toDTO(saved);

    }

    @Override
    public ReceiptDTO find(Long receiptId) {

        Receipt receipt = receiptRepository.findById(receiptId);
        return converter.toDTO(receipt);

    }

    @Override
    public List<ReceiptDTO> find(String email, LocalDate date) {

        return receiptRepository.findAllByEmailAndDate(email, date)
                .stream()
                .map(converter::toDTO)
                .toList();

    }

    @Override
    public ReceiptDTO processReceipt(PaymentRequest request) {

        var shopper = shopperService.find(request.getShopper().getEmail());
        var merchant = merchantService.find(request.getMerchantId());
        var receipt = assemble(request, shopper, merchant);
        var savedReceipt = save(receipt);

        itemService.save(request.getLineItems(), savedReceipt);

        return savedReceipt;
    }


    @Override
    public ReceiptDTO assemble(PaymentRequest request, ShopperDTO shopper, MerchantDTO merchant) {

        var lineItems = request.getLineItems();

        var sub = financeService.getSubTotal(lineItems);
        var vatAmount = financeService.getVatAmount(sub);
        var totalDue = financeService.getTotalDue(sub, vatAmount);

        var cashier = cashierService.save(request.getCashier());
        var till = tillService.save(request.getTill());

        return ReceiptDTO.builder()
                .timestamp(request.getTxDate())
                .shopper(shopper)
                .merchant(merchant)
                .cashier(cashier)
                .till(till)
                .lineItems(lineItems)
                .vatRate(ZA_VAT_RATE)
                .subTotal(sub)
                .vatAmount(vatAmount)
                .totalDueAfterTax(totalDue)
                .amountPaid(totalDue)
                .discountAmount(BigDecimal.ZERO)
                .viewed(false).
                build();

    }

    public boolean updateStatus(UpdateStatusRequest request) {

        return receiptRepository.updateStatus(request.receiptId(), request.status());

    }

    @Override
    public Long findUnread(String email) {

        return receiptRepository.findUnread(email);

    }

    @Override
    public Long findTotalCount(String email) {

        return receiptRepository.findTotalCount(email);

    }

}

