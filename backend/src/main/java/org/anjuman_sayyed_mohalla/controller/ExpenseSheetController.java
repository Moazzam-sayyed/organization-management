package org.anjuman_sayyed_mohalla.controller;

import org.anjuman_sayyed_mohalla.model.dto.ExpenseSheetDto;
import org.anjuman_sayyed_mohalla.service.ExpenseSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anjuman/v1")
public class ExpenseSheetController {

    @Autowired
    ExpenseSheetService expenseSheetService;

    @PostMapping("/expensesheet")
    public ResponseEntity<String> createExpenseSheet(@RequestBody ExpenseSheetDto expenseSheetDto){
        String result = expenseSheetService.createExpenseSheet(expenseSheetDto);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @GetMapping("/expensesheet")
    public ResponseEntity<List<ExpenseSheetDto>> getExpenseSheet(){
        List<ExpenseSheetDto> expenseSheetDtoList = expenseSheetService.getExpenseSheet();
        return new ResponseEntity<List<ExpenseSheetDto>>(expenseSheetDtoList,HttpStatus.OK);
    }

    @PutMapping("/expensesheet")
    public ResponseEntity<String> updateExpenseSheet(@RequestBody ExpenseSheetDto expenseSheetDto,@RequestParam Long expenseSheetId){
        String result = expenseSheetService.updateExpenseSheet(expenseSheetDto,expenseSheetId);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @DeleteMapping("/expensesheet")
    public ResponseEntity<String> deleteExpenseSheet(@RequestParam Long expenseSheetId){
        String result = expenseSheetService.deleteExpenseSheet(expenseSheetId);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
