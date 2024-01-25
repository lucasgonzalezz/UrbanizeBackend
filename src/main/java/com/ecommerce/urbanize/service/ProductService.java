package com.ecommerce.urbanize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.urbanize.entity.CategoryEntity;
import com.ecommerce.urbanize.entity.ProductEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.helper.ProductDataGenerationHelper;
import com.ecommerce.urbanize.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository oProductRepository;

    @Autowired
    CategoryService oCategoryService;

    @Autowired
    SessionService oSessionService;

    // Get product by ID
    public ProductEntity get(Long id) {
        return oProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    // Get a page of products
    public Page<ProductEntity> getPage(Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oProductRepository.findAll(oPageable);
    }

    // Create a new product
    public Long create(ProductEntity oProductEntity) {
        oSessionService.onlyAdmins();
        oProductEntity.setId(null);
        return oProductRepository.save(oProductEntity).getId();
    }

    // Update an existing product
    public ProductEntity update(ProductEntity oProductEntity) {
        oSessionService.onlyAdmins();
        return oProductRepository.save(oProductEntity);
    }

    // Delete an existing product
    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        oProductRepository.deleteById(id);
        return id;
    }

    // Update product stock and check if depleted
    @Transactional
    public void updateStock(ProductEntity oProductEntity, int amount) {
        ProductEntity productFound = oProductRepository.findById(oProductEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Error: Product not found."));

        if (productFound != null) {
            int currentStock = productFound.getStock();
            int newStock = currentStock - amount;

            if (newStock < 0) {
                // Si no hay suficiente stock, lanzar una excepción o mostrar un mensaje
                throw new IllegalStateException("There is not enough stock to buy this shirt.");
            }

            productFound.setStock(newStock);
            oProductRepository.save(productFound);
        }
    }

    // Get a random product
    public ProductEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oProductRepository.count()), 1);
        return oProductRepository.findAll(oPageable).getContent().get(0);
    }

    // Get products by category ID
    public Page<ProductEntity> getByCategory(Long category_id, Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oProductRepository.findByCategoryId(category_id, oPageable);
    }

    // Get products by size
    public Page<ProductEntity> getBySize(String size, Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oProductRepository.findBySize(size, oPageable);
    }

    // Get products by stock descending
    public Page<ProductEntity> getByStockDesc(Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oProductRepository.findByStockDesc(oPageable);
    }

    // Get products by price descending
    public Page<ProductEntity> getByPriceDesc(Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oProductRepository.findByPriceDesc(oPageable);
    }

    // Get products by price and category descending
    public Page<ProductEntity> getByPriceDescAndIdCategory(Long category_id, Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oProductRepository.findByPriceDescAndIdCategory(category_id, oPageable);
    }

    // Populate the product table
    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            // Generate random product data
            String productName = ProductDataGenerationHelper.getRandomProductName();
            int stock = ProductDataGenerationHelper.getRandomStock();
            String size = ProductDataGenerationHelper.getRandomSize();
            int price = ProductDataGenerationHelper.getRandomPrice();
            // For simplicity, assuming you have a method to get a random CategoryEntity
            CategoryEntity category = oCategoryService.getOneRandom();

            // Save the product to the repository
            oProductRepository.save(new ProductEntity(productName, stock, size, price, category));
        }
        return oProductRepository.count();
    }

    // Empty the product table
    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oProductRepository.deleteAll();
        oProductRepository.resetAutoIncrement();
        oProductRepository.flush();
        return oProductRepository.count();
    }

}