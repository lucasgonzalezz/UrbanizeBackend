package com.ecommerce.urbanize.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.urbanize.bean.CaptchaBean;
import com.ecommerce.urbanize.bean.CaptchaResponseBean;
import com.ecommerce.urbanize.entity.CaptchaEntity;
import com.ecommerce.urbanize.entity.PendentEntity;
import com.ecommerce.urbanize.entity.UserEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.exception.UnauthorizedException;
import com.ecommerce.urbanize.helper.DataGenerationHelper;
import com.ecommerce.urbanize.helper.JWTHelper;
import com.ecommerce.urbanize.repository.CaptchaRepository;
import com.ecommerce.urbanize.repository.PendentRepository;
import com.ecommerce.urbanize.repository.UserRepository;
import com.ecommerce.urbanize.bean.UserBean;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.transaction.Transactional;

@Service
public class SessionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    CaptchaService captchaService;

    @Autowired
    PendentRepository pendentRepository;

    @Autowired
    CaptchaRepository captchaRepository;

    @Transactional
    public CaptchaResponseBean prelogin() {
        // Create a captcha for pre-login
        CaptchaEntity captchaEntity = captchaService.createCaptcha();

        // Create a new pendent entity associated with the captcha
        PendentEntity pendentEntity = new PendentEntity();
        pendentEntity.setCaptcha(captchaEntity);
        pendentEntity.setTimecode(LocalDateTime.now());
        PendentEntity newPendentEntity = pendentRepository.save(pendentEntity);

        // Generate a token for the pendent entity
        newPendentEntity.setToken(DataGenerationHelper.getSHA256(
                String.valueOf(newPendentEntity.getId())
                        + String.valueOf(captchaEntity.getId())
                        + String.valueOf(DataGenerationHelper.getRandomInt(0, 9999))));
        pendentRepository.save(newPendentEntity);

        // Prepare response with token and captcha image
        CaptchaResponseBean captchaResponseBean = new CaptchaResponseBean();
        captchaResponseBean.setToken(newPendentEntity.getToken());
        captchaResponseBean.setCaptchaImage(captchaEntity.getImage());

        return captchaResponseBean;
    }

    public String loginCaptcha(@RequestBody CaptchaBean captchaBean) {
        if (captchaBean.getUsername() != null && captchaBean.getPassword() != null) {
            // Validate user credentials
            UserEntity userEntity = userRepository
                    .findByUsernameAndPassword(captchaBean.getUsername(), captchaBean.getPassword())
                    .orElseThrow(() -> new ResourceNotFoundException("Incorrect username or password"));

            // Check if user entity is present
            if (userEntity != null) {
                // Retrieve pendent entity using the provided token
                PendentEntity pendentEntity = pendentRepository.findByToken(captchaBean.getToken())
                        .orElseThrow(() -> new ResourceNotFoundException("Incorrect token"));

                LocalDateTime timecode = pendentEntity.getTimecode();

                // Check if the captcha is still valid (within 180 seconds)
                if (LocalDateTime.now().isAfter(timecode.plusSeconds(180))) {
                    throw new UnauthorizedException("Captcha has expired");
                }

                // Validate the captcha answer
                if (pendentEntity.getCaptcha().getText().equals(captchaBean.getAnswer())) {
                    // Generate and return a JWT token upon successful validation
                    return JWTHelper.generateJWT(captchaBean.getUsername());
                } else {
                    throw new UnauthorizedException("Incorrect captcha answer");
                }
            } else {
                throw new UnauthorizedException("Incorrect username or password");
            }
        } else {
            throw new UnauthorizedException("User not found");
        }
    }

    // Login without captcha verification
    public String login(UserBean oUsuarioBean) {
        userRepository.findByUsernameAndPassword(oUsuarioBean.getUsername(), oUsuarioBean.getPassword()).orElseThrow(() -> new ResourceNotFoundException("Usuario o contraseña incorrectos"));
        return JWTHelper.generateJWT(oUsuarioBean.getUsername());
    }

    // Get the username from the current session
    public String getSessionUsername() {
        if (httpServletRequest.getAttribute("username") instanceof String) {
            return httpServletRequest.getAttribute("username").toString();
        } else {
            return null;
        }
    }

    // Get the user entity from the current session
    public UserEntity getSessionUser() {
        if (this.getSessionUsername() != null) {
            return userRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } else {
            return null;
        }
    }

    // Check if a session is active
    public boolean isSessionActive() {
        return this.getSessionUsername() != null
                && userRepository.findByUsername(this.getSessionUsername()).isPresent();
    }

    // Check if the current user is an admin
    public Boolean isAdmin() {
        if (this.getSessionUsername() != null) {
            UserEntity userEntitySession = userRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return Boolean.TRUE.equals(userEntitySession.getRole());
        } else {
            return false;
        }
    }

    // Check if the current user is a regular user
    public Boolean isUser() {
        if (this.getSessionUsername() != null) {
            UserEntity userEntitySession = userRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return Boolean.FALSE.equals(userEntitySession.getRole());
        } else {
            return false;
        }
    }

    // Throw unauthorized exception if the current user is not an admin
    public void onlyAdmins() {
        if (!this.isAdmin()) {
            throw new UnauthorizedException("Only administrators can perform this action");
        }
    }

    // Throw unauthorized exception if the current user is not a regular user
    public void onlyUsers() {
        if (!this.isUser()) {
            throw new UnauthorizedException("Only users can perform this action");
        }
    }

    // Throw unauthorized exception if no session is active
    public void onlyAdminsOrUsers() {
        if (!this.isSessionActive()) {
            throw new UnauthorizedException("You must log in to perform this action");
        }
    }

    // Throw unauthorized exception if the current user is not the target user
    public void onlyUsersWithTheirData(Long userId) {
        if (!this.isUser()) {
            throw new UnauthorizedException("Only users can perform this action");
        }
        if (!this.getSessionUser().getId().equals(userId)) {
            throw new UnauthorizedException("You can only access your own data");
        }
    }

    // Throw unauthorized exception if the current user is neither an admin nor the
    // target user
    public void onlyAdminsOrUsersWithTheirData(Long userId) {
        if (this.isSessionActive()) {
            if (!this.isAdmin()) {
                if (!this.isUser()) {
                    throw new UnauthorizedException("Only users can perform this action");
                } else {
                    if (!this.getSessionUser().getId().equals(userId)) {
                        throw new UnauthorizedException("You can only access your own data");
                    }
                }
            }
        } else {
            throw new UnauthorizedException("You must log in to perform this action");
        }
    }
}