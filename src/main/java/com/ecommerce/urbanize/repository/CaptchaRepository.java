package com.ecommerce.urbanize.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.urbanize.entity.CaptchaEntity;

public interface CaptchaRepository extends JpaRepository<CaptchaEntity, Long> {

}