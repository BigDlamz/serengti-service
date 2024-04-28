package za.co.serengti.shoppers;

import lombok.Builder;
import lombok.Data;

import javax.money.MonetaryAmount;

@Data
@Builder
public class StatsDTO {

    MonetaryAmount totalPayments;
    Long unreadReceiptsCount;
    Long totalReceiptsCount;
}
