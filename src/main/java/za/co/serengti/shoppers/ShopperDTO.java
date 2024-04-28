package za.co.serengti.shoppers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopperDTO {

    @JsonIgnore
    private Long shopperId;
    private String name;
    private String email;
    @JsonIgnore
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String mobileNo;

}
