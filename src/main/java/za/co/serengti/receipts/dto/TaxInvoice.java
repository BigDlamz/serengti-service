package za.co.serengti.receipts.dto;

import za.co.serengti.merchants.dto.StoreDTO;

public class TaxInvoice {
    private String serialNumber;
    private StoreDTO store;
    private Vat vat;
}
