package com.computer.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberFormDto {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "이메일 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Length(min = 4, max = 12, message = "비밀번호는 4자리 이상, 12자리 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "주소를 입력해주세요.")
    private String address;
    
}
