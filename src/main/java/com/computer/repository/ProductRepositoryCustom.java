package com.computer.repository;

import com.computer.dto.ProductSearchDto;
import com.computer.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> getAdminProductPage(ProductSearchDto searchDto, Pageable pageable) ;

}
