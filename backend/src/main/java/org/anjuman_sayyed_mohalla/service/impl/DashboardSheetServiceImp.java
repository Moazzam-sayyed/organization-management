package org.anjuman_sayyed_mohalla.service.impl;

import org.anjuman_sayyed_mohalla.model.dto.DashboardSheetDto;
import org.anjuman_sayyed_mohalla.model.entities.BalanceSheet;
import org.anjuman_sayyed_mohalla.model.entities.DashboardSheet;

import org.anjuman_sayyed_mohalla.repositories.DashboardSheetRepository;
import org.anjuman_sayyed_mohalla.service.DashboardSheetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardSheetServiceImp implements DashboardSheetService {

    @Autowired
    DashboardSheetRepository dashboardSheetRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public DashboardSheetDto getDashboard() {
        List<DashboardSheet> dashboardSheetServiceList = dashboardSheetRepository.findAll();
        if(dashboardSheetServiceList.size()>=1){
            List<DashboardSheetDto> dashboardSheetDtoList = new ArrayList<>();
            for(DashboardSheet dashboardSheet:dashboardSheetServiceList){
                DashboardSheetDto dashboardSheetDto = convertToDto(dashboardSheet);
                dashboardSheetDtoList.add(dashboardSheetDto);
            }
            return dashboardSheetDtoList.get(0);
        }
        return null;
    }

    @Override
    public String updateDashboard(List<BalanceSheet> balanceSheetList, Long dashboarSheetId) {
        return "";
    }

    public DashboardSheetDto convertToDto(DashboardSheet dashboardSheet){
        return modelMapper.map(dashboardSheet,DashboardSheetDto.class);
    }
}
