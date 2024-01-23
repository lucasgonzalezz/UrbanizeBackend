package com.ecommerce.urbanize.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.urbanize.entity.CartEntity;
import com.ecommerce.urbanize.entity.PurchaseEntity;
import com.ecommerce.urbanize.entity.UserEntity;
import com.ecommerce.urbanize.service.PurchaseService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class PurchaseApi {

    @Autowired
    PurchaseService oPurchaseService;

    // Get purchase by ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseService.get(id));
    }

    // Get page of purchases
    @GetMapping("")
    public ResponseEntity<Page<PurchaseEntity>> getPage(Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseService.getPage(oPageable));
    }

    // Get page of purchases by user ID
    @GetMapping("/byUser/{user_id}")
    public ResponseEntity<Page<PurchaseEntity>> getPageByIdUser(@PathVariable("user_id") Long user_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseService.findByIdUser(user_id, oPageable));
    }

    // Get a random purchase
    @GetMapping("/random")
    public ResponseEntity<PurchaseEntity> getRandomCompra() {
        PurchaseEntity purchase = oPurchaseService.getOneRandom();
        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }

    // Make a single cart purchase
    @PostMapping("/makeSingleCartPurchase")
    public ResponseEntity<PurchaseEntity> makeSingleCartPurchase(
            @RequestBody CartEntity oCartEntity,
            @RequestParam Long user_id) {
        UserEntity user = new UserEntity();
        user.setId(user_id);

        PurchaseEntity purchase = oPurchaseService.makeSingleCartPurchase(oCartEntity, user);
        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

    // Make purchase of all carts
    @PostMapping("/makeAllCartPurchase")
    public ResponseEntity<PurchaseEntity> makeAllCartPurchase(
            @RequestBody List<CartEntity> carts,
            @RequestParam Long user_id) {
        UserEntity user = new UserEntity();
        user.setId(user_id);

        PurchaseEntity purchase = oPurchaseService.makeAllCartPurchase(carts, user);
        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

    // Delete purchase by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> cancelPurchase(@PathVariable("purchase_id") Long purchase_id) {
        Long cancelledIdPurchase = oPurchaseService.cancelPurchase(purchase_id);
        return new ResponseEntity<>(cancelledIdPurchase, HttpStatus.OK);
    }

    // Generate purchase code
    @GetMapping("/generateOrderCode")
    public ResponseEntity<String> generateOrderCode() {
        return ResponseEntity.ok(oPurchaseService.generateOrderCode());
    }

    // Find purchases, ordered by the newest first
    @GetMapping("/findPurchasesByNewest")
    public ResponseEntity<Page<PurchaseEntity>> findByNewestPurchase(Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseService.findByNewestPurchase(oPageable));
    }

    // Find purchases, ordered by the oldest first
    @GetMapping("/findPurchasesByOldest")
    public ResponseEntity<Page<PurchaseEntity>> findByOldestPurchase(Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseService.findByOldestPurchase(oPageable));
    }

    // Find purchases by purchase code (using LIKE)
    @GetMapping("/findPurchasesByPurchaseCode")
    public ResponseEntity<Page<PurchaseEntity>> findByPurchaseCode(@RequestParam String purchaseCode,
            Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseService.findByPurchaseCode(purchaseCode, oPageable));
    }

    // Find total purchases by user ID
    @GetMapping("/findTotalPurchasesByIdUser")
    public ResponseEntity<Double> findTotalPurchasesByIdUser(@RequestParam Long user_id) {
        return ResponseEntity.ok(oPurchaseService.findTotalPurchasesByIdUser(user_id));
    }

    // Find total purchase by ID
    @GetMapping("/findTotalPurchaseById")
    public ResponseEntity<Double> findTotalPurchaseById(@RequestParam Long id) {
        return ResponseEntity.ok(oPurchaseService.findTotalPurchaseById(id));
    }

    // Find total purchase by user ID and purchase ID
    @GetMapping("/findTotalPurchaseByUserIdAndPurchaseId")
    public ResponseEntity<Double> findTotalPurchaseByUserIdAndPurchaseId(@RequestParam Long user_id,
            @RequestParam Long purchase_id) {
        return ResponseEntity.ok(oPurchaseService.findTotalPurchaseByUserIdAndPurchaseId(user_id, purchase_id));
    }

    // Find purchases by user ID, ordered by the most expensive first
    @GetMapping("/findPurchasesMostExpensiveByIdUser")
    public ResponseEntity<Page<PurchaseEntity>> findPurchasesMostExpensiveByIdUser(@RequestParam Long user_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseService.findPurchasesMostExpensiveByIdUser(user_id, oPageable));
    }

    // Find purchases by user ID, ordered by the cheapest first
    @GetMapping("/findPurchasesMostCheapestByIdUser")
    public ResponseEntity<Page<PurchaseEntity>> findPurchasesMostCheapestByIdUser(@RequestParam Long user_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseService.findPurchasesMostCheapestByIdUser(user_id, oPageable));
    }

    // Populate the purchase table
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oPurchaseService.populate(amount));
    }

    // Empty the purchase table
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        Long deletedCount = oPurchaseService.empty();
        return new ResponseEntity<>(deletedCount, HttpStatus.OK);
    }
}