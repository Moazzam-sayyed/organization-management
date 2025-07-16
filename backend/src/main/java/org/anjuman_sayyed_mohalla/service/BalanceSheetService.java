package org.anjuman_sayyed_mohalla.service;

import org.anjuman_sayyed_mohalla.model.dto.BalanceSheetDto;
import org.anjuman_sayyed_mohalla.model.entities.BalanceSheet;

import java.util.List;

public interface BalanceSheetService {

    public String addBalanceSheet(BalanceSheetDto balanceSheetDto, String memberName);

    public List<BalanceSheetDto> getBalanceSheet();

    public BalanceSheetDto getBalanceSheetById(Long balanceSheetId);

    public String updateBalanceSheet(BalanceSheetDto balanceSheetDto, Long balanceSheetId);

    public String deleteBalanceSheet(Long balanceSheetId);
}
