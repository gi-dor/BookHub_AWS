package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.BookList;
import com.example.bookhub.admin.dto.Product;
import com.example.bookhub.admin.dto.ProductFilter;
import com.example.bookhub.admin.mapper.ProductMapper;
import com.example.bookhub.product.vo.Author;
import com.example.bookhub.product.vo.Publisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public int getTotalRows(ProductFilter filter) {
        return productMapper.getTotalRows(filter);
    }

    public List<BookList> getBooks(ProductFilter filter, int offset, int limit, String sort) {
        return productMapper.getBooks(filter, offset, limit, sort);
    }

    public List<Publisher> getPublishers() {
        return productMapper.getPublishers();
    }

    public List<Author> getAuthors() {
        return productMapper.getAuthors();
    }

    public void deleteProductByNo(List<Long> deletedProductNos) {
        for (Long deletedProductNo : deletedProductNos) {
            productMapper.deleteProductByNo(deletedProductNo);
        }
    }

    public Product getProductByNo(Long productNo) {
        return productMapper.getProductByNo(productNo);
    }

    public Long getSuperCategoryNoBySubCategoryNo(Long categoryNo) {
        return productMapper.getSuperCategoryNoBySubCategoryNo(categoryNo);
    }

    public void modifyProduct(Product modifiedProduct) {
        productMapper.modifyProduct(modifiedProduct);
    }

    public void createProduct(Product createdProduct) {
        Product product = new Product();
        product = createdProduct;
        long topCategoryNo = createdProduct.getTopCategoryNo();
        long secondCategoryNo = createdProduct.getSecondCategoryNo();
        long thirdCategoryNo = createdProduct.getThirdCategoryNo();
        int height = createdProduct.getHeight();
        int width = createdProduct.getWidth();
        String size = height + "*" + width;

        product.setSize(size);

        if (thirdCategoryNo != 0) {
            product.setCategoryNo(thirdCategoryNo);
            productMapper.registerProduct(product);
            productMapper.registerImage(product);
            return;
        }
        if (secondCategoryNo != 0) {
            product.setCategoryNo(secondCategoryNo);
            productMapper.registerProduct(product);
            productMapper.registerImage(product);
            return;
        }

        product.setCategoryNo(topCategoryNo);
        productMapper.registerProduct(product);
        productMapper.registerImage(product);
    }
}
