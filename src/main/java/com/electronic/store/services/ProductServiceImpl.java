package com.electronic.store.services;

import com.electronic.store.dto.Helper;
import com.electronic.store.dto.PageableResponse;
import com.electronic.store.dto.ProductDto;
import com.electronic.store.entities.Category;
import com.electronic.store.entities.Product;
import com.electronic.store.repositories.CategoryRepository;
import com.electronic.store.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

  //  @Autowired
  //  private Helper helper;

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        //added
        product.setDate(new Date());
        productRepository.save(product);
        return modelMapper.map(product , ProductDto.class);
    }

    @Override
    public ProductDto createByCategory(ProductDto productDto, int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Id not found"));
        Product product = modelMapper.map(productDto, Product.class);
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        //added
        product.setDate(new Date());
        product.setCategory(category);
        productRepository.save(product);
        return modelMapper.map(product , ProductDto.class);
    }

    @Override
    public ProductDto updateCategory(String productId, int categoryId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product cannot be found"));
        Category category= categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("Category cannot be found"));
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountPrice(productDto.getDiscountPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        product.setProductImage(productDto.getProductImage());
        Product product1 = productRepository.save(product);
        return modelMapper.map(product1, ProductDto.class);
    }

    @Override
    public ProductDto get(String id) {
       Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
       return modelMapper.map(product, ProductDto.class);
    }
    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map((product) -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public PageableResponse<ProductDto> getAllByCategory(int categoryId, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Id not found"));
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> products = productRepository.findByCategory(category, pageable);
        return Helper.getPageableResponse(products , ProductDto.class);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

//    @Override
//    public List<ProductDto> getAllLive() {
//        List<Product> products = productRepository.findByLiveTrue();
//        List<ProductDto> productDtos = products.stream().map((product) -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
//        return productDtos;
//    }

    @Override
    public List<ProductDto> searchByTitle(String subTitle) {
       List<Product> products = productRepository.findByTitleContaining(subTitle);
        List<ProductDto> productDtos = products.stream().map((product) -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }


}
