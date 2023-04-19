package com.computer.dto;

import com.computer.constant.ProductStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductSearchDto {

    private String searchDateType ;

    private ProductStatus productStatus ;

    private String searchBy ;

    private String searchQuery ;

}
