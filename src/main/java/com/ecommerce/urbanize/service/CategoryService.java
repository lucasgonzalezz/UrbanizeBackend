package com.ecommerce.urbanize.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.ecommerce.urbanize.entity.CategoryEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.helper.CategoryDataGenerationHelper;
import com.ecommerce.urbanize.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository oCategoryRepository;

    @Autowired
    SessionService oSessionService;

    // Get category by ID
    public CategoryEntity get(Long id) {
        return oCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    // Get page of categories
    public Page<CategoryEntity> getPage(Pageable oPageable) {
        oSessionService.onlyAdminsOrUsers();
        return oCategoryRepository.findAll(oPageable);
    }

    // Create a new category
    public Long create(CategoryEntity oCategoryEntity) {
        oSessionService.onlyAdmins();
        oCategoryEntity.setId(null);
        return oCategoryRepository.save(oCategoryEntity).getId();
    }

    // Update an existing category
    public CategoryEntity update(CategoryEntity oCategoryEntity) {
        oSessionService.onlyAdmins();
        return oCategoryRepository.save(oCategoryEntity);
    }

    // Delete an existing category
    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        oCategoryRepository.deleteById(id);
        return id;
    }

    // Get a random category
    public CategoryEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oCategoryRepository.count()), 1);
        return oCategoryRepository.findAll(oPageable).getContent().get(0);
    }

    // Get category ordered by the quantity of associated products in descending
    // order
    public Page<CategoryEntity> getPageByQuantityProduct(Pageable oPageable) {
        oSessionService.onlyAdminsOrUsers();
        return oCategoryRepository.findByQuantityProduct(oPageable);
    }

    // Populate database with random categories
    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            // Generate random category data
            String categoryName = CategoryDataGenerationHelper.getRandomCategoryName();

            // Save the category to the repository
            oCategoryRepository.save(new CategoryEntity(categoryName));
        }
        return oCategoryRepository.count();
    }

    @Transactional
    // Empty the category table
    public Long empty() {
        oSessionService.onlyAdmins();
        oCategoryRepository.deleteAll();
        oCategoryRepository.resetAutoIncrement();
        oCategoryRepository.flush();
        return oCategoryRepository.count();
    }

}