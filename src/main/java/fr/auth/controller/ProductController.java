package fr.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.auth.dto.ProductDto;
import fr.auth.exception.GlobalException;
import fr.auth.service.ProductService;

/**
 * 
 * @author hicham
 *
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	
	@Autowired
	private ProductService service;
    
	/**
	 * save or update product
	 * @param productDto
	 * @return
	 * @throws GlobalException
	 */
	@PostMapping(value = "/create")
	@PreAuthorize("hasAuthority('ADMIN')")
	public @ResponseBody ProductDto createProduct(@RequestBody ProductDto productDto) throws GlobalException {
		return service.saveOrUpdateProduct(productDto);
	}
    
	/**
	 * get all products
	 * @return list of product
	 * @throws GlobalException
	 */
	@GetMapping(value = "/all")
	public @ResponseBody List<ProductDto> findAll() throws GlobalException {
		return  service.findAll();
	}
    
	/**
	 * find product by name
	 * @param name
	 * @return
	 * @throws GlobalException
	 */
	@GetMapping(value = "/{name}")
	public @ResponseBody ProductDto findProductByName(@PathVariable String name) throws GlobalException {
		return service.findByName(name);
	}

    /**
     * delete product by name
     * @param name
     * @return list of the rest prduct
     * @throws GlobalException
     */
	@DeleteMapping(value = "/delete/{name}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public @ResponseBody List<ProductDto> deleteProduct(@PathVariable String name) throws GlobalException {
		return service.deletProduct(name);
	}

}
