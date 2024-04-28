package za.co.serengti.receipts;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import za.co.serengti.shoppers.Shopper;
import za.co.serengti.merchants.Merchant;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receipts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    public Long receiptId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "merchant_id")
    public Merchant merchant;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "shopper_id")
    public Shopper shopper;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "till_Id")
    public Till till;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cashier_Id")
    public Cashier cashier;

    @Column(name = "transaction_date")
    public LocalDateTime transactionDate;

    @Column(name = "subtotal")
    public BigDecimal subTotal;

    @Column(name = "vat_rate")
    public BigDecimal vatRate;

    @Column(name = "vat_amount")
    public BigDecimal vatAmount;

    @Column(name = "discount_amount")
    public BigDecimal discountAmount;

    @Column(name = "total_due_after_tax")
    public BigDecimal totalDueAfterTax;

    @Column(name = "amount_paid")
    public BigDecimal amountPaid;

    @Column(name = "viewed", nullable = false)
    public Boolean viewed;

    @OneToMany(mappedBy = "receiptId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    public List<LineItem> lineItems;

}
