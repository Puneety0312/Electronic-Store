package com.electronic.store.services;

import com.electronic.store.dto.ApiMessage;
import com.electronic.store.dto.ProductDto;

import java.util.List;

public interface ProductService
{
    ProductDto create(ProductDto productDto);
    ProductDto createByCategory(ProductDto productDto, int categoryId);
    ProductDto updateCategory(String productId, int categoryId);
    ProductDto update(ProductDto productDto, String id);
    ProductDto get(String id);
    List<ProductDto> getAll();
    void delete(String id);
   // List<ProductDto> getAllLive();
    List<ProductDto> searchByTitle(String subTitle);
}
