package za.co.serengti.receipts.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import za.co.serengti.application.SaveReceiptRequest;
import za.co.serengti.merchants.entity.MetaData;
import za.co.serengti.merchants.service.MerchantService;
import za.co.serengti.receipts.dto.CashierDTO;
import za.co.serengti.receipts.dto.PromotionsDTO;
import za.co.serengti.receipts.dto.TillDTO;
import za.co.serengti.receipts.entity.*;
import za.co.serengti.receipts.repository.ReceiptRepository;
import za.co.serengti.shoppers.entity.Shopper;
import za.co.serengti.shoppers.service.ShopperService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
@Slf4j
public class ReceiptService {

    private final MerchantService merchantService;
    private final ShopperService shopperService;
    private final LineItemsService lineItemsService;
    private final TillService tillService;
    private final CashierService cashierService;
    private final ReceiptRepository receiptRepository;
    private final PromotionsService promotionsService;

    public ReceiptService(MerchantService merchantService, ShopperService shopperService,
                          LineItemsService lineItemsService, TillService tillService,
                          CashierService cashierService, ReceiptRepository receiptRepository, PromotionsService promotionsService) {
        this.merchantService = merchantService;
        this.shopperService = shopperService;
        this.lineItemsService = lineItemsService;
        this.tillService = tillService;
        this.cashierService = cashierService;
        this.receiptRepository = receiptRepository;
        this.promotionsService = promotionsService;
    }

    @Transactional
    public Long save(SaveReceiptRequest request) {

        validateTransactionDate(request);

        long posId = request.getPosSystemId();
        long storeId = request.getStoreId();

        log.info("Saving receipt for POS ID: {} and Store ID: {}", posId, storeId);
        Receipt receipt;
        try {
            MetaData meta = getMetaData(posId, storeId);
            Shopper shopper = shopperService.findOrSaveNewShopper(request.getCustomerIdentifier());
            Till till = saveTill(request.getTill(), meta);
            Cashier cashier = saveCashier(request.getCashier());
            Promotions promotions = savePromotions(request.getPromotions());
            receipt = receiptRepository.save(buildReceipt(request, meta, shopper, till, cashier, promotions));
            saveLineItems(request, meta, receipt);
            log.info("Successfully saved receipt with ID: {}", receipt.getReceiptId());
        } catch (Exception e) {
            log.error("Error saving receipt for POS ID: {} and Store ID: {}", posId, storeId, e);
            throw e;
        }
        return receipt.receiptId;
    }

    private MetaData getMetaData(long posId, long storeId) {
        return merchantService.retrieveMetaData(posId, storeId);
    }

    private static void validateTransactionDate(SaveReceiptRequest request) {
        LocalDate transactionDate = request.getTransactionDate().toLocalDate();
        LocalDate today = LocalDate.now();
        if(!Objects.equals(transactionDate, today)) {
                throw new IllegalArgumentException("Transaction date must be today & not in the past or future. Received transaction date:" + transactionDate);
        }
    }

    private void saveLineItems(SaveReceiptRequest request, MetaData meta, Receipt receipt) {
        log.info("Start processing line items for POS ID: {} and Store ID: {}", meta.getPosSystem().posSystemID, meta.getStore().getStoreId());
        try {
            var items = request.getLineItems();
            List<LineItem> lineItems = lineItemsService.processLineItems(items, meta, receipt);
            lineItemsService.saveLineItems(lineItems);
            log.info("Successfully processed line items for POS ID: {} and Store ID: {}", meta.getPosSystem().posSystemID, meta.getStore().getStoreId());
        } catch (Exception e) {
            log.error("Error processing line items for POS ID: {} and Store ID: {}", meta.getPosSystem().posSystemID, meta.getStore().getStoreId(), e);
            throw e;
        }
    }

    private Till saveTill(TillDTO tillDTO, MetaData meta) {
        log.info("Start saving till information for POS ID: {} and Store ID: {}", meta.getPosSystem().posSystemID, meta.getStore().getStoreId());
        Till savedTill;
        try {
            Till till = tillService.toEntity(tillDTO, meta);
            savedTill = tillService.save(till);
            log.info("Successfully saved till information for POS ID: {} and Store ID: {}", meta.getPosSystem().posSystemID, meta.getStore().getStoreId());
        } catch (Exception e) {
            log.error("Error saving till information for POS ID: {} and Store ID: {}", meta.getPosSystem().posSystemID, meta.getStore().getStoreId(), e);
            throw e;
        }
        return savedTill;
    }

    private Cashier saveCashier(CashierDTO cashierDTO) {
        log.info("Start saving cashier information");
        Cashier cashier;
        try {
            cashier = cashierService.save(cashierService.toEntity(cashierDTO));
            log.info("Successfully saved cashier information. Cashier ID: {}", cashier.getCashierId());
        } catch (Exception e) {
            log.error("Error saving cashier information", e);
            throw e;
        }
        return cashier;
    }

    private Promotions savePromotions(PromotionsDTO promotionsDTO) {
        log.info("Start saving promotions information");
        Promotions promotions;
        try {
            promotions = promotionsService.save(promotionsService.toEntity(promotionsDTO));
            log.info("Successfully saved promotions information. Promotions ID: {}", promotions.getPromotionID());
        } catch (Exception e) {
            log.error("Error saving promotions information", e);
            throw e;
        }
        return promotions;
    }

    private Receipt buildReceipt(SaveReceiptRequest request, MetaData meta, Shopper shopper, Till till, Cashier cashier, Promotions promotions) {
        Receipt receipt;
        try {
            receipt = Receipt.builder()
                    .posSystem(meta.getPosSystem())
                    .store(meta.getStore())
                    .shopper(shopper)
                    .till(till)
                    .cashier(cashier)
                    .promotions(promotions)
                    .transactionDate(request.getTransactionDate())
                    .discountAmount(request.getDiscountAmount())
                    .subTotal(request.getSubTotal())
                    .vatRate(request.getVatRate())
                    .vatAmount(request.getVatAmount())
                    .totalDueAfterTax(request.getTotalDueAfterTax())
                    .amountPaid(request.getAmountPaid())
                    .changeDue(request.getChangeDue())
                    .viewed(false)
                    .build();
        } catch (Exception e) {
            log.error("Error building receipt", e);
            throw e;
        }
        return receipt;
    }

    public Receipt retrieve(Long receiptId) {
        log.info("Finding receipt with ID: {}", receiptId);
        return receiptRepository.findById(receiptId);
    }

    public List<Receipt> retrieveReceiptsByEmailAndDate(String email, LocalDate date) {
        log.info("Finding all receipts for customer with email: {} and date: {}", email, date);
        return receiptRepository.findAllReceiptsByCustomerEmailAndDate(email, date);
    }

    public Long findTotalUserReceiptCount(String email) {
        log.info("Finding total receipts for customer with email: {}", email);
        return receiptRepository.findTotalShopperReceiptsCount(email);
    }

    public boolean updateReceiptAsViewed(Long receiptId) {
        log.info("Marking receiptId: {} as viewed", receiptId);
        return receiptRepository.updateMethodAsViewed(receiptId);
    }

    public long retrieveUnreadReceiptsByEmail(String email) {
        log.info("Finding unread receipts for user with email: {}", email);
        return receiptRepository.findUnreadReceiptsByEmail(email);
    }
}