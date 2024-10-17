package com.electronic.store.services;

import com.electronic.store.dto.CategoryDto;
import com.electronic.store.dto.PageableResponse;
public interface CategoryService {
    //create
    CategoryDto create(CategoryDto categoryDto);
    CategoryDto update(CategoryDto categoryDto, int categoryId);
    //delete
    void delete(int categoryId);
    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);
    //get single category detail
    CategoryDto get(int categoryId);
}
