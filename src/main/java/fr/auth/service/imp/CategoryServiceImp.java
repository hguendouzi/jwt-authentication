package fr.auth.service.imp;

import fr.auth.dto.CategoryDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.CategoryMapper;
import fr.auth.model.Category;
import fr.auth.repository.CategoryRepository;
import fr.auth.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author hicham
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

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
