package com.electronic.store.controllers;

import com.electronic.store.dto.ApiMessage;
import com.electronic.store.dto.CategoryDto;
import com.electronic.store.dto.PageableResponse;
import com.electronic.store.dto.ProductDto;
import com.electronic.store.services.CategoryService;
import com.electronic.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    //create
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.create(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    @PostMapping("/{categoryId}/products")
    public ResponseEntity<ProductDto> createProductByCategory(@RequestBody ProductDto productDto,
                                                              @PathVariable("categoryId") int categoryId){
        ProductDto productDto1 = productService.createByCategory(productDto, categoryId);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable int categoryId,
            @RequestBody CategoryDto categoryDto
    ) {
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}/products/{productId}")
    public  ResponseEntity<ProductDto> updateCategoryOfProduct(@PathVariable int categoryId,
                                                               @PathVariable String productId){
        ProductDto productDto =productService.updateCategory(productId,categoryId);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiMessage> deleteCategory(
            @PathVariable int categoryId
    ) {
        categoryService.delete(categoryId);
        ApiMessage responseMessage = ApiMessage.builder().message("Category is deleted successfully !!").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir


    ) throws InterruptedException {
        //  Thread.sleep(1000);
        PageableResponse<CategoryDto> pageableResponse = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<PageableResponse<ProductDto>> getProductsByCategory(
            @PathVariable int categoryId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){

            PageableResponse<ProductDto> products = productService.getAllByCategory(categoryId, pageNumber,pageSize,sortBy,sortDir);
            return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingle(@PathVariable int categoryId) {
        CategoryDto categoryDto = categoryService.get(categoryId);
        return ResponseEntity.ok(categoryDto);
    }
}
