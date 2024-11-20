package com.Electronics.store.Electronics_goods.Store.services.impl;

import com.Electronics.store.Electronics_goods.Store.dtos.CategoryDto;
import com.Electronics.store.Electronics_goods.Store.dtos.PageableResponse;
import com.Electronics.store.Electronics_goods.Store.entities_models.Category;
import com.Electronics.store.Electronics_goods.Store.exceptions.ResourceNotFoundException;
import com.Electronics.store.Electronics_goods.Store.helper.Helper;
import com.Electronics.store.Electronics_goods.Store.repositories.CategoryRepository;
import com.Electronics.store.Electronics_goods.Store.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        //creating categoryId:randomly

        String categoryId = UUID.randomUUID().toString();//Universally unique Identifier
        categoryDto.setCategoryId(categoryId);

        Category category = mapper.map(categoryDto, Category.class);
        //In the above line of code we are converting dto to Entity by using the modal mapper concept

        Category savedCategory = categoryRepository.save(category);
        //In the above line of code we are converting  Entity to dto by using the modal mapper concept

        return mapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {

        //get category of given id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id !!"));
        //update category details
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());

        //saving the data in the database
        Category updatedCategory = categoryRepository.save(category);
        return mapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        //get category of given id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id !!"));
        categoryRepository.delete(category);
    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);
        return pageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id !!"));
        return mapper.map(category, CategoryDto.class);
    }
}
