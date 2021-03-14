package fr.auth.service;

import java.util.List;

import fr.auth.dto.CategoryDto;
import fr.auth.exception.GlobalException;

/***
 * 
 * @author hicham
 *
 */
public interface CategoryService {
	
	/**
	 * save or update category
	 * @param dto
	 * @return
	 * @throws GlobalException
	 */
	CategoryDto saveOrUpdateCategory(CategoryDto dto) throws GlobalException ;
	
	/**
	 * find category by name
	 * @param name
	 * @return category
	 * @throws GlobalException
	 */
	CategoryDto findByName(String name) throws GlobalException;
	
	/**
	 * delete category and child orphan (product)
	 * @param name
	 * @return list catgeory
	 * @throws GlobalException
	 */
	List<CategoryDto> deleteByName(String name) throws GlobalException;
	
	/**
	 * find all category 
	 * @return list of category
	 * @throws GlobalException
	 */
	List<CategoryDto>findAllCategory()throws GlobalException;
	

}
