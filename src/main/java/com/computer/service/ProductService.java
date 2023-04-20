package com.computer.service;

import com.computer.dto.ProductFormDto;
import com.computer.dto.ProductImageDto;
import com.computer.dto.ProductSearchDto;
import com.computer.entity.Product;
import com.computer.entity.ProductImage;
import com.computer.repository.ProductImageRepository;
import com.computer.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository ;
    private final ProductImageService productImageService ;

    // 상품 등록
    public Long saveProduct(ProductFormDto dto, List<MultipartFile> uploadedFile) throws Exception {

        Product product= dto.createProduct() ;
        productRepository.save(product) ;

        // 상품에 들어가는 각 이미지들
        for (int i = 0; i < uploadedFile.size(); i++) {
            ProductImage productImage = new ProductImage() ;
            productImage.setProduct(product);

            if (i == 0) {
                productImage.setRepImageYesNo("YES");
            }else {
                productImage.setRepImageYesNo("No");
            }

            productImageService.saveProductImage(productImage, uploadedFile.get(i));
        }

        return product.getId().longValue() ;
    }

    private final ProductImageRepository productImageRepository ;

    // 등록된 상품 정보를 읽어 들입니다.
    public ProductFormDto getProductDetail(Long productId) {

        List<ProductImage> productImageList = productImageRepository.findByProductIdOrderByIdAsc(productId) ;

        List<ProductImageDto> productImageDtoList = new ArrayList<ProductImageDto>() ;

        for (ProductImage productImg : productImageList) {

            ProductImageDto productImgDto = ProductImageDto.of(productImg) ;
            productImageDtoList.add(productImgDto);
        }

        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new) ;

        ProductFormDto dto = ProductFormDto.of(product) ;

        dto.setProductImageDtoList(productImageDtoList);

        return dto ;
    }

    public Long updateProduct(ProductFormDto dto, List<MultipartFile> uploadFile) throws Exception {

        Product product = productRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new) ;

        // 화면에서 넘어온 데이터를 Entity에게 전달
        product.updateProduct(dto);

        // 5 개의 이미지들에 대한 아이디 목록
        List<Long> productImageIds = dto.getProductImageIds() ;

        for (int i = 0; i < uploadFile.size(); i++) {

            productImageService.updateProductImage(productImageIds.get(i), uploadFile.get(i)) ;

        }

        return product.getId() ;
    }

    public Page<Product> getAdminProductPage(ProductSearchDto dto, Pageable pageable) {
        // 상품 검색 조건 dto와 페이징 객체 pageable를 사용해서 페이징 객체를 구함
        return productRepository.getAdminProductPage(dto, pageable) ;
    }


}
