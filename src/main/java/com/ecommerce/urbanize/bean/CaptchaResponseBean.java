package com.ecommerce.urbanize.bean;

/**
 * Bean class representing a captcha response.
 */
public class CaptchaResponseBean {

    private String token;
    private byte[] captchaImage;

    /**
     * Get the token associated with the captcha.
     * 
     * @return The captcha token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Set the token associated with the captcha.
     * 
     * @param token The captcha token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Get the byte array representing the captcha image.
     * 
     * @return The captcha image byte array.
     */
    public byte[] getCaptchaImage() {
        return captchaImage;
    }

    /**
     * Set the byte array representing the captcha image.
     * 
     * @param captchaImage The captcha image byte array to set.
     */
    public void setCaptchaImage(byte[] captchaImage) {
        this.captchaImage = captchaImage;
    }
}