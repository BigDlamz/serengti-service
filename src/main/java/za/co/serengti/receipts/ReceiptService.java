package za.co.serengti.receipts;

import za.co.serengti.merchants.MerchantDTO;
import za.co.serengti.payments.PaymentRequest;
import za.co.serengti.shoppers.ShopperDTO;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.util.List;

public interface ReceiptService {

    ReceiptDTO save(ReceiptDTO receiptDTO);

    ReceiptDTO find(Long receiptId);

    List<ReceiptDTO> find(String email, LocalDate date);

    Long findUnread(String email);

    Long findTotalCount(String email);

    MonetaryAmount findTotalPaid(String shopperEmail);

    ReceiptDTO assemble(PaymentRequest request, ShopperDTO shopper, MerchantDTO merchant);

    Long processReceipt(PaymentRequest request);

    boolean updateStatus(Long receiptId, Status status);


}
