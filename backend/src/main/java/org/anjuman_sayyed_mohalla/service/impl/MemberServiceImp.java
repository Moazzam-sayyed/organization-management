package org.anjuman_sayyed_mohalla.service.impl;

import org.anjuman_sayyed_mohalla.exception.DuplicateRecordFoundException;
import org.anjuman_sayyed_mohalla.exception.ResourceNotFoundException;
import org.anjuman_sayyed_mohalla.model.dto.MemberDto;
import org.anjuman_sayyed_mohalla.model.entities.BalanceSheet;
import org.anjuman_sayyed_mohalla.model.entities.Member;
import org.anjuman_sayyed_mohalla.repositories.BalanceSheetRepository;
import org.anjuman_sayyed_mohalla.repositories.MemberRepository;
import org.anjuman_sayyed_mohalla.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BalanceSheetRepository balanceSheetRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MemberDto> getMember() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        if(memberList.size()>=1){
            for(Member member:memberList){
                MemberDto memberDto = convertToDto(member);
                memberDtoList.add(memberDto);
            }
            return memberDtoList;
        }
        return List.of();
    }

    @Override
    public String addMember(MemberDto memberDto) {
        if(memberDto!=null){
            checkDuplicate(memberDto.getName());
            Member member = convertToEntity(memberDto);
            /* Intital paid/pending amount zero */
            member.setPaidAmount(0L);
            member.setPendingAmount(0L);
             memberRepository.save(member);
             return "Member added sucesfully";
        }
        else
            return "unable to add memeber";
    }

    @Override
    public String updateMember(MemberDto memberDto, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isPresent()){
            Member member = convertToEntity(memberDto);
            member.setId(optionalMember.get().getId());
            memberRepository.save(member);
            return "Member updated successfully";
        }
        throw new ResourceNotFoundException("Unable To Find Member");
    }

    @Override
    public String deleteMember(Long memberId) {
        Optional<Member>  optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isPresent()){
            try{
                memberRepository.delete(optionalMember.get());
                return "Member delete successfully";
            }catch (Exception ex)
            {
                return ex.toString();
            }
        }
        throw new ResourceNotFoundException("Unable To Find Member");
    }

    public void checkDuplicate(String name){
        Member member = memberRepository.findByName(name);
        if(member!=null){
            throw new DuplicateRecordFoundException("Member is present with name: '"+name+"'.");
        }
    }
    public Member convertToEntity(MemberDto memberDto){
        return modelMapper.map(memberDto,Member.class);
    }

    public MemberDto convertToDto(Member member) { return modelMapper.map(member, MemberDto.class);}
}
