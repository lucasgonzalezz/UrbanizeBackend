package com.ecommerce.urbanize.service;

import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.urbanize.entity.CaptchaEntity;
import com.ecommerce.urbanize.repository.CaptchaRepository;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@Service
public class CaptchaService {

    @Autowired
    private CaptchaRepository captchaRepository;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    // Create a new captcha entity
    public CaptchaEntity createCaptcha() {
        CaptchaEntity captchaEntity = new CaptchaEntity();
        String text = defaultKaptcha.createText();
        byte[] image = generateCaptchaImage(text);
        captchaEntity.setId(null);
        captchaEntity.setText(text);
        captchaEntity.setImage(image);
        return captchaRepository.save(captchaEntity);
    }

    // Get a random captcha from the database
    public CaptchaEntity getRandomCaptcha() {
        List<CaptchaEntity> list = captchaRepository.findAll();
        if (!list.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(list.size());
            return list.get(index);
        } else {
            throw new RuntimeException("No captchas found in the database");
        }
    }

    // Generate a captcha image for the given text
    private byte[] generateCaptchaImage(String text) {
        BufferedImage bufferedImage = defaultKaptcha.createImage(text);
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception exception) {
            exception.printStackTrace();
            return new byte[0];
        }
    }
}