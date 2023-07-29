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
public class POSSystemDTO {
    @JsonIgnore
    private Long posSystemID;
    private String name;
    private String version;
}
