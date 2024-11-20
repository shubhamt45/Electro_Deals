package com.Electronics.store.Electronics_goods.Store.services;

import com.Electronics.store.Electronics_goods.Store.dtos.CategoryDto;
import com.Electronics.store.Electronics_goods.Store.dtos.PageableResponse;

public interface CategoryService {

    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //delete

    void delete(String categoryId);

    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single category detail
    CategoryDto get(String categoryId);

    //search:
}
