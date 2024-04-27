package za.co.serengti.merchants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {

    private Long receiptId;
    private Integer starRating;
    private String userComment;
    private LocalDateTime feedbackDate;

}
