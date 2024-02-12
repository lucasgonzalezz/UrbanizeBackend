package com.ecommerce.urbanize.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.urbanize.entity.ProductEntity;
import com.ecommerce.urbanize.entity.RatingEntity;
import com.ecommerce.urbanize.entity.UserEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.helper.RatingGenerationHelper;
import com.ecommerce.urbanize.repository.RatingRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RatingService {

    @Autowired
    RatingRepository oRatingRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    UserService oUserService;

    @Autowired
    ProductService oProductService;

    @Autowired
    SessionService oSessionService;

    // Get rating by ID
    public RatingEntity get(Long id) {
        return oRatingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
    }

    // Get a page of ratings
    public Page<RatingEntity> getPage(Pageable oPageable, Long user_id, Long product_id, String filter) {
        Page<RatingEntity> page;

        if (user_id != 0) {
            return oRatingRepository.findByUserId(user_id, oPageable);
        } else if (product_id != 0) {
            return oRatingRepository.findByProductId(product_id, oPageable);

        } else {
            if (filter == null || filter.isEmpty() || filter.trim().isEmpty()) {
                page = oRatingRepository.findAll(oPageable);
            } else {
                page = oRatingRepository.findByMensajeOrTitulo(
                        filter, filter, oPageable);
            }
        }
        return page;

    }

    public Long create(RatingEntity oRatingEntity) {
        oSessionService.onlyAdminsOrUsersWithTheirData(oSessionService.getSessionUser().getId());
        oRatingEntity.setId(null);
        oRatingEntity.setDate(LocalDate.now());
        return oRatingRepository.save(oRatingEntity).getId();
    }

    // Update an existing rating
    public RatingEntity update(RatingEntity oRatingEntity) {
        oSessionService.onlyAdminsOrUsersWithTheirData(oRatingEntity.getUser().getId());
        RatingEntity ratingEntityFromDatabase = this.get(oRatingEntity.getId());
        oRatingEntity.setDate(ratingEntityFromDatabase.getDate());
        return oRatingRepository.save(oRatingEntity);
    }

    // Delete a rating by ID
    public Long delete(Long id) {
        RatingEntity oRatingEntity = this.get(id);
        oSessionService.onlyAdminsOrUsersWithTheirData(oRatingEntity.getUser().getId());
        oRatingRepository.deleteById(id);
        return id;
    }

    // Get a random rating
    public RatingEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oRatingRepository.count()), 1);
        return oRatingRepository.findAll(oPageable).getContent().get(0);
    }

    // Get ratings by product ID
    public Page<RatingEntity> findByIdProduct(Long product_id, Pageable pageable) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.findByProductId(product_id, pageable);
    }

    // Get ratings by user ID
    public Page<RatingEntity> findByIdUser(Long user_id, Pageable pageable) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.findByUserId(user_id, pageable);
    }

    // Get a rating for a specific product and user
    public Optional<RatingEntity> findByIdProductAndIdUser(Long product_id, Long user_id) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.findByProductIdAndUserId(product_id, user_id);
    }

    // Get average rating for a product
    public Double getAverageRating(Long product_id) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.getAverageRating(product_id);
    }

    // Get ratings sorted by lowest punctuation
    public Page<RatingEntity> getRatingByLowestPunctuation(Long product_id, Pageable pageable) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.getRatingByLowestPunctuation(product_id, pageable);
    }

    // Get ratings sorted by highest punctuation
    public Page<RatingEntity> getRatingByHighestPunctuation(Long product_id, Pageable pageable) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.getRatingByHighestPunctuation(product_id, pageable);
    }

    // Get ratings sorted by newest
    public Page<RatingEntity> getRatingByNewest(Long product_id, Pageable pageable) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.getRatingByNewest(product_id, pageable);
    }

    // Get ratings sorted by oldest
    public Page<RatingEntity> getRatingByOldest(Long product_id, Pageable pageable) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.getRatingByOldest(product_id, pageable);
    }

    // Get ratings by user with lowest punctuation
    public Page<RatingEntity> getRatingByLowestPunctuationOfUsers(Long user_id, Pageable pageable) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.getRatingByLowestPunctuationOfUsers(user_id, pageable);
    }

    // Get ratings by user with highest punctuation
    public Page<RatingEntity> getRatingByHighestPunctuationOfUsers(Long user_id, Pageable pageable) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.getRatingByHighestPunctuationOfUsers(user_id, pageable);
    }

    // Get ratings by user sorted by newest
    public Page<RatingEntity> getRatingByNewestOfUsers(Long user_id, Pageable pageable) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.getRatingByNewestOfUsers(user_id, pageable);
    }

    // Get ratings by user sorted by oldest
    public Page<RatingEntity> getRatingByOldestOfUsers(Long user_id, Pageable pageable) {
        oSessionService.onlyAdminsOrUsers();
        return oRatingRepository.getRatingByOldestOfUsers(user_id, pageable);
    }

    // Populate the database with random ratings
    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            // Generate random rating data
            String title = RatingGenerationHelper.getRandomTitle();
            String description = RatingGenerationHelper.getRandomDescription();
            int punctuation = RatingGenerationHelper.getRandomPunctuation();
            LocalDate date = RatingGenerationHelper.getRandomDate();
            // For simplicity, assuming you have methods to get random UserEntity and
            // ProductEntity
            UserEntity user = oUserService.getOneRandom();
            ProductEntity product = oProductService.getOneRandom();

            // Save the rating to the repository
            oRatingRepository.save(new RatingEntity(title, description, punctuation, date, user, product));
        }
        return oRatingRepository.count();
    }

    // Empty the rating table
    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oRatingRepository.deleteAll();
        oRatingRepository.resetAutoIncrement();
        oRatingRepository.flush();
        return oRatingRepository.count();
    }
}