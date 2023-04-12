package com.computer.repository;

import com.computer.constant.ProductStatus;
import com.computer.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository ;

    @Test
    @DisplayName("테스트")
    public void createProductTest(){

        Product product = new Product() ;

        product.setProductStatus(ProductStatus.SELL);
        // product.setId();
        product.setName("노트북");
        product.setPrice(1000);
        product.setStock(999);
        product.setDescription("가성비 굳");
        product.setRegDate(LocalDateTime.now());
        product.setUpdateDate(LocalDateTime.now());

        Product saveItem = productRepository.save(product) ;
        System.out.println(saveItem.toString());
    }


}
