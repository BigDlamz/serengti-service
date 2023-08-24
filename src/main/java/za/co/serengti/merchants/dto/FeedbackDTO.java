package za.co.serengti.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {

    private Long feedbackId;
    private Long receiptId;
    private Integer starRating;
    private String userComment;

}
