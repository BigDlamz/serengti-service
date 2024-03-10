package za.co.serengti.shoppers.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import za.co.serengti.application.SaveReceiptRequest;
import za.co.serengti.receipts.entity.Receipt;
import za.co.serengti.receipts.service.ReceiptService;
import za.co.serengti.shoppers.entity.EmailShopper;
import za.co.serengti.shoppers.entity.MobileShopper;
import za.co.serengti.shoppers.entity.Shopper;
import za.co.serengti.shoppers.repository.ShopperRepository;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ShopperService {

    final ShopperIdentifier shopperIdentifier;
    final ShopperRepository shopperRepository;
    final ReceiptService receiptService;

    @Inject
    public ShopperService(ShopperIdentifier shopperIdentifier, ShopperRepository shopperRepository, ReceiptService receiptService) {
        this.shopperIdentifier = shopperIdentifier;
        this.shopperRepository = shopperRepository;
        this.receiptService = receiptService;
    }

    public Long saveReceipt(SaveReceiptRequest request) {
        return receiptService.save(request);
    }

    public Receipt retrieveReceipt(Long receiptId) {
        return receiptService.retrieve(receiptId);
    }

    public  List<Receipt> retrieveReceiptsByEmailAndDate(String email, LocalDate transactionDate) {
        return  receiptService.retrieveReceiptsByEmailAndDate(email, transactionDate);
    }

    public Long retrieveReceiptsTotalCount(String email) {
        return receiptService.findTotalUserReceiptCount(email);
    }

    public boolean updateReceiptAsViewed(Long receiptId) {
    return receiptService.updateReceiptAsViewed(receiptId);
    }

    public Long retrieveUnreadReceipts(String email) {
      return receiptService.retrieveUnreadReceiptsByEmail(email);
    }

    @Transactional
    public Shopper findOrSaveNewShopper(String shopperId) {
        ShopperIdentifier.Type type = shopperIdentifier.identify(shopperId);
        return switch (type) {
            case EMAIL -> shopperRepository.findByEmailAddress(shopperId)
                    .orElseGet(() -> shopperRepository.save(new EmailShopper("Philani", type.name(), shopperId)));
            case MOBILE -> shopperRepository.findByMobileNumber(shopperId)
                    .orElseGet(() -> shopperRepository.save(new MobileShopper("Philani", type.name(), shopperId)));
            default -> null;
        };
    }

}
