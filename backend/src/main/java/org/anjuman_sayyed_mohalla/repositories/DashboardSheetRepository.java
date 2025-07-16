package org.anjuman_sayyed_mohalla.repositories;

import org.anjuman_sayyed_mohalla.model.entities.BalanceSheet;
import org.anjuman_sayyed_mohalla.model.entities.DashboardSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface DashboardSheetRepository extends JpaRepository<DashboardSheet, Serializable> {
}
