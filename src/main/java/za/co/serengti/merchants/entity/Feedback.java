package za.co.serengti.merchants.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "feedback")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;

    @Column(name = "receipt_id")
    private Long receiptId;

    @Column(name = "star_rating")
    private Integer starRating;

    @Column(name = "user_comment")
    private String userComment;

    @Column(name = "feedback_date")
    private java.util.Date feedbackDate;


}
