package org.anjuman_sayyed_mohalla.controller;

import org.anjuman_sayyed_mohalla.model.dto.DashboardSheetDto;
import org.anjuman_sayyed_mohalla.model.dto.ExpenseSheetDto;
import org.anjuman_sayyed_mohalla.service.DashboardSheetService;
import org.anjuman_sayyed_mohalla.service.ExpenseSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anjuman/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    @Autowired
    DashboardSheetService dashboardSheetService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardSheetDto> getDashboard(@RequestParam Long dashboardId) {
        DashboardSheetDto dashboardSheetDto = dashboardSheetService.getDashboard();
        return new ResponseEntity<DashboardSheetDto>(dashboardSheetDto, HttpStatus.OK);
    }
}
