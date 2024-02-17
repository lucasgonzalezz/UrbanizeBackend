package com.ecommerce.urbanize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.urbanize.entity.CaptchaEntity;
import com.ecommerce.urbanize.service.CaptchaService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/captcha")
public class CaptchaApi {

    @Autowired
    CaptchaService captchaService;

    // Endpoint for creating a new captcha
    @PostMapping("/create")
    public ResponseEntity<CaptchaEntity> create() {
        return ResponseEntity.ok(captchaService.createCaptcha());
    }
}