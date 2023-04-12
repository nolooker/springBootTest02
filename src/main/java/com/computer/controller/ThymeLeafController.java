package com.computer.controller;

import com.computer.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeLeafController {

    // http://localhost:8999/thymeleaf/ex01
    @GetMapping(value = "/ex01")
    public String thymeleafExample01(Model model){

        model.addAttribute("data", "타임리프 테스트") ;

        return "thymeleafEx/viewEx01" ;

    }

    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model){

        ProductDto bean = new ProductDto();

        bean.setDescription("가성비가 좋아요");
        bean.setName("맥북");
        bean.setPrice(3000);
        bean.setRegDate(LocalDateTime.now());
        bean.setUpdateDate(LocalDateTime.now());

        model.addAttribute("bean", bean) ;

        return "thymeleafEx/viewEx02" ;

    }

    @GetMapping(value = "/ex03")
    public String thymeleafExample03(Model model){

        List<ProductDto> beanList = new ArrayList<ProductDto>() ;

        for (int i = 1; i < 11; i++) {

            ProductDto bean = new ProductDto();

            bean.setDescription("가성비가 좋아요");
            bean.setName("맥북" + i);
            bean.setPrice(12345);
            bean.setRegDate(LocalDateTime.now());
            bean.setUpdateDate(LocalDateTime.now());

            beanList.add(bean) ;

        }

        model.addAttribute("beanList",beanList) ;
        return "thymeleafEx/viewEx03" ;

    }

    @GetMapping(value = "/ex04")
    public String thymeleafExample04(Model model){

        List<ProductDto> beanList = new ArrayList<ProductDto>() ;

        for (int i = 1; i < 11; i++) {

            ProductDto bean = new ProductDto();

            bean.setDescription("가성비가 좋아요");
            bean.setName("갤럭시북" + i);
            bean.setPrice(12345);
            bean.setRegDate(LocalDateTime.now());
            bean.setUpdateDate(LocalDateTime.now());

            beanList.add(bean) ;

        }

        model.addAttribute("beanList",beanList) ;
        return "thymeleafEx/viewEx04" ;

    }

    @GetMapping(value = "/ex05")
    public String thymeleafExample05(Model model){

        List<ProductDto> beanList = new ArrayList<ProductDto>() ;

        for (int i = 1; i < 11; i++) {

            ProductDto bean = new ProductDto();

            bean.setDescription("가성비가 좋아요");
            bean.setName("맥북" + i);
            bean.setPrice(12345);
            bean.setRegDate(LocalDateTime.now());
            bean.setUpdateDate(LocalDateTime.now());

            beanList.add(bean) ;

        }

        model.addAttribute("beanList",beanList) ;
        return "thymeleafEx/viewEx05" ;

    }

    @GetMapping(value = "/ex06")
    public String thymeleafExample06(Model model){

        return "thymeleafEx/viewEx06" ;
    }

    @GetMapping(value = "/ex07")
    public String thymeleafExample07(String param1, String param2, Model model){

        if(param1 == null) {param1 = "abcde" ; }
        if(param2 == null) {param2 = "qwert" ; }

        model.addAttribute("param1",param1) ;
        model.addAttribute("param2",param2) ;

        return "thymeleafEx/viewEx07" ;
    }

    @GetMapping(value = "/ex08")
    public String thymeleafExample08(Model model){

        return "thymeleafEx/viewEx08" ;
    }

}
