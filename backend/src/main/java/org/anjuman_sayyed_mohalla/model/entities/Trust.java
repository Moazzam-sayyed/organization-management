package org.anjuman_sayyed_mohalla.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Table(name="TRUST_MEMBER")
@Entity
public class Trust {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trusty_id")
    private Long id;

    @Column(name = "trust_member_name")
    private String trustMemberName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "last_update_ts")
    private Timestamp lastUpdateTs;

}
