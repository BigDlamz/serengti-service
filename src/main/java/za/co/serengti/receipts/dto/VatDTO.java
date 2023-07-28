package za.co.serengti.receipts.dto;

import lombok.Data;

@Data
public class VatDTO {
    private static final Float RATE = 15.00f;
    private Float current_rate;

    public VatDTO() {
        current_rate = RATE;
    }

}
