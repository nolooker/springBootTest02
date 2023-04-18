package com.computer.entity;

import com.computer.constant.ProductStatus;
import com.computer.dto.ProductFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter @Setter @ToString
public class Product extends BaseEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;

    @Column(nullable = false, length = 50)
    private String name ;

    @Column(nullable = false, name = "price")
    private Integer price ;

    @Column(nullable = false)
    private Integer stock ;

    @Lob
    @Column(nullable = false)
    private String description ;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus ;

//    private LocalDateTime regDate ;
//    private LocalDateTime updateDate ;

    public void updateProduct(ProductFormDto productFormDto) {
        this.name = productFormDto.getName() ;
        this.price = productFormDto.getPrice() ;
        this.stock = productFormDto.getStock() ;
        this.description = productFormDto.getDescription() ;
        this.productStatus = productFormDto.getProductStatus() ;

    }

}
