package org.anjuman_sayyed_mohalla.model.entities;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "EXPENSE_SHEET")
public class ExpenseSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private Long year;

    @Column(name = "month")
    private String month;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Long amount;


}
