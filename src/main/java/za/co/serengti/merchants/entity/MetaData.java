package za.co.serengti.merchants.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetaData {
    POSSystem posSystem;
    Store store;
}
