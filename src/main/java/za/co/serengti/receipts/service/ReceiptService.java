package za.co.serengti.receipts.service;

import lombok.extern.slf4j.Slf4j;
import za.co.serengti.application.SaveReceiptRequestDTO;
import za.co.serengti.customers.entity.Customer;
import za.co.serengti.customers.service.CustomerService;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.Store;
import za.co.serengti.merchants.service.MerchantService;
import za.co.serengti.receipts.dto.CashierDTO;
import za.co.serengti.receipts.dto.PromotionsDTO;
import za.co.serengti.receipts.dto.TillDTO;
import za.co.serengti.receipts.entity.*;
import za.co.serengti.receipts.repository.ReceiptRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Slf4j
public class ReceiptService {

    private final MerchantService merchantService;
    private final CustomerService customerService;
    private final LineItemsService lineItemsService;
    private final TillService tillService;
    private final CashierService cashierService;
    private final ReceiptRepository receiptRepository;
    private final PromotionsService promotionsService;
    private final RecordMapper mapper;

    public ReceiptService(MerchantService merchantService, CustomerService customerService,
                          LineItemsService lineItemsService, TillService tillService,
                          CashierService cashierService, ReceiptRepository receiptRepository, PromotionsService promotionsService, RecordMapper mapper) {
        this.merchantService = merchantService;
        this.customerService = customerService;
        this.lineItemsService = lineItemsService;
        this.tillService = tillService;
        this.cashierService = cashierService;
        this.receiptRepository = receiptRepository;
        this.promotionsService = promotionsService;
        this.mapper = mapper;
    }

    @Transactional
    public Receipt process(SaveReceiptRequestDTO request, Long posId, Long storeId) {
        log.info("Processing receipt for POS ID: {} and Store ID: {}", posId, storeId);
        Receipt receipt;
        try {
            POSSystem posSystem = merchantService.findPosSystem(posId);
            Store store = merchantService.findStore(storeId);
            Customer customer = customerService.findOrSaveCustomer(request.getCustomerIdentifier());
            List<LineItem> lineItems = saveLineItems(request, posSystem, store);
            Till till = saveTill(request.getTill(), posSystem, store);
            Cashier cashier = saveCashier(request.getCashier());
            Promotions promotions = savePromotions(request.getPromotions());
            receipt = save(buildReceipt(request,posSystem, store, customer, lineItems, till, cashier, promotions));  // call save method before logging the receipt's ID
            log.info("Successfully processed receipt. Receipt ID: {}", receipt.getReceiptID());
        } catch (Exception e) {
            log.error("Error processing receipt for POS ID: {} and Store ID: {}", posId, storeId, e);
            throw e;
        }
        return receipt;
    }

    public Receipt save(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    private List<LineItem> saveLineItems(SaveReceiptRequestDTO request, POSSystem posSystem, Store store) {
        log.info("Start processing line items for POS ID: {} and Store ID: {}", posSystem.posSystemID, store.getStoreID());
        List<LineItem> lineItems;
        try {
            lineItems = lineItemsService.processLineItems(request.getPurchasedItems(), posSystem, store);
            log.info("Successfully processed line items for POS ID: {} and Store ID: {}", posSystem.posSystemID, store.getStoreID());
        }
        catch (Exception e) {
            log.error("Error processing line items for POS ID: {} and Store ID: {}",  posSystem.posSystemID, store.getStoreID(), e);
            throw e;
        }
        return lineItems;
    }

    private Till saveTill(TillDTO tillDTO, POSSystem posSystem, Store store) {
        log.info("Start saving till information for POS ID: {} and Store ID: {}", posSystem.posSystemID, store.getStoreID());
       Till savedTill;
        try {
            Till till = mapper.convert(tillDTO, Till.class);
            till.setStore(store);
            till.setPosSystem(posSystem);
            savedTill = tillService.save(till);
            log.info("Successfully saved till information for POS ID: {} and Store ID: {}", posSystem.posSystemID, store.getStoreID());
        }
        catch (Exception e) {
            log.error("Error saving till information for POS ID: {} and Store ID: {}", posSystem.posSystemID, store.getStoreID(), e);
            throw e;
        }
        return savedTill;
    }

    private Cashier saveCashier(CashierDTO cashierDTO) {
        log.info("Start saving cashier information");
        Cashier cashier;
        try {
            cashier = cashierService.save(mapper.convert(cashierDTO, Cashier.class));
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
            promotions = promotionsService.save(mapper.convert(promotionsDTO, Promotions.class));
            log.info("Successfully saved promotions information. Promotions ID: {}", promotions.getPromotionID());
        } catch (Exception e) {
            log.error("Error saving promotions information", e);
            throw e;
        }
        return promotions;
    }

    private Receipt buildReceipt(SaveReceiptRequestDTO request, POSSystem posSystem, Store store, Customer customer, List<LineItem> lineItems, Till till, Cashier cashier, Promotions promotions) {
        Receipt receipt;
        try {
            receipt = Receipt.builder()
                    .posSystem(posSystem)
                    .store(store)
                    .customer(customer)
                    .purchasedItems(lineItems)
                    .till(till)
                    .cashier(cashier)
                    .promotions(promotions)
                    .transactionDate(request.getTransactionDate())
                    .amountBeforeTax(request.getTaxInvoice().getAmountBeforeTax())
                    .amountAfterTax(request.getTaxInvoice().getAmountAfterTax())
                    .build();
        } catch (Exception e) {
            log.error("Error building receipt", e);
            throw e;
        }
        return receipt;
    }

    //CREATE A seperate tax invoice table, run it past ChatGPT first
}