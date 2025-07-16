package org.anjuman_sayyed_mohalla.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "DASHBOARD_SHEET")
public class DashboardSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_receive_amount")
    private Long totalReceivedAmount;

    @Column(name = "total_available_amount")
    private Long totalAvailableAmount;

    @Column(name = "total_pending_amount")
    private Long totalPendingAmount;

    @Column(name = "total_expend_amount")
    private Long totalExpendAmount;
}
