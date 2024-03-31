package za.co.serengti.application;

import lombok.Builder;
import lombok.Data;

import javax.money.MonetaryAmount;

@Data
@Builder
public class ShopperStats {
    MonetaryAmount totalPaymentsMade;
    Long unreadReceiptsCount;
    Long totalReceiptsCount;
}
