package com.ozkan.business.service.Impl;

import com.ozkan.business.dto.ProductDto;
import com.ozkan.business.service.IProductService;
import com.ozkan.configuration.beanconfig.ModelMapperBean;
import com.ozkan.data.entity.Product;
import com.ozkan.data.repository.IProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService<ProductDto, Product> {

    private final IProductRepository productRepository;
    private final ModelMapperBean modelMapper;

    @Override
    public ProductDto entityToDto(Product product) {
        return modelMapper.modelMapperMethod().map(product, ProductDto.class);
    }

    @Override
    public Product dtoToEntity(ProductDto productDto) {
        return modelMapper.modelMapperMethod().map(productDto,Product.class);
    }

    @Override
    @Transactional
    @SneakyThrows
    public ProductDto productServiceCreate(ProductDto productDto) {
        if(productDto!=null){
            Product product = dtoToEntity(productDto);
            productRepository.save(product);
            productDto.setProductId(product.getProductId());
        }else{
            throw new Exception("Product Dto is null!");
        }
        return productDto;
    }

    @Override
    public List<ProductDto> productServiceList() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for(Product p:productList){
            productDtoList.add(entityToDto(p));
        }
        return productDtoList;
    }

    @Override
    @SneakyThrows
    public ProductDto productServiceFindById(Long id) {
        if(productRepository.findByProductId(id)!=null){
            return entityToDto(productRepository.findByProductId(id).get());
        }else{
            throw new Exception("No product with "+id+" id");
        }


    }

    @Override
    public ProductDto productServiceUpdateById(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    @Transactional
    @SneakyThrows
    public ProductDto productServiceDeleteById(Long id) {
        ProductDto dtoFind = productServiceFindById(id);
        Product entityToDelete = dtoToEntity(dtoFind);

        if(entityToDelete!=null){
            productRepository.delete(entityToDelete);
            return dtoFind;
        }else{
            throw new Exception("Can not find product with "+id+" id");
        }
    }
}
