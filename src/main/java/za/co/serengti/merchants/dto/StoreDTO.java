package za.co.serengti.merchants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
    @JsonIgnore
    private Long id;
    private String name;
    private String address;
    private String vatRegistrationNumber;
    private String storeLogoURL;
}
