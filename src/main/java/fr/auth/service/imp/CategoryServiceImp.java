package fr.auth.service.imp;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.auth.dto.CategoryDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.CategoryMapper;
import fr.auth.model.Category;
import fr.auth.repository.CategoryRepository;
import fr.auth.service.CategoryService;


/**
 * 
 * @author hicham
 *
 */
@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	

	 private final CategoryMapper mapper = Mappers.getMapper( CategoryMapper.class );

	@Override
	public CategoryDto saveOrUpdateCategory(CategoryDto dto)
			throws GlobalException {
		Category category = repository.save(mapper.toCategory(dto));
		return mapper.toCategoryDto(category);
	}

	@Override
	public CategoryDto findByName(String name) throws GlobalException {
		return mapper.toCategoryDto(repository.findByName(name));
		 
	}

	@Override
	public List<CategoryDto> deleteByName(String name) throws GlobalException {
		repository.deleteByName(name);
		return mapper.toListDto((List<Category>) repository.findAll());
	}

	@Override
	public List<CategoryDto> findAllCategory() throws GlobalException {
		return mapper.toListDto((List<Category>) repository.findAll());
	}

}
