package org.anjuman_sayyed_mohalla.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Table(name="BALANCE_SHEET")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BalanceSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;

    @Column(name = "year")
    private Long year;

    @Column(name = "january")
    private Long january;

    @Column(name = "february")
    private Long february;

    @Column(name = "march")
    private Long march;

    @Column(name = "april")
    private Long april;

    @Column(name = "may")
    private Long may;

    @Column(name = "june")
    private Long june;

    @Column(name = "july")
    private Long july;

    @Column(name = "august")
    private Long august;

    @Column(name = "september")
    private Long september;

    @Column(name = "october")
    private Long october;

    @Column(name = "november")
    private Long november;

    @Column(name = "december")
    private Long december;

    @Column(name = "other1")
    private Long other1;

    @Column(name = "other2")
    private Long other2;

    @Column(name = "yearly_paid_amount")
    private Long yearlyPaidAmount;

    @Column(name = "yearly_pending_amount")
    private Long yearlyPendingAmount;

    @Column(name = "lastUpdateTs")
    @UpdateTimestamp
    private Timestamp lastUpdateTs;

}
