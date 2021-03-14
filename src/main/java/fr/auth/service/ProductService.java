package fr.auth.service;

import java.util.List;

import fr.auth.dto.ProductDto;
import fr.auth.exception.GlobalException;

/**
 * 
 * @author hicham
 *
 */
public interface ProductService {
   
	/**
	 * save or update product
	 * @param productDto
	 * @return productDto with ID
	 * @throws GlobalException
	 */
	ProductDto saveOrUpdateProduct(ProductDto productDto) throws GlobalException;
    
	
	/**
	 * find all Product
	 * @return Productdto
	 * @throws GlobalException
	 */
	List<ProductDto> findAll() throws GlobalException;
    
	/**
	 * find product by name
	 * @param name
	 * @return product 
	 * @throws GlobalException
	 */
	ProductDto findByName(String name) throws GlobalException;
    
	/**
	 * delete product by name
	 * @param name
	 * @return list of the rest product 
	 * @throws GlobalException
	 */
	List<ProductDto> deletProduct(String name) throws GlobalException;


}
