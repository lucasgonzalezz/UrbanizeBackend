package com.ecommerce.urbanize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.urbanize.bean.CaptchaBean;
import com.ecommerce.urbanize.bean.CaptchaResponseBean;
import com.ecommerce.urbanize.bean.UserBean;
import com.ecommerce.urbanize.service.SessionService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    SessionService oSessionService;

    // Endpoint for pre-login operations, such as captcha generation
    @GetMapping("/prelogin")
    public ResponseEntity<CaptchaResponseBean> prelogin() {
        return ResponseEntity.ok(oSessionService.prelogin());
    }

    // Endpoint for handling login with captcha verification
    @PostMapping("/loginCaptcha")
    public ResponseEntity<String> loginCaptcha(@RequestBody CaptchaBean oCaptchaBean) {
        return ResponseEntity.ok(oSessionService.loginCaptcha(oCaptchaBean));
    }

    // Endpoint for handling login with captcha verification
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserBean userBean) {
        return ResponseEntity.ok(oSessionService.login(userBean));
    }
}