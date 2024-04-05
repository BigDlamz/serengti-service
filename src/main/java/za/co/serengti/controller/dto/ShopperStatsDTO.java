package za.co.serengti.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.money.MonetaryAmount;

@Data
@Builder
public class ShopperStatsDTO {
    MonetaryAmount totalPaymentsMade;
    Long unreadReceiptsCount;
    Long totalReceiptsCount;
}
