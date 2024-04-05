package za.co.serengti.payments.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import za.co.serengti.controller.dto.SaveReceiptRequestDTO;
import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.receipts.dto.CashierDTO;
import za.co.serengti.receipts.dto.PromotionsDTO;
import za.co.serengti.receipts.dto.TillDTO;
import za.co.serengti.receipts.service.ReceiptService;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class PaymentsService {

    final ReceiptService receiptService;

    @Inject
    public PaymentsService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public PaymentResponse processPayment(PaymentRequest request) {


        //TODO make a call the the PayShap request to pay API

        //save a digital receipt after successful payment
        SaveReceiptRequestDTO saveReceiptRequest = SaveReceiptRequestDTO.builder()
                .posSystemId(request.getPosId())
                .storeId(request.getStoreId())
                .customerIdentifier(request.getPayingCustomerIdentifier())
                .transactionDate(request.getTransactionDate())
                .lineItems(request.getPurchasedProducts())
                .till(TillDTO.builder()
                        .tillNumber(3L)
                        .build())
                .cashier(CashierDTO.builder()
                        .name("Pearl")
                        .surname("Thusi")
                        .build())
                .promotions(PromotionsDTO.builder()
                        .name("Black Friday")
                        .description("Get 10% off on all items")
                        .build()).lineItems(List.of(ProductDTO.builder()
                        .name("Vest")
                        .description("Wife Beater")
                        .ean13Code("string")
                        .universalProductCode("string")
                        .sku("sku8989898989")
                        .category("CLOTHING")
                        .quantity(1)
                        .unitPrice(BigDecimal.valueOf(1000.00))
                        .build()))
                .discountAmount(BigDecimal.valueOf(1000.00))
                .subTotal(BigDecimal.valueOf(9000.00))
                .vatRate(BigDecimal.valueOf(15.00))
                .vatAmount(BigDecimal.valueOf(1350.00))
                .totalDueAfterTax(BigDecimal.valueOf(10350.00))
                .amountPaid(request.purchasedProducts.get(0).getUnitPrice())
                .changeDue(BigDecimal.valueOf(150.00))
                .build();

                receiptService.save(saveReceiptRequest);

        return PaymentResponse.
                builder().
                build();
    }
}
