package za.co.serengti.receipt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class POSSystem {
    private Long id;
    private String name;
    private String vendor;
}
