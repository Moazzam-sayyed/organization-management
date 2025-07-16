package org.anjuman_sayyed_mohalla.service;

import org.anjuman_sayyed_mohalla.model.dto.DashboardSheetDto;
import org.anjuman_sayyed_mohalla.model.entities.BalanceSheet;
import org.anjuman_sayyed_mohalla.model.entities.DashboardSheet;

import java.util.List;

public interface DashboardSheetService {

    public DashboardSheetDto getDashboard();

    public String updateDashboard(List<BalanceSheet> balanceSheetList, Long dashboarSheetId);


}
