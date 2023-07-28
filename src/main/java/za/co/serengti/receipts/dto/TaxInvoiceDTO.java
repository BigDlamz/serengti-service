package za.co.serengti.receipts.dto;

import za.co.serengti.merchants.dto.StoreDTO;

public class TaxInvoiceDTO {
    private String serialNumber;
    private StoreDTO store;
    private VatDTO vat;
}
