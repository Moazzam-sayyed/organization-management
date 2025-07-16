package org.anjuman_sayyed_mohalla.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;

public class TrustDto {


    private Long id;
    private String trustMemberName;
    private String designation;
    private String contactNumber;
    private Timestamp lastUpdateTs;
}
