// This is a repository interface for managing database operations related to purchases in an e-commerce application.
package com.ecommerce.urbanize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ecommerce.urbanize.entity.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    // Find purchases by user ID
    Page<PurchaseEntity> findByIdUser(Long idUser, Pageable pageable);

    // Find purchases, ordered by the newest first
    @Query(value = "SELECT * FROM purchase ORDER BY purchaseDate DESC", nativeQuery = true)
    Page<PurchaseEntity> findByNewestPurchase(Pageable pageable);

    // Find purchases, ordered by the oldest first
    @Query(value = "SELECT * FROM purchase ORDER BY purchaseDate ASC", nativeQuery = true)
    Page<PurchaseEntity> findByOldestPurchase(Pageable pageable);

    // Find purchases by purchase code (using LIKE)
    @Query(value = "SELECT * FROM purchase WHERE purchaseCode LIKE %?1%", nativeQuery = true)
    Page<PurchaseEntity> findByPurchaseCode(String purchaseCode, Pageable pageable);

    @Query(value = "SELECT SUM(pd.price * pd.amount) FROM purchase p, purchaseDetail pd WHERE p.id = pd.idPurchase AND p.idUser = ?1", nativeQuery = true)
    Double findTotalPurchasesByIdUser(Long idUser);

    @Query(value = "SELECT SUM(pd.price * pd.amount) FROM purchaseDetail pd WHERE pd.idPurchase = ?1", nativeQuery = true)
    Double findTotalPurchaseById(Long id);

    @Query(value = "SELECT SUM(pd.precio * pd.cantidad) FROM purchaseDetail pd, purchase p WHERE pd.idPurchase = p.id AND p.idUser = ?1 AND p.id = ?2", nativeQuery = true)
    Double findTotalPurchaseByUserIdAndPurchaseId(Long idUser, Long idPurchase);

    @Query(value = "SELECT * FROM purchase WHERE idUser = ?1 ORDER BY (SELECT SUM(pd.precio * pd.cantidad) FROM purchaseDetail pd WHERE pd.idPurchase = purchase.id) DESC", nativeQuery = true)
    Page<PurchaseEntity> findPurchasesMostExpensiveByIdUser(Long idUser, Pageable pageable);

    @Query(value = "SELECT * FROM purchase WHERE idUser = ?1 ORDER BY (SELECT SUM(pd.precio * pd.cantidad) FROM purchaseDetail pd WHERE pd.idPurchase = purchase.id) ASC", nativeQuery = true)
    Page<PurchaseEntity> findPurchasesMostCheapestByIdUser(Long idUser, Pageable pageable);

    // Reset the auto-increment value of the purchase table
    @Modifying
    @Query(value = "ALTER TABLE purchase AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}