package com.ecommerce.urbanize.service;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerce.urbanize.entity.RatingEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.repository.RatingRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class RatingService {

    @Autowired
    RatingRepository oRatingRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    // Get rating by ID
    public RatingEntity get(Long id) {
        return oRatingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
    }

    // Get a page of ratings
    public Page<RatingEntity> getPage(Pageable oPageable) {
        return oRatingRepository.findAll(oPageable);
    }

    // Create a new rating or update an existing one for a product and user
    // combination
    public Long create(RatingEntity oRatingEntity) {
        Optional<RatingEntity> ratingFromDatabase = oRatingRepository
                .findByIdProductAndIdUser(oRatingEntity.getProduct().getId(), oRatingEntity.getUser().getId());
        if (ratingFromDatabase.isPresent()) {
            RatingEntity rating = ratingFromDatabase.get();
            rating.setDate(LocalDate.now());
            return oRatingRepository.save(rating).getId();
        } else {
            oRatingEntity.setId(null);
            oRatingEntity.setDate(LocalDate.now());
            return oRatingRepository.save(oRatingEntity).getId();
        }
    }

    // Update an existing rating
    public RatingEntity update(RatingEntity oRatingEntity) {
        RatingEntity ratingEntityFromDatabase = this.get(oRatingEntity.getId());
        oRatingEntity.setDate(ratingEntityFromDatabase.getDate());
        return oRatingRepository.save(oRatingEntity);
    }

    // Delete a rating by ID
    public Long delete(Long id) {
        oRatingRepository.deleteById(id);
        return id;
    }

    // Get a random rating
    public RatingEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oRatingRepository.count()), 1);
        return oRatingRepository.findAll(oPageable).getContent().get(0);
    }

    // Get ratings by product ID
    public Page<RatingEntity> findByIdProduct(Long idProduct, Pageable pageable) {
        return oRatingRepository.findByIdProduct(idProduct, pageable);
    }

    // Get ratings by user ID
    public Page<RatingEntity> findByIdUser(Long idUser, Pageable pageable) {
        return oRatingRepository.findByIdUser(idUser, pageable);
    }

    // Get a rating for a specific product and user
    public Optional<RatingEntity> findByIdProductAndIdUser(Long idProduct, Long idUser) {
        return oRatingRepository.findByIdProductAndIdUser(idProduct, idUser);
    }

    // Get average rating for a product
    public Double getAverageRating(Long idProduct) {
        return oRatingRepository.getAverageRating(idProduct);
    }

    // Get ratings sorted by lowest punctuation
    public Page<RatingEntity> getRatingByLowestPunctuation(Long idProduct, Pageable pageable) {
        return oRatingRepository.getRatingByLowestPunctuation(idProduct, pageable);
    }

    // Get ratings sorted by highest punctuation
    public Page<RatingEntity> getRatingByHighestPunctuation(Long idProduct, Pageable pageable) {
        return oRatingRepository.getRatingByHighestPunctuation(idProduct, pageable);
    }

    // Get ratings sorted by newest
    public Page<RatingEntity> getRatingByNewest(Long idProduct, Pageable pageable) {
        return oRatingRepository.getRatingByNewest(idProduct, pageable);
    }

    // Get ratings sorted by oldest
    public Page<RatingEntity> getRatingByOldest(Long idProduct, Pageable pageable) {
        return oRatingRepository.getRatingByOldest(idProduct, pageable);
    }

    // Get ratings by user with lowest punctuation
    public Page<RatingEntity> getRatingByLowestPunctuationOfUsers(Long idUser, Pageable pageable) {
        return oRatingRepository.getRatingByLowestPunctuationOfUsers(idUser, pageable);
    }

    // Get ratings by user with highest punctuation
    public Page<RatingEntity> getRatingByHighestPunctuationOfUsers(Long idUser, Pageable pageable) {
        return oRatingRepository.getRatingByHighestPunctuationOfUsers(idUser, pageable);
    }

    // Get ratings by user sorted by newest
    public Page<RatingEntity> getRatingByNewestOfUsers(Long idUser, Pageable pageable) {
        return oRatingRepository.getRatingByNewestOfUsers(idUser, pageable);
    }

    // Get ratings by user sorted by oldest
    public Page<RatingEntity> getRatingByOldestOfUsers(Long idUser, Pageable pageable) {
        return oRatingRepository.getRatingByOldestOfUsers(idUser, pageable);
    }

    // Empty the rating table
    @Transactional
    public Long empty() {
        oRatingRepository.deleteAll();
        oRatingRepository.resetAutoIncrement();
        oRatingRepository.flush();
        return oRatingRepository.count();
    }

}