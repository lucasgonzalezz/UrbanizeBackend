package com.ecommerce.urbanize.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.urbanize.entity.CartEntity;
import com.ecommerce.urbanize.entity.ProductEntity;
import com.ecommerce.urbanize.entity.PurchaseDetailEntity;
import com.ecommerce.urbanize.entity.PurchaseEntity;
import com.ecommerce.urbanize.entity.UserEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.repository.PurchaseRepository;
import com.ecommerce.urbanize.repository.PurchaseDetailRepository;
import jakarta.transaction.Transactional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository oPurchaseRepository;

    @Autowired
    PurchaseDetailRepository oPurchaseDetailRepository;

    @Autowired
    CartService oCartService;

    @Autowired
    UserService oUserService;

    @Autowired
    ProductService oProductService;

    @Autowired
    PurchaseDetailService oPurchaseDetailService;

    // Get order by ID
    public PurchaseEntity get(Long id) {
        return oPurchaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public Page<PurchaseEntity> getPage(Pageable oPageable) {
        return oPurchaseRepository.findAll(oPageable);
    }

    public PurchaseEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oPurchaseRepository.count()), 1);
        return oPurchaseRepository.findAll(oPageable).getContent().get(0);
    }

    // Generate order code
    public String generateOrderCode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String currentDate = LocalDateTime.now().format(formatter);
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return currentDate + uuid;
    }

    // Make a single cart purchase
    @Transactional
    public PurchaseEntity makeSingleCartPurchase(CartEntity oCartEntity, UserEntity oUserEntity) {
        PurchaseEntity oPurchaseEntity = new PurchaseEntity();
        PurchaseDetailEntity oPurchaseDetailEntity = new PurchaseDetailEntity();
        oPurchaseDetailEntity.setId(null);
        oPurchaseDetailEntity.setProduct(oCartEntity.getProduct());
        oPurchaseDetailEntity.setPurchase(oPurchaseEntity);
        oPurchaseDetailEntity.setAmount(oCartEntity.getAmount());
        oPurchaseDetailEntity.setPrice(oCartEntity.getProduct().getPrice());
       
        oPurchaseDetailRepository.save(oPurchaseDetailEntity);

        ProductEntity Product = oCartEntity.getProduct();
        oProductService.updateStock(Product, oCartEntity.getAmount());

        oCartService.delete(oCartEntity.getId());

        oPurchaseEntity.setUser(oUserEntity);
        oPurchaseEntity.setPurchaseDate(LocalDate.now());
        oPurchaseEntity.setPurchaseCode(generateOrderCode());
        
        return oPurchaseRepository.save(oPurchaseEntity);
    }

    // Make purchase of all carts
    @Transactional
    public PurchaseEntity makeAllCartPurchase(List<CartEntity> carts, UserEntity oUserEntity) {
        PurchaseEntity oPurchaseEntity = new PurchaseEntity();
        
        carts = oCartService.getCartByUser(oUserEntity.getId());

        for (CartEntity cart : carts) {
            PurchaseDetailEntity oPurchaseDetailEntity = new PurchaseDetailEntity();
            oPurchaseDetailEntity.setId(null);
            oPurchaseDetailEntity.setProduct(cart.getProduct());
            oPurchaseDetailEntity.setPurchase(oPurchaseEntity);
            oPurchaseDetailEntity.setAmount(cart.getAmount());
            oPurchaseDetailEntity.setPrice(cart.getProduct().getPrice());
            
            oPurchaseDetailRepository.save(oPurchaseDetailEntity);
        }

        for (CartEntity cart : carts) {
            ProductEntity product = cart.getProduct();
            oProductService.actualizarStock(product, cart.getAmount());
        }

        oCartService.deleteByUser(oUserEntity.getId());

        oPurchaseEntity.setUser(oUserEntity);
        oPurchaseEntity.setPurchaseDate(LocalDate.now());
        oPurchaseEntity.setPurchaseCode(generateOrderCode());
        

        return oPurchaseRepository.save(oPurchaseEntity);

    }

    // Cancel order
     public Long cancelPurchase(Long id) {
        if (oPurchaseRepository.existsById(id)) {
            Page<PurchaseDetailEntity> purchaseDatils = oPurchaseDetailRepository.findByCompraId(id, PageRequest.of(0, 1000));
            for (PurchaseDetailEntity purchaseDetail : purchaseDatils) {
                ProductEntity product = purchaseDetail.getProduct();
                int amount = purchaseDetail.getAmount();
                oProductService.actualizarStock(product, -amount);
            }
            oPurchaseDetailRepository.deleteAll(purchaseDatils);
            oPurchaseRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("Error: La compra no existe.");
        }
    }

    // Empty the order table
    @Transactional
    public Long empty() {
        oPurchaseRepository.deleteAll();
        oPurchaseRepository.resetAutoIncrement();
        oPurchaseRepository.flush();
        return oPurchaseRepository.count();
    }

}