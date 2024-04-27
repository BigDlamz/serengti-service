package za.co.serengti.receipts;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import za.co.serengti.merchants.MerchantDTO;
import za.co.serengti.merchants.MerchantFacade;
import za.co.serengti.payments.PaymentRequest;
import za.co.serengti.shoppers.ShopperDTO;
import za.co.serengti.shoppers.ShopperService;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static za.co.serengti.receipts.FinanceService.ZA_VAT_RATE;

@ApplicationScoped
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptService receiptService;
    private final MerchantFacade merchantFacade;
    private final ShopperService shopperService;
    private final CashierService cashierService;
    private final TillService tillService;
    private final LineItemService lineItemService;
    private final ReceiptRepository receiptRepository;
    private final FinanceService financeService;
    private final ReceiptMapper converter;

    @Inject
    public ReceiptServiceImpl(ReceiptService receiptService, MerchantFacade merchantFacade, ShopperService shopperService, CashierService cashierService, TillService tillService, LineItemService lineItemService, ReceiptRepository receiptRepository, FinanceService financeService, ReceiptMapper converter) {
        this.receiptService = receiptService;
        this.merchantFacade = merchantFacade;
        this.shopperService = shopperService;
        this.cashierService = cashierService;
        this.tillService = tillService;
        this.lineItemService = lineItemService;
        this.receiptRepository = receiptRepository;
        this.financeService = financeService;
        this.converter = converter;

    }

    @Override
    @Transactional
    public ReceiptDTO save(ReceiptDTO receiptDTO) {

        Receipt entity = converter.toEntity(receiptDTO);
        Receipt receipt = receiptRepository.save(entity);
        return converter.toDTO(receipt);

    }

    @Override
    public ReceiptDTO find(Long receiptId) {

        return converter.toDTO(receiptRepository.findById(receiptId));

    }

    @Override
    public List<ReceiptDTO> find(String email, LocalDate date) {

        return receiptRepository.findAllByEmailAndDate(email, date)
                .stream()
                .map(converter::toDTO)
                .toList();

    }

    @Override
    public Long processReceipt(PaymentRequest request) {

        var shopper = shopperService.find(request.getShopper().getEmail());
        var merchant = merchantFacade.find(request.getMerchantId());

        var receipt = assemble(request, shopper, merchant);

        var savedReceipt = receiptService.save(receipt);

        lineItemService.save(request.getLineItems(), savedReceipt);

        return savedReceipt.getReceiptId();
    }


    @Override
    public ReceiptDTO assemble(PaymentRequest request, ShopperDTO shopper, MerchantDTO merchant) {

        var lineItems = request.getLineItems();

        var sub = financeService.getSubTotal(lineItems);
        var vatAmount = financeService.getVatAmount(sub);
        var totalDue = financeService.getTotalDue(sub, vatAmount);

        CashierDTO cashier = cashierService.save(request.getCashier());

        TillDTO till = tillService.save(request.getTill());

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

    @Override
    public boolean updateStatus(Long receiptId, Status status) {

          return receiptRepository.updateStatus(receiptId, status);

    }

    @Override
    public Long findUnread(String shopperEmail)
    {

        return receiptRepository.findUnread(shopperEmail);

    }

    @Override
    public Long findTotalCount(String shopperEmail) {

        return receiptRepository.findTotalCount(shopperEmail);

    }


    @Override
    public MonetaryAmount findTotalPaid(String shopperEmail) {

        var totalPaid = receiptRepository.findTotalPaid(shopperEmail);

        CurrencyUnit currency = Monetary.getCurrency("ZAR");

        return Monetary.getDefaultAmountFactory()
                .setCurrency(currency)
                .setNumber(totalPaid)
                .create();

    }

}

