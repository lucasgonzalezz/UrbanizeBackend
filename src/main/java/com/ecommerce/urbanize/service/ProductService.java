package com.ecommerce.urbanize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerce.urbanize.entity.ProductEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProductService {

    @Autowired
    private ProductRepository oProductRepository;

    @Autowired
    private HttpServletRequest oHttpServletRequest;

    // Get product by ID
    public ProductEntity get(Long id) {
        return oProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    // Get a page of products
    public Page<ProductEntity> getPage(Pageable oPageable) {
        return oProductRepository.findAll(oPageable);
    }

    // Create a new product
    public Long create(ProductEntity oProductEntity) {
        oProductEntity.setId(null);
        return oProductRepository.save(oProductEntity).getId();
    }

    // Update an existing product
    public ProductEntity update(ProductEntity oProductEntity) {
        return oProductRepository.save(oProductEntity);
    }

    // Delete an existing product
    public Long delete(Long id) {
        oProductRepository.deleteById(id);
        return id;
    }

    // Update product stock and check if depleted
    @Transactional
    public ProductEntity updateStock(Long id, int quantity) {
        ProductEntity product = oProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        int updatedStock = product.getStock() - quantity;
        if (updatedStock < 0) {
            // Stock is depleted
            throw new RuntimeException("Insufficient stock for product with ID: " + id);
        }

        product.setStock(updatedStock);
        oProductRepository.save(product);

        return product;
    }

    // Get a random product
    public ProductEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oProductRepository.count()), 1);
        return oProductRepository.findAll(oPageable).getContent().get(0);
    }

    // Get products by category ID
    public Page<ProductEntity> getByCategory(Long idCategory, Pageable oPageable) {
        return oProductRepository.findByIdCategory(idCategory, oPageable);
    }

    // Get products by size
    public Page<ProductEntity> getBySize(String size, Pageable oPageable) {
        return oProductRepository.findBySize(size, oPageable);
    }

    // Get products by stock descending
    public Page<ProductEntity> getByStockDesc(Pageable oPageable) {
        return oProductRepository.findByStockDesc(oPageable);
    }

    // Get products by price descending
    public Page<ProductEntity> getByPriceDesc(Pageable oPageable) {
        return oProductRepository.findByPriceDesc(oPageable);
    }

    // Get products by price and category descending
    public Page<ProductEntity> getByPriceDescAndIdCategory(Long idCategory, Pageable oPageable) {
        return oProductRepository.findByPriceDescAndIdCategory(idCategory, oPageable);
    }

    // Empty the product table
    @Transactional
    public Long empty() {
        oProductRepository.deleteAll();
        oProductRepository.resetAutoIncrement();
        oProductRepository.flush();
        return oProductRepository.count();
    }

}