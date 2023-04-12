package com.computer.repository;

import com.computer.constant.ProductStatus;
import com.computer.entity.Product;
import com.computer.entity.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest02 {

    @Autowired
    ProductRepository productRepository ;

    @Test
    @DisplayName("상품 여러개 추가하기")
    public void createProductTestMany() {

        String[] computer = {"노트북", "컴퓨터", "맥북", "갤럭시북"};
        String[] description = {"가성비 좋아요", "성능최고", "쓸만해요", "가성비 값"};
        Integer[] stock = {100, 200, 300, 400};
        Integer[] price = {1000, 2000, 3000, 4000, 5000};

        for (int i = 0; i < 10; i++) {
            Product product = new Product();

            product.setName(computer[i % computer.length]);
            product.setPrice(price[i % price.length]);
            product.setDescription(description[i % description.length]);
            product.setProductStatus(ProductStatus.SELL);
            product.setStock(stock[i % stock.length]);
            product.setRegDate(LocalDateTime.now());
            product.setUpdateDate(LocalDateTime.now());

            Product savedItem = productRepository.save(product);
            System.out.println(savedItem.toString());
        }
    }

    @Test
    @DisplayName("상품명으로 조회 테스트")
    public void findProductByNameTest(){
        List<Product> itemList = productRepository.findProductByName("노트북") ;
        for (Product product : itemList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("@Query 메소드를 사용한 상품 조회 01")
    public void findByProductDetail01(){
        List<Product> itemList = productRepository.findByProductDetail01("성") ;
        for (Product product : itemList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("@Query 메소드를 사용한 상품 조회 02")
    public void findByProductDetail02(){
        List<Product> itemList = productRepository.findByProductDetail02("성") ;
        for (Product product : itemList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("특정 가격 이하 상품들만 조회 테스트")
    public void findByPriceLessThanTest(){
        List<Product> itemList = productRepository.findByPriceLessThan(2000) ;
        for (Product product : itemList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("특정 가격 이하 상품들 조회, 가격 높은 순으로 조회")
    public void findByPriceLessThanTestOrderByPriceDesc(){
        List<Product> itemList = productRepository.findByPriceLessThanOrderByPriceDesc(300) ;
        for (Product product : itemList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("데이터 생성(for QuerySQL)")
    public void createProductListNew(){
        String[] computer = {"노트북", "컴퓨터", "맥북", "갤럭시북"};
        String[] description = {"가성비 좋아요", "성능최고", "쓸만해요", "가성비 값"};
        Integer[] stock = {100, 200, 300, 400};
        Integer[] price = {1000, 2000, 3000, 4000, 5000};

        for (int i = 0; i < 10; i++) {
            Product product = new Product();

            product.setName(computer[i % computer.length]);
            product.setPrice(price[i % price.length]);
            product.setDescription(description[i % description.length]);
            product.setProductStatus(ProductStatus.SELL);
            product.setStock(stock[i % stock.length]);
            product.setRegDate(LocalDateTime.now());
            product.setUpdateDate(LocalDateTime.now());

            Product savedItem = productRepository.save(product);
            System.out.println(savedItem.toString());
        }
    }

    // EntityManager를 주입해주는 어노테이션
    @PersistenceContext // JPA가 동작하는 영속성 작업 구간
            EntityManager em ; // 엔티티를 관리해주는 관리자

    @Test
    @DisplayName("query Dsl Test01")
    public void queryDslTest01(){
        // 현재 판매중(SELL)인 상품 중에서, 상품 설명에 '아'자가 들어 있는 상품들 조회
        // 가격의 역순으로 출력
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QProduct qbean = QProduct.product ;

        JPAQuery<Product> query = queryFactory
                .selectFrom(qbean)
                .where(qbean.productStatus.eq(ProductStatus.SELL))
                .where(qbean.description.like("%" + "성" + "%"))
                .orderBy(qbean.price.desc()) ;

        List<Product> itemList = query.fetch() ;

        for (Product product : itemList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("query Dsl Test02")
    public void queryDslTest02(){
        // 판매 중인 상품 중에서 단가가 200원 초과이고, 상품 설명에 '어'가 들어 있는 상품을 조회
        // 1페이지에 2개씩 볼건데, 2페이지를 조회해보자
        BooleanBuilder booleanBuilder = new BooleanBuilder() ;
        QProduct product = QProduct.product ;

        int price = 200 ;
        String description = "어" ;

        booleanBuilder.and(product.description.like("%" + description + "%")) ;
        booleanBuilder.and(product.price.gt(price)) ;
        booleanBuilder.and(product.productStatus.eq(ProductStatus.SELL)) ;

        Pageable pageable = PageRequest.of(0, 4) ;
        Page<Product> result = productRepository.findAll(booleanBuilder, pageable) ;
        System.out.println("total elements : " + result.getTotalElements());

        List<Product> productList = result.getContent();
        for (Product item : productList) {
            System.out.println(item.toString());
        }
    }












}
