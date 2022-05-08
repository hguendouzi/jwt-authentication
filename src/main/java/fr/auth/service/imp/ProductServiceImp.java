package fr.auth.service.imp;

import fr.auth.dto.ProductDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.ProductMapper;
import fr.auth.model.Product;
import fr.auth.repository.ProductRepository;
import fr.auth.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author hicham
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {


    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ProductDto saveOrUpdateProduct(ProductDto productDto)
            throws GlobalException {
        Product product = repository.save(mapper.toProduct(productDto));
        return mapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> findAll() throws GlobalException {
        return mapper.toProductDtos((List<Product>) repository.findAll());
    }

    @Override
    public ProductDto findByName(String name) throws GlobalException {
        return mapper.toProductDto(repository.findByName(name));
    }

    @Override
    public List<ProductDto> deletProduct(String name) throws GlobalException {
        repository.deleteByName(name);
        return mapper.toProductDtos((List<Product>) repository.findAll());
    }

}
