package za.co.serengti.receipts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TillDTO {

    @JsonIgnore
    private Long tillId;
    private Long tillNo;

}
