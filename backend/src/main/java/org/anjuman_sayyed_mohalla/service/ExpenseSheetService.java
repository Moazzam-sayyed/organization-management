package org.anjuman_sayyed_mohalla.service;

import lombok.extern.java.Log;
import org.anjuman_sayyed_mohalla.model.dto.ExpenseSheetDto;
import org.anjuman_sayyed_mohalla.model.entities.ExpenseSheet;
import org.anjuman_sayyed_mohalla.repositories.ExpenseSheetRepository;

import java.util.List;

public interface ExpenseSheetService {

    public String createExpenseSheet(ExpenseSheetDto expenseSheetDto);

    public List<ExpenseSheetDto> getExpenseSheet();

    public String updateExpenseSheet(ExpenseSheetDto expenseSheetDto, Long expenseSheetId);

    public String deleteExpenseSheet(Long expenseSheetId);
}
