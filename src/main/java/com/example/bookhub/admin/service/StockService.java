package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.Product;
import com.example.bookhub.admin.dto.StockFilter;
import com.example.bookhub.admin.mapper.StockMapper;
import com.example.bookhub.admin.vo.Stock;
import com.example.bookhub.product.vo.Author;
import com.example.bookhub.product.vo.Publisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockMapper stockMapper;

    public int getTotalRows(StockFilter filter) {
        return stockMapper.getTotalRows(filter);
    }

    public List<Stock> getStockNotifications(StockFilter filter, int offset, int limit) {
        return stockMapper.getStockNotifications(filter, offset, limit);
    }

    public List<Publisher> getPublishers() {
        return stockMapper.getPublishers();
    }

    public List<Author> getAuthors() {
        return stockMapper.getAuthors();
    }

    public void deleteProductByNo(List<Long> deletedProductNos) {
        for (Long deletedProductNo : deletedProductNos) {
            stockMapper.deleteProductByNo(deletedProductNo);
        }
    }

    public Product getProductByNo(Long productNo) {
        return stockMapper.getProductByNo(productNo);
    }

    public Long getSuperCategoryNoBySubCategoryNo(Long categoryNo) {
        return stockMapper.getSuperCategoryNoBySubCategoryNo(categoryNo);
    }

    public void modifyProduct(Product modifiedProduct) {
        stockMapper.modifyProduct(modifiedProduct);
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
            stockMapper.registerProduct(product);
            stockMapper.registerImage(product);
            return;
        }
        if (secondCategoryNo != 0) {
            product.setCategoryNo(secondCategoryNo);
            stockMapper.registerProduct(product);
            stockMapper.registerImage(product);
            return;
        }

        product.setCategoryNo(topCategoryNo);
        stockMapper.registerProduct(product);
        stockMapper.registerImage(product);
    }
}
