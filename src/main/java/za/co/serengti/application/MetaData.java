package za.co.serengti.application;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetaData {
    private long posSystem;
    private long store;
}