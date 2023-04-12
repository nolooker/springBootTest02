package com.computer.repository;

import com.computer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    List<Product> findProductByName(String name) ;

    List<Product> findByPriceLessThan(Integer price) ;

    List<Product> findByPriceLessThanOrderByPriceDesc(Integer price) ;

    @Query("select i from Product i where i.description like " +
            "%:description% order by i.price desc ")
    List<Product> findByProductDetail01(@Param("description") String description);

    @Query(value = "select * from Products i where i.description like " +
            "%:description% order by i.price desc ", nativeQuery = true)
    List<Product> findByProductDetail02(@Param("description") String description);

}
