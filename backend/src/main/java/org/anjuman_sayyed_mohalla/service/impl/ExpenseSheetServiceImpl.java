package org.anjuman_sayyed_mohalla.service.impl;

import org.anjuman_sayyed_mohalla.model.dto.ExpenseSheetDto;
import org.anjuman_sayyed_mohalla.model.entities.ExpenseSheet;
import org.anjuman_sayyed_mohalla.repositories.ExpenseSheetRepository;
import org.anjuman_sayyed_mohalla.service.ExpenseSheetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseSheetServiceImpl implements ExpenseSheetService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ExpenseSheetRepository expenseSheetRepository;

    @Override
    public String createExpenseSheet(ExpenseSheetDto expenseSheetDto) {
        ExpenseSheet expenseSheet = convertToEntity(expenseSheetDto);
        expenseSheetRepository.save(expenseSheet);
        return "Expense Sheet Added Successfully";
    }

    @Override
    public List<ExpenseSheetDto> getExpenseSheet() {
        List<ExpenseSheet> expenseSheetList = expenseSheetRepository.findAll();
        List<ExpenseSheetDto> expenseSheetDtoList = new ArrayList<>();
        for (ExpenseSheet expenseSheet : expenseSheetList){
            expenseSheetDtoList.add(convertToDto(expenseSheet));
        }
        if(expenseSheetDtoList.size()>=1)
            return expenseSheetDtoList;
        else
            return Collections.emptyList();
    }

    @Override
    public String updateExpenseSheet(ExpenseSheetDto expenseSheetDto, Long expenseSheetId) {
        Optional<ExpenseSheet> optionalExpenseSheet = expenseSheetRepository.findById(expenseSheetId);
        if(optionalExpenseSheet.isPresent()){
            ExpenseSheet expenseSheet = convertToEntity(expenseSheetDto);
            expenseSheet.setId(expenseSheetId);
            expenseSheetRepository.save(expenseSheet);
            return "Expense Updated Successfully";
        }
        return "Unable to find the record";
    }

    @Override
    public String deleteExpenseSheet(Long expenseSheetId) {
        Optional<ExpenseSheet> optionalExpenseSheet = expenseSheetRepository.findById(expenseSheetId);
        if(optionalExpenseSheet.isPresent()){
            expenseSheetRepository.delete(optionalExpenseSheet.get());
            return "Expense Deleted Successfully";
        }
        return "Unable to find record";
    }

    public ExpenseSheet convertToEntity(ExpenseSheetDto expenseSheetDto){
        return modelMapper.map(expenseSheetDto,ExpenseSheet.class);
    }

    public ExpenseSheetDto convertToDto(ExpenseSheet expenseSheet){
        return modelMapper.map(expenseSheet,ExpenseSheetDto.class);
    }
}
