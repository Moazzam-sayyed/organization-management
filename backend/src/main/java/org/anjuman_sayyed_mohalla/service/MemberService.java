package org.anjuman_sayyed_mohalla.service;

import org.anjuman_sayyed_mohalla.model.dto.MemberDto;
import org.anjuman_sayyed_mohalla.model.entities.Member;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MemberService {

    public List<MemberDto> getMember();

    public String addMember(MemberDto memberDto);

    public String updateMember(MemberDto memberDto,Long memberId);

    public String deleteMember(Long memberId);

}
