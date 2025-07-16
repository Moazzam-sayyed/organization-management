package org.anjuman_sayyed_mohalla.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.anjuman_sayyed_mohalla.model.entities.Member;
import java.sql.Timestamp;

@Data
public class BalanceSheetDto {

    private Long id;
    private Long memberId;
    private String memberName;
    private Long year;
    private Long january;
    private Long february;
    private Long march;
    private Long april;
    private Long may;
    private Long june;
    private Long july;
    private Long august;
    private Long september;
    private Long october;
    private Long november;
    private Long december;
    private Long other1;
    private Long other2;
    private Long yearlyPaidAmount;
    private Long yearlyPendingAmount;
    private Timestamp lastUpdateTs;
}
