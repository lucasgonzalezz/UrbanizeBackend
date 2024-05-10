package com.ecommerce.urbanize.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.urbanize.entity.UserEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.helper.UserGenerationHelper;
import com.ecommerce.urbanize.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

    // Default password for user accounts
    private final String password = "c2dbd3ccae9ed8f37e3c31641825e6ecb4edee3bb01708a5fb1b074cc574317b";

    // Autowired repository and HTTPServletRequest
    @Autowired
    UserRepository oUserRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    // Get user by ID
    public UserEntity get(Long id) {
        return oUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // Get user by username
    public UserEntity getByUsername(String username) {
        return oUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by username"));
    }

    // Get a random user
    public UserEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oUserRepository.count()), 1);
        return oUserRepository.findAll(oPageable).getContent().get(0);
    }

    // Get a page of users
    public Page<UserEntity> getPage(Pageable oPageable, String filter) {
        oSessionService.onlyAdmins();
        Page<UserEntity> page;

        if (filter == null || filter.isEmpty() || filter.trim().isEmpty()) {
            page = oUserRepository.findAll(oPageable);
        } else {
            page = oUserRepository.findByUserByNameOrSurnameOrLastnameContainingIgnoreCase(
                    filter, filter, filter, filter, oPageable);
        }
        return page;
    }

    // Create a new user
    public Long create(UserEntity oUserEntity) {
        
        oUserEntity.setId(null);
        oUserEntity.setPassword(password);
        return oUserRepository.save(oUserEntity).getId();
    }

    // Update an existing user
    public UserEntity update(UserEntity oUserEntity) {
        oSessionService.onlyAdminsOrUsersWithTheirData(oUserEntity.getId());
        UserEntity oUserEntityFromDatabase = this.get(oUserEntity.getId());
        oUserEntity.setPassword(oUserEntityFromDatabase.getPassword());
        oUserEntity.setRole(oUserEntityFromDatabase.getRole());
        return oUserRepository.save(oUserEntity);
    }

    // Delete a user by ID
    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        oUserRepository.deleteById(id);
        return id;
    }

    // Populate the database with random users
    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            // Generate random user data
            String name = UserGenerationHelper.getRadomName();
            String lastName1 = UserGenerationHelper.getRadomLastName1();
            String lastName2 = UserGenerationHelper.getRadomLastName2();
            LocalDate birthDate = UserGenerationHelper.getRandomBirthDate();
            int phoneNumber = UserGenerationHelper.getRandomPhoneNumber();
            String dni = UserGenerationHelper.getRandomDNI();
            int postalCode = UserGenerationHelper.getRandomPostalCode();
            String city = UserGenerationHelper.getRandomCity();
            String address = UserGenerationHelper.getRandomAddress();
            String email = UserGenerationHelper.getRandomEmail();
            String username = UserGenerationHelper.getRandomUsername();
            // Save the user to the repository
            oUserRepository.save(new UserEntity(name, lastName1, lastName2, birthDate, phoneNumber, dni, postalCode,
                    city, address, email, username, password, true));
        }
        return oUserRepository.count();
    }

    // Get users with the most orders
    public Page<UserEntity> getUsersWithMostPurchases(Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oUserRepository.findUsersByPurchaseDesc(oPageable);
    }

    // Get users with the fewest orders
    public Page<UserEntity> getUsersWithFewestPurchases(Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oUserRepository.findUsersByPurchaseAsc(oPageable);
    }

    // Get users with the most ratings
    public Page<UserEntity> getUsersWithMostRatings(Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oUserRepository.findUsersByRatingDesc(oPageable);
    }

    // Empty the user table and add two sample users
    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oUserRepository.deleteAll();
        oUserRepository.resetAutoIncrement();
        // Add an admin user
        UserEntity userAdmin = new UserEntity(1L, "Pepe", "Pérez", "Fernández", LocalDate.of(1950, 03, 21),
                640259857, "24518752L", 46033, "Valencia", "Calle Perico los Palotes", "pepe4@gmail.com", "pepe33",
                password, true);
        oUserRepository.save(userAdmin);
        // Add a normal user
        UserEntity userNormal = new UserEntity(2L, "Pepa", "Sánchez", "García", LocalDate.of(1995, 6, 15),
                555555555, "12345678A", 28001, "Madrid", "Calle Gran Vía", "pepa@gmail.com", "pepita",
                password, false);
        oUserRepository.save(userNormal);
        return oUserRepository.count();
    }
}