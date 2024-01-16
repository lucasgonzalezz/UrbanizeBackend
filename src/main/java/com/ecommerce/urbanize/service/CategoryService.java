package com.ecommerce.urbanize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ecommerce.urbanize.entity.CategoryEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.repository.CategoryRepository;
import jakarta.servlet.http.HttpServletRequest;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository oCategoryRepository;

    @Autowired
    private HttpServletRequest oHttpServletRequest;

    // Get category by ID
    public CategoryEntity get(Long id) {
        return oCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    // Get page of categories
    public Page<CategoryEntity> getPage(Pageable oPageable) {
        return oCategoryRepository.findAll(oPageable);
    }

    // Create a new category
    public Long create(CategoryEntity oCategoryEntity) {
        oCategoryEntity.setId(null);
        return oCategoryRepository.save(oCategoryEntity).getId();
    }

    // Update an existing category
    public CategoryEntity update(CategoryEntity oCategoryEntity) {
        return oCategoryRepository.save(oCategoryEntity);
    }

    // Delete an existing category
    public Long delete(Long id) {
        oCategoryRepository.deleteById(id);
        return id;
    }

    // Get a random category
    public CategoryEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oCategoryRepository.count()), 1);
        return oCategoryRepository.findAll(oPageable).getContent().get(0);
    }

    // Get category ordered by the quantity of associated products in descending order
    public Page<CategoryEntity> getPageByQuantityProduct(Pageable oPageable) {
        return oCategoryRepository.findByQuantityProduct(oPageable);
    }

    // Empty the category table
    public Long emptyTable() {
        oCategoryRepository.deleteAll();
        oCategoryRepository.resetAutoIncrement();
        oCategoryRepository.flush();
        return oCategoryRepository.count();
    }

}