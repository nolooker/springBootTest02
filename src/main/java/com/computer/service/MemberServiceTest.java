package com.computer.service;

import com.computer.dto.MemberFormDto;
import com.computer.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService ;

//    @Autowired(required = false)
//    PasswordEncoder passwordEncoder ;

    private Member createMember(){

        MemberFormDto dto = new MemberFormDto();

        dto.setName("가나다");
        dto.setEmail("Test@google.com");
        dto.setPassword("1234");
        dto.setAddress("서울");

        return Member.createMember(dto, null);
    }

    @Test
    @DisplayName("회원 가입 테스트")
    public void saveMember(){
        Member member = createMember();

        Member savedMember = memberService.saveMember(member);

        savedMember.setEmail("abc@naver.com");

        Assertions.assertEquals(member.getEmail(), savedMember.getEmail());
        Assertions.assertEquals(member.getName(), savedMember.getName());
    }







}
