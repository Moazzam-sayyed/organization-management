package org.anjuman_sayyed_mohalla.controller;

import org.anjuman_sayyed_mohalla.model.dto.BalanceSheetDto;
import org.anjuman_sayyed_mohalla.service.BalanceSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anjuman/v1")
public class BalanceSheetController {

    @Autowired
    BalanceSheetService balanceSheetService;

    @PostMapping("/balancesheet")
    public ResponseEntity<String> createBalanceSheet(@RequestBody BalanceSheetDto balanceSheetDto, @RequestParam String memberName) {
        String result = balanceSheetService.addBalanceSheet(balanceSheetDto,memberName);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @GetMapping("/balancesheet")
    public ResponseEntity<List<BalanceSheetDto>> getBalanceSheet() {
        List<BalanceSheetDto> balanceSheetDtoList = balanceSheetService.getBalanceSheet();
        return new ResponseEntity<List<BalanceSheetDto>>(balanceSheetDtoList, HttpStatus.OK);
    }

    @GetMapping("balancesheetById")
    public ResponseEntity<BalanceSheetDto> getBalancesheet(@RequestParam Long balanceSheetId) {
        BalanceSheetDto result = balanceSheetService.getBalanceSheetById(balanceSheetId);
        return new ResponseEntity<BalanceSheetDto>(result, HttpStatus.OK);
    }

    @PutMapping("/balancesheet")
    public ResponseEntity<String> updateBalanceSheet(@RequestBody BalanceSheetDto balanceSheetDto, @RequestParam Long balanceSheetId) {
        String result = balanceSheetService.updateBalanceSheet(balanceSheetDto, balanceSheetId);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @DeleteMapping("/balancesheet")
    public ResponseEntity<String> deleteMember(@RequestParam Long balanceSheetId) {
        String result = balanceSheetService.deleteBalanceSheet(balanceSheetId);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}