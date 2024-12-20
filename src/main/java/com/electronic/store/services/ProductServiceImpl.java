package com.electronic.store.services;

import com.electronic.store.dto.ProductDto;
import com.electronic.store.entities.Product;
import com.electronic.store.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        return modelMapper.map(product , ProductDto.class);
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
    public List<ProductDto> getAllLive() {
        List<Product> products = productRepository.findByLiveTrue();
        List<ProductDto> productDtos = products.stream().map((product) -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDto> searchByTitle(String subTitle) {
       List<Product> products = productRepository.findByTitleContaining(subTitle);
        List<ProductDto> productDtos = products.stream().map((product) -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }
}
