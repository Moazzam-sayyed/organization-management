package org.anjuman_sayyed_mohalla.model.dto;

import lombok.Data;

@Data
public class ExpenseSheetDto {

    private Long year;

    private String month;

    private String description;

    private Long amount;
}
