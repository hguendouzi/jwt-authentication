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

import fr.auth.dto.CategoryDto;
import fr.auth.exception.GlobalException;
import fr.auth.service.CategoryService;

/**
 * 
 * @author hicham
 *
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	
	/**
	 * save or update category
	 * @param categoryDto
	 * @return
	 * @throws GlobalException
	 */
	@PostMapping(value = "/create")
	@PreAuthorize("hasAuthority('ADMIN')")
	public @ResponseBody CategoryDto createCategory( @RequestBody CategoryDto categoryDto) throws GlobalException {
		return categoryService.saveOrUpdateCategory(categoryDto);
		
	}
	
	/**
	 * get all list of category
	 * @return
	 * @throws GlobalException
	 */
	@GetMapping(value = "/all")
	public @ResponseBody  List<CategoryDto> findAll() throws GlobalException {
		return categoryService.findAllCategory();
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws GlobalException
	 */
	@GetMapping(value = "/{name}")
	public @ResponseBody  CategoryDto findCategoryByName(@PathVariable String name) throws GlobalException {
		return categoryService.findByName(name);
	}
	
	/**
	 * delete category by name
	 * @param name
	 * @return
	 * @throws GlobalException
	 */
	@DeleteMapping(value = "/delete/{name}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public @ResponseBody  List<CategoryDto>deleteCatgory(@PathVariable String name) throws GlobalException {
		return categoryService.deleteByName(name);
	}


}
