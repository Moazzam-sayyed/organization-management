package org.anjuman_sayyed_mohalla.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DashboardSheetDto {
    private Long id;
    private Long totalReceivedAmount;
    private Long totalAvailableAmount;
    private Long totalPendingAmount;
    private Long totalExpendAmount;

}
