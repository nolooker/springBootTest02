package com.computer.controller;

import com.computer.dto.MemberFormDto;
import com.computer.entity.Member;
import com.computer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService ;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model){

        model.addAttribute("memberFormDto", new MemberFormDto());

        return "/member/memberForm" ;

    }

    @PostMapping("/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "/member/memberForm" ;
        }

        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member) ;

        }catch (IllegalStateException e){
            model.addAttribute("errMessage", e.getMessage()) ;
            return "/member/memberForm" ;
        }

        System.out.println("포스트 방식 요청");
        return "redirect:/" ;

    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm" ;
    }

    //SecurityConfig.java 파일에 정의 됨
    @GetMapping(value = "/login/error")
    public String loginError(Model model){

        model.addAttribute("loginErrorMsg", "이메일 또는 비밀번호를 확인해주세요.");
        return "/member/memberLoginForm" ;
    }

}
