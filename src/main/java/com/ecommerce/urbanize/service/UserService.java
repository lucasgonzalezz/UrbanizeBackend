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
import com.ecommerce.urbanize.helper.DataGenerationHelper;
import com.ecommerce.urbanize.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

    private final String password = "82B9AA49C5C0CF486ABFAE9346457D4665275D3E0E3147D8E61F04FE126A7B67";

    @Autowired
    UserRepository oUserRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public UserEntity get(Long id) {
        return oUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserEntity getByUsername(String username) {
        return oUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by username"));
    }

    public Page<UserEntity> getPage(Pageable oPageable) {
        return oUserRepository.findAll(oPageable);
    }

    public Long create(UserEntity oUserEntity) {
        oUserEntity.setId(null);
        oUserEntity.setPassword(password);
        return oUserRepository.save(oUserEntity).getId();
    }

    public Long update(UserEntity oUserEntity) {
        UserEntity oUserEntityFromDatabase = this.get(oUserEntity.getId());
        oUserEntity.setPassword(oUserEntityFromDatabase.getPassword());
        oUserEntity.setRole(oUserEntityFromDatabase.getRole());
        return oUserRepository.save(oUserEntity).getId();
    }

    public Long delete(Long id) {
        oUserRepository.deleteById(id);
        return id;
    }

    public UserEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oUserRepository.count()), 1);
        return oUserRepository.findAll(oPageable).getContent().get(0);
    }

    public Long populate(Integer amount) {
        for (int i = 0; i < amount; i++) {
            String name = DataGenerationHelper.getRadomName();
            String lastName1 = DataGenerationHelper.getRadomLastName1();
            String lastName2 = DataGenerationHelper.getRadomLastName2();
            LocalDate birthDate = DataGenerationHelper.getRandomBirthDate();
            int phoneNumber = DataGenerationHelper.getRandomPhoneNumber();
            String dni = DataGenerationHelper.getRandomDNI();
            int postalCode = DataGenerationHelper.getRandomPostalCode();
            String city = DataGenerationHelper.getRandomCity();
            String address = DataGenerationHelper.getRandomAddress();
            String email = DataGenerationHelper.getRandomEmail();
            String username = DataGenerationHelper.getRandomUsername();
            oUserRepository.save(new UserEntity(name, lastName1, lastName2, birthDate, phoneNumber, dni, postalCode,
                    city, address, email, username, password, true));
        }
        return oUserRepository.count();
    }

    @Transactional
    public Long empty() {
        oUserRepository.deleteAll();
        oUserRepository.resetAutoIncrement();
        UserEntity userAdmin = new UserEntity(1L, "Lucas", "Gonzalez", "Rozalen", LocalDate.of(2004, 03, 21),
                640383838, "26882786H", 46022, "Valencia", "Calle Duqe de Gaeta", "lucgr04@gmail.com", "lucgr",
                password, true);
        oUserRepository.save(userAdmin);
        UserEntity userNormal = new UserEntity(2L, "Blanca", "Pérez", "García", LocalDate.of(1995, 6, 15),
                555555555, "12345678A", 28001, "Madrid", "Calle Gran Vía", "blanca@gmail.com", "blancaizm",
                password, false);
        oUserRepository.save(userNormal);
        return oUserRepository.count();
    }

    public Page<UserEntity> getUsersWithMostOrders(Pageable oPageable) {
        return oUserRepository.findUsersByOrderDesc(oPageable);
    }

    public Page<UserEntity> getUsersWithFewestOrders(Pageable oPageable) {
        return oUserRepository.findUsersByOrderAsc(oPageable);
    }

    public Page<UserEntity> getUsersWithMostRatings(Pageable oPageable) {
        return oUserRepository.findUsersByRatingDesc(oPageable);
    }

}