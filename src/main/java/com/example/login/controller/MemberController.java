package com.example.login.controller;

import com.example.login.domain.Member;
import com.example.login.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getMember() {
        return memberService.findByAll();
    }

    @GetMapping("/{id}")
    public Optional<Member> getMember(@PathVariable("id") long id) {
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable("id") long id, @RequestBody Member memberReq) {
        Member member = memberService.findById(id).orElseThrow(NoSuchElementException::new);
        member.setNickname(memberReq.getNickname());
        member.setPassword(memberReq.getPassword());
        return memberService.save(member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable("id") long id) {
        Member member = memberService.findById(id).orElseThrow(NoSuchElementException::new);
        memberService.delete(member);
    }
}
