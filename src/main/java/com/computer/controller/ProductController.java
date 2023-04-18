package com.computer.controller;

import com.computer.dto.ProductFormDto;
import com.computer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    @GetMapping(value = "/admin/products/new")
    public String productForm(Model model) {

        model.addAttribute("productFormDto", new ProductFormDto()) ;
        return "/product/prInsertForm" ;
    }

    private final ProductService productService ;

    @PostMapping(value = "/admin/products/new")
    public String productNew
            (@Valid ProductFormDto dto, BindingResult error, Model model, @RequestParam("productImageFile") List<MultipartFile> uploadedFile){

        if (error.hasErrors()) {
            return "/product/prInsertForm" ;
        }

        if (uploadedFile.get(0).isEmpty() && dto.getId() == null) {
            model.addAttribute("errorMessage", "첫 번째 이미지는 필수 입력 값입니다.") ;
            return "/product/prInsertForm" ;
        }

        try{
            productService.saveProduct(dto, uploadedFile) ;
        }catch (Exception err) {
            err.printStackTrace();
            model.addAttribute("errorMessage", "예외가 발생했습니다.") ;
            return "/product/prInsertForm" ;
        }

        return "redirect:/" ; // 메인 페이지로 이동
    }

}