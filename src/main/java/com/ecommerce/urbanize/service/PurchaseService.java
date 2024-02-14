package com.ecommerce.urbanize.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Random;
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

import com.ecommerce.urbanize.helper.PurchaseDataGenerationHelper;

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

    @Autowired
    SessionService oSessionService;

    // Get order by ID
    public PurchaseEntity get(Long id) {
        return oPurchaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    // Get order by user ID
    public Page<PurchaseEntity> findByIdUser(Long user_id, Pageable oPageable) {
        oSessionService.onlyAdminsOrUsersWithTheirData(user_id);
        return oPurchaseRepository.findByUserId(user_id, oPageable);
    }

    public Page<PurchaseEntity> getPage(Pageable oPageable) {
        oSessionService.onlyAdminsOrUsers();
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

    @Transactional
    public PurchaseEntity makeProductPurhase(ProductEntity oProductEntity, UserEntity oUserEntity, int amount) {

        oSessionService.onlyAdminsOrUsersWithTheirData(oUserEntity.getId());

        PurchaseEntity oPurchaseEntity = new PurchaseEntity();

        oPurchaseEntity.setUser(oUserEntity);
        oPurchaseEntity.setPurchaseDate(LocalDate.now());
        oPurchaseEntity.setPurchaseCode(generateOrderCode());

        oPurchaseRepository.save(oPurchaseEntity);

        PurchaseDetailEntity oPurchaseDetailEntity = new PurchaseDetailEntity();
        oPurchaseDetailEntity.setId(null);
        oPurchaseDetailEntity.setProduct(oProductEntity);
        oPurchaseDetailEntity.setPurchase(oPurchaseEntity);
        oPurchaseDetailEntity.setAmount(amount);
        oPurchaseDetailEntity.setPrice(oProductEntity.getPrice());

        oPurchaseDetailRepository.save(oPurchaseDetailEntity);

        oProductService.updateStock(oProductEntity, amount);

        return oPurchaseEntity;
    }

    // Make a single cart purchase
    @Transactional
    public PurchaseEntity makeSingleCartPurchase(CartEntity oCartEntity, UserEntity oUserEntity) {

        oSessionService.onlyAdminsOrUsersWithTheirData(oUserEntity.getId());

        PurchaseEntity oPurchaseEntity = new PurchaseEntity();

        oPurchaseEntity.setStatus("Pendent");
        oPurchaseEntity.setDateBill(LocalDate.now());
        oPurchaseEntity.setDeliveryDate(LocalDate.now().plusDays(3));
        oPurchaseEntity.setNumBill(1);

        oPurchaseEntity.setPurchaseCode(generateOrderCode());
        oPurchaseEntity.setPurchaseDate(LocalDate.now());
        oPurchaseEntity.setUser(oUserEntity);

        oPurchaseRepository.save(oPurchaseEntity);

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

        return oPurchaseEntity;
    }

    // Make purchase of all carts
    @Transactional
    public PurchaseEntity makeAllCartPurchase(Page<CartEntity> carts, UserEntity oUserEntity) {

        oSessionService.onlyAdminsOrUsersWithTheirData(oUserEntity.getId());

        PurchaseEntity oPurchaseEntity = new PurchaseEntity();

        oPurchaseEntity.setStatus("Pendent");
        oPurchaseEntity.setDateBill(LocalDate.now());
        oPurchaseEntity.setDeliveryDate(LocalDate.now().plusDays(3));
        oPurchaseEntity.setNumBill(1);
    
        oPurchaseEntity.setPurchaseCode(generateOrderCode());
        oPurchaseEntity.setPurchaseDate(LocalDate.now());
        oPurchaseEntity.setUser(oUserEntity);

        oPurchaseRepository.save(oPurchaseEntity);

        carts = oCartService.getCartByUser(oUserEntity.getId(), PageRequest.of(0, 1000));

        for (CartEntity cart : carts) {
            PurchaseDetailEntity oPurchaseDetailEntity = new PurchaseDetailEntity();
            oPurchaseDetailEntity.setId(null);
            oPurchaseDetailEntity.setProduct(cart.getProduct());
            oPurchaseDetailEntity.setPurchase(oPurchaseEntity);
            oPurchaseDetailEntity.setAmount(cart.getAmount());
            oPurchaseDetailEntity.setPrice(cart.getProduct().getPrice());

            oPurchaseDetailRepository.save(oPurchaseDetailEntity);
            ProductEntity product = cart.getProduct();
            oProductService.updateStock(product, cart.getAmount());
        }

        oCartService.deleteByUserId(oUserEntity.getId());

        return oPurchaseEntity;
    }

    // Cancel order
    public Long cancelPurchase(Long id) {
        PurchaseEntity purchase = oPurchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Purchase not found."));
        oSessionService.onlyAdminsOrUsersWithTheirData(purchase.getUser().getId());
        if (oPurchaseRepository.existsById(id)) {
            Page<PurchaseDetailEntity> purchaseDatils = oPurchaseDetailRepository.findByPurchaseId(id,
                    PageRequest.of(0, 1000));
            for (PurchaseDetailEntity purchaseDetail : purchaseDatils) {
                ProductEntity product = purchaseDetail.getProduct();
                int amount = purchaseDetail.getAmount();
                oProductService.updateStock(product, -amount);
            }
            oPurchaseDetailRepository.deleteAll(purchaseDatils);
            oPurchaseRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("Error: Purchase not found.");
        }
    }

    // Find purchases, ordered by the newest first
    public Page<PurchaseEntity> findByNewestPurchase(Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oPurchaseRepository.findByNewestPurchase(oPageable);
    }

    // Find purchases, ordered by the oldest first
    public Page<PurchaseEntity> findByOldestPurchase(Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oPurchaseRepository.findByOldestPurchase(oPageable);
    }

    // Find purchases by purchase code (using LIKE)
    public Page<PurchaseEntity> findByPurchaseCode(String purchaseCode, Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oPurchaseRepository.findByPurchaseCode(purchaseCode, oPageable);
    }

    // Find total purchases by user ID
    public Double findTotalPurchasesByIdUser(Long user_id) {
        return oPurchaseRepository.findTotalPurchasesByUserId(user_id);
    }

    // Find total purchase by ID
    public Double findTotalPurchaseById(Long id) {
        oSessionService.onlyAdmins();
        return oPurchaseRepository.findTotalPurchaseById(id);
    }

    // Find total purchase by user ID and purchase ID
    public Double findTotalPurchaseByUserIdAndPurchaseId(Long user_id, Long purchase_id) {
        oSessionService.onlyAdminsOrUsersWithTheirData(user_id);
        return oPurchaseRepository.findTotalPurchaseByUserIdAndPurchaseId(user_id, purchase_id);
    }

    // Find purchases by user ID, ordered by the most expensive first
    public Page<PurchaseEntity> findPurchasesMostExpensiveByIdUser(Long user_id, Pageable oPageable) {
        oSessionService.onlyAdminsOrUsersWithTheirData(user_id);
        return oPurchaseRepository.findPurchasesMostExpensiveByUserId(user_id, oPageable);
    }

    // Find purchases by user ID, ordered by the cheapest first
    public Page<PurchaseEntity> findPurchasesMostCheapestByIdUser(Long user_id, Pageable oPageable) {
        oSessionService.onlyAdminsOrUsersWithTheirData(user_id);
        return oPurchaseRepository.findPurchasesMostCheapestByUserId(user_id, oPageable);
    }

    // Populate the database with random purchases
    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            // Generate random purchase data
            LocalDate purchaseDate = PurchaseDataGenerationHelper.getRandomDate();
            LocalDate deliveryDate = PurchaseDataGenerationHelper.getRandomDate();
            String status = PurchaseDataGenerationHelper.getRandomStatus();
            String purchaseCode = PurchaseDataGenerationHelper.getRandomPurchaseCode();
            // For simplicity, assuming you have a method to get a random UserEntity
            UserEntity user = oUserService.getOneRandom();
            int numBill = new Random().nextInt(1000) + 1; // Assuming the bill number is between 1 and 1000
            LocalDate dateBill = PurchaseDataGenerationHelper.getRandomDate();

            // Save the purchase to the repository
            oPurchaseRepository.save(
                    new PurchaseEntity(purchaseDate, deliveryDate, status, purchaseCode, user, numBill, dateBill));
        }
        return oPurchaseRepository.count();
    }

    // Empty the order table
    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oPurchaseRepository.deleteAll();
        oPurchaseRepository.resetAutoIncrement();
        oPurchaseRepository.flush();
        return oPurchaseRepository.count();
    }

}