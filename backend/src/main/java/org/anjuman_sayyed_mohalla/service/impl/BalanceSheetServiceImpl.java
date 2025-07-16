package org.anjuman_sayyed_mohalla.service.impl;

import org.anjuman_sayyed_mohalla.exception.DuplicateRecordFoundException;
import org.anjuman_sayyed_mohalla.exception.ResourceNotFoundException;
import org.anjuman_sayyed_mohalla.model.dto.BalanceSheetDto;
import org.anjuman_sayyed_mohalla.model.entities.BalanceSheet;
import org.anjuman_sayyed_mohalla.model.entities.DashboardSheet;
import org.anjuman_sayyed_mohalla.model.entities.ExpenseSheet;
import org.anjuman_sayyed_mohalla.model.entities.Member;
import org.anjuman_sayyed_mohalla.repositories.BalanceSheetRepository;
import org.anjuman_sayyed_mohalla.repositories.DashboardSheetRepository;
import org.anjuman_sayyed_mohalla.repositories.ExpenseSheetRepository;
import org.anjuman_sayyed_mohalla.repositories.MemberRepository;
import org.anjuman_sayyed_mohalla.service.BalanceSheetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceSheetServiceImpl implements BalanceSheetService {

    @Autowired
    BalanceSheetRepository balanceSheetRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private DashboardSheetRepository dashboardSheetRepository;

    @Autowired
    private ExpenseSheetRepository expenseSheetRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public String addBalanceSheet(BalanceSheetDto balanceSheetDto, String memberName) {
        //Optional<Member> optionalMember = memberRepository.findById(balanceSheetDto.getMemberId());
        Member member = memberRepository.findByName(memberName);
            if(member!=null){
                Long year = balanceSheetDto.getYear();
                Long memberId = member.getId();
                Optional<BalanceSheet> existingMemberWithYear = balanceSheetRepository.findByYearAndMemberId(year,memberId);
                if(existingMemberWithYear.isPresent()){
                    throw new DuplicateRecordFoundException("Balance sheet for Member is already present for the year"+year.toString());
                }
                BalanceSheet saveBalanceSheet = convertDtoToEntity(balanceSheetDto);
                Long yearlyPaidAmount = calculateYearlyPaidAmmount(saveBalanceSheet);
                Long yearlyPendingAmount = 1900-yearlyPaidAmount;
                saveBalanceSheet.setYearlyPaidAmount(yearlyPaidAmount);
                saveBalanceSheet.setYearlyPendingAmount(yearlyPendingAmount);
                saveBalanceSheet.setMember(member);
                try {
                    balanceSheetRepository.save(saveBalanceSheet);
                    updateDashboardSheet(1L);
                    updateMemberPaidAndPendingAmount(saveBalanceSheet.getMember().getName());
                }catch (Exception ex){
                    return ex.toString();
                }

                return "balance sheet added successfully";
            }
        throw new ResourceNotFoundException("Member is not present");
    }

    @Override
    public List<BalanceSheetDto> getBalanceSheet() {
        List<BalanceSheet> balanceSheetList= balanceSheetRepository.findAll();
        if(balanceSheetList.size()>=1){
            List<BalanceSheetDto> balanceSheetDtoList = new ArrayList<>();
            for(BalanceSheet balanceSheet:balanceSheetList){
                balanceSheetDtoList.add(convetEntitytoDto(balanceSheet));
            }
            return balanceSheetDtoList;
        }
        return Collections.emptyList();
    }

    @Override
    public BalanceSheetDto getBalanceSheetById(Long id) {
        Optional<BalanceSheet> optionalBalanceSheet = balanceSheetRepository.findById(id);
        if(optionalBalanceSheet.isPresent()){
            return convetEntitytoDto(optionalBalanceSheet.get());
        }
        throw new ResourceNotFoundException("Unable to find the Balance Sheet");
    }

    @Override
    public String updateBalanceSheet(BalanceSheetDto balanceSheetDto, Long balanceSheetId) {
        Optional<BalanceSheet> optionalBalanceSheet = balanceSheetRepository.findById(balanceSheetId);
        if(optionalBalanceSheet.isPresent())
        {
            Member existingMember = memberRepository.findByName(balanceSheetDto.getMemberName());
            if(existingMember != null){
                BalanceSheet balanceSheet = optionalBalanceSheet.get();
                balanceSheet = convertDtoToEntity(balanceSheetDto);
                balanceSheet.setId(optionalBalanceSheet.get().getId());
                Long yearlyPaidAmount = calculateYearlyPaidAmmount(balanceSheet);
                balanceSheet.setYearlyPaidAmount(yearlyPaidAmount);
                balanceSheet.setYearlyPendingAmount(1900-yearlyPaidAmount);
                balanceSheet.setMember(existingMember);
                balanceSheetRepository.save(balanceSheet);
                updateDashboardSheet(1L);
                updateMemberPaidAndPendingAmount(balanceSheetDto.getMemberName());

                return "Balance sheet update successfully";
            }
            else {
                throw new ResourceNotFoundException("User not found with name '"+balanceSheetDto.getMemberName()+"'.");
            }
        }
        throw new ResourceNotFoundException("Unable to find the Balance Sheet");
    }

    @Override
    public String deleteBalanceSheet(Long balanceSheetId) {
        Optional<BalanceSheet> optionalBalanceSheet = balanceSheetRepository.findById(balanceSheetId);
        if(optionalBalanceSheet.isPresent())
        {
            Optional<Member> member = memberRepository.findById(optionalBalanceSheet.get().getMember().getId()); //Check if member is present for the deleting balance sheet
            if(member.isPresent()){
                throw new DuplicateRecordFoundException("Can not delete balance sheet for active member");
            }
            try {
                balanceSheetRepository.delete(optionalBalanceSheet.get());
                return "Balance Sheet Deleted Successfully";
            }catch (Exception ex){
                return ex.toString();
            }
        }
        throw new ResourceNotFoundException("Unable to find the Balance Sheet");
    }

    //***
    //Update Dashboard data after each insertion and modification of balance sheet
    //***
    public String updateDashboardSheet(Long dashboarSheetId) {
        Optional<DashboardSheet> optionalDashboardSheet = dashboardSheetRepository.findById(dashboarSheetId);
        List<ExpenseSheet> expenseSheetList = expenseSheetRepository.findAll();
        List<BalanceSheet> balanceSheetList = balanceSheetRepository.findAll();

        Long totalRecievedAmount = calculateTotalRecievedAmount(balanceSheetList);
        Long totalPendingAmount = calculateTotalPendingAmount(balanceSheetList);
        Long totalExpenseAmount = calculateTotalExpenseAmount(expenseSheetList);
        Long totalAvailableAmount = totalRecievedAmount-totalExpenseAmount;

        DashboardSheet dashboardSheet = optionalDashboardSheet.get();
        dashboardSheet.setId(dashboarSheetId);
        dashboardSheet.setTotalExpendAmount(totalExpenseAmount);
        dashboardSheet.setTotalAvailableAmount(totalAvailableAmount);
        dashboardSheet.setTotalPendingAmount(totalPendingAmount);
        dashboardSheet.setTotalReceivedAmount(totalRecievedAmount);

        dashboardSheetRepository.save(dashboardSheet);
        return "dashbard updated successfully";

    }

    public Long calculateTotalRecievedAmount(List<BalanceSheet> balanceSheetList){

        return balanceSheetList.stream()
                .mapToLong(BalanceSheet::getYearlyPaidAmount)
                .sum();

    }

    public Long calculateTotalPendingAmount(List<BalanceSheet> balanceSheetList)
    {
        return balanceSheetList.stream()
                .mapToLong(BalanceSheet::getYearlyPendingAmount)
                .sum();
    }

    public Long calculateTotalExpenseAmount(List<ExpenseSheet> expenseSheetList){

        return expenseSheetList.stream().mapToLong(ExpenseSheet::getAmount).sum();
    }

    //---------------------
    public Long calculateYearlyPaidAmmount(BalanceSheet balanceSheet){
        List<Long> monthlyPaidAmountList = new ArrayList<>();
        monthlyPaidAmountList.add(balanceSheet.getJanuary() == null ? 0 : balanceSheet.getJanuary());
        monthlyPaidAmountList.add(balanceSheet.getFebruary() == null ? 0 : balanceSheet.getFebruary());
        monthlyPaidAmountList.add(balanceSheet.getMarch() == null ? 0 :balanceSheet.getMarch());
        monthlyPaidAmountList.add(balanceSheet.getApril() == null ? 0 :balanceSheet.getApril());
        monthlyPaidAmountList.add(balanceSheet.getMay() == null ? 0 :balanceSheet.getMay());
        monthlyPaidAmountList.add(balanceSheet.getJune() == null ? 0 :balanceSheet.getJune());
        monthlyPaidAmountList.add(balanceSheet.getJuly() == null ? 0 :balanceSheet.getJuly());
        monthlyPaidAmountList.add(balanceSheet.getAugust() == null ? 0 :balanceSheet.getAugust());
        monthlyPaidAmountList.add(balanceSheet.getSeptember() == null ? 0 :balanceSheet.getSeptember());
        monthlyPaidAmountList.add(balanceSheet.getOctober() == null ? 0 :balanceSheet.getOctober());
        monthlyPaidAmountList.add(balanceSheet.getNovember() == null ? 0 :balanceSheet.getNovember());
        monthlyPaidAmountList.add(balanceSheet.getDecember() == null ? 0 :balanceSheet.getDecember());
        monthlyPaidAmountList.add(balanceSheet.getOther1() == null ? 0: balanceSheet.getOther1());
        monthlyPaidAmountList.add(balanceSheet.getOther2() == null ? 0: balanceSheet.getOther2());

        return monthlyPaidAmountList.stream().mapToLong(n ->n).sum();

    }

    public BalanceSheet convertDtoToEntity(BalanceSheetDto balanceSheetDto)
    {
        BalanceSheet balanceSheet = new BalanceSheet();
        balanceSheet = (balanceSheetDto == null) ? null : modelMapper.map(balanceSheetDto,BalanceSheet.class);
        return balanceSheet;
    }

    public BalanceSheetDto convetEntitytoDto(BalanceSheet balanceSheet){

        BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
        balanceSheetDto.setMemberId(balanceSheet.getMember().getId());
        balanceSheetDto.setMemberName(balanceSheet.getMember().getName());
        balanceSheetDto.setId(balanceSheet.getId());
        balanceSheetDto.setYear(balanceSheet.getYear());
        balanceSheetDto.setJanuary(balanceSheet.getJanuary());
        balanceSheetDto.setFebruary(balanceSheet.getFebruary());
        balanceSheetDto.setMarch(balanceSheet.getMarch());
        balanceSheetDto.setApril(balanceSheet.getApril());
        balanceSheetDto.setMay(balanceSheet.getMay());
        balanceSheetDto.setJune(balanceSheet.getJune());
        balanceSheetDto.setJuly(balanceSheet.getJuly());
        balanceSheetDto.setAugust(balanceSheet.getAugust());
        balanceSheetDto.setSeptember(balanceSheet.getSeptember());
        balanceSheetDto.setOctober(balanceSheet.getOctober());
        balanceSheetDto.setNovember(balanceSheet.getNovember());
        balanceSheetDto.setDecember(balanceSheet.getDecember());
        balanceSheetDto.setYearlyPaidAmount(balanceSheet.getYearlyPaidAmount());
        balanceSheetDto.setYearlyPendingAmount(balanceSheet.getYearlyPendingAmount());
        balanceSheetDto.setLastUpdateTs(balanceSheet.getLastUpdateTs());
//        BalanceSheetDto balanceSheetDto = (balanceSheet == null) ? null : modelMapper.map(balanceSheet, BalanceSheetDto.class);
//        balanceSheetDto.setMember(balanceSheet.getMember().getId());
        return balanceSheetDto;
    }

    public void updateMemberPaidAndPendingAmount(String name){
        List<BalanceSheet> balanceSheetList = balanceSheetRepository.findByMemberName(name);
        Long paidAmount = balanceSheetList.stream().mapToLong(BalanceSheet::getYearlyPaidAmount).sum();
        Long pendingAmount = balanceSheetList.stream().mapToLong(BalanceSheet::getYearlyPendingAmount).sum();
        Member member = memberRepository.findByName(name);
        member.setPaidAmount(paidAmount);
        member.setPendingAmount(pendingAmount);
        memberRepository.save(member);
    }
}
