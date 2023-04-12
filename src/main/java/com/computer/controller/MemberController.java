package com.computer.controller;

import com.computer.dto.MemberFormDto;
import com.computer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService ;

    @GetMapping("/new")
    public String memberForm(Model model){

        model.addAttribute("memberFormDto", new MemberFormDto());

        return "/member/memberForm" ;

    }

    @PostMapping("/new")
    public String newMember(){

        System.out.println("포스트 방식 요청");
        return "redirect:/" ;

    }

}
