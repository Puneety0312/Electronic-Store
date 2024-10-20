package com.electronic.store.services;

import com.electronic.store.dto.ProductDto;

import java.util.List;

public interface ProductService
{
    ProductDto create(ProductDto productDto);
    ProductDto update(ProductDto productDto, String id);
    ProductDto get(String id);
    List<ProductDto> getAll();
    List<ProductDto> getAllLive();
    List<ProductDto> searchByTitle(String subTitle);
}
