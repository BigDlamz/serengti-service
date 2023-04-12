package za.co.serengti.receipt.service.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestMetaData {
    private long posId;
    private long storeId;
}
