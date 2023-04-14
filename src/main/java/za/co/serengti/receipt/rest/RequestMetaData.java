package za.co.serengti.receipt.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestMetaData {
    private long posSystem;
    private long store;
}
