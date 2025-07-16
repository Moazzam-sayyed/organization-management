package org.anjuman_sayyed_mohalla.model.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class MemberDto {

    private Long id;
    private String name;
    private Long paidAmount;
    private Long pendingAmount;
    private Timestamp lastUpdateTs;
}
