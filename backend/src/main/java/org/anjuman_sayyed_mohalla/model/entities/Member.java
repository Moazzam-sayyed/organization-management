package org.anjuman_sayyed_mohalla.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.sql.Timestamp;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Table(name="member")
@Entity
public class Member {

    @Id
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "paid_amount")
    private Long paidAmount;

    @Column(name = "pending_amount")
    private Long pendingAmount;

    @UpdateTimestamp
    @Column(name = "lastUpdate")
    private Timestamp lastUpdateTs;
}
