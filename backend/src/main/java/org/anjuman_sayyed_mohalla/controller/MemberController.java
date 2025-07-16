package org.anjuman_sayyed_mohalla.controller;

import org.anjuman_sayyed_mohalla.model.dto.MemberDto;
import org.anjuman_sayyed_mohalla.model.entities.Member;
import org.anjuman_sayyed_mohalla.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringValueResolver;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/anjuman/v1")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<List<MemberDto>> getMember(){
        List<MemberDto> result = memberService.getMember();
        return new ResponseEntity<List<MemberDto>>(result,HttpStatus.OK);
    }

    @PostMapping("/member")
    public ResponseEntity<String> addMember(@RequestBody MemberDto member){
        String result = memberService.addMember(member);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @PutMapping("/member")
    public ResponseEntity<String> updateMember(@RequestBody MemberDto memberDto, @RequestParam Long memberId){
        String result = memberService.updateMember(memberDto,memberId);
        return new ResponseEntity<String>(result,HttpStatus.OK);
    }

    @DeleteMapping("/member")
    public ResponseEntity<String> deleteMember(@RequestParam Long memberId){
        String result = memberService.deleteMember(memberId);
        return new ResponseEntity<String>(result,HttpStatus.OK);

    }
}
