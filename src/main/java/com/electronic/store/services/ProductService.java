package com.electronic.store.services;

import com.electronic.store.dto.ApiMessage;
import com.electronic.store.dto.PageableResponse;
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
    PageableResponse<ProductDto> getAllByCategory(int categoryId, int pageNumber, int pageSize, String sortBy, String sortDir);

  //  PageableResponse<ProductDto> getAllByCategory(int categoryId);

    void delete(String id);
   // List<ProductDto> getAllLive();
    List<ProductDto> searchByTitle(String subTitle);
}
