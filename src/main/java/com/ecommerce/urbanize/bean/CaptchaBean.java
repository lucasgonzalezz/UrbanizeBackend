package com.ecommerce.urbanize.bean;

/**
 * Bean class representing captcha-related information.
 */
public class CaptchaBean {

    private String username = "";
    private String password = "";
    private String token = "";
    private String answer = "";

    /**
     * Get the username associated with the captcha.
     * 
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username associated with the captcha.
     * 
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password associated with the captcha.
     * 
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password associated with the captcha.
     * 
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the token associated with the captcha.
     * 
     * @return The token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Set the token associated with the captcha.
     * 
     * @param token The token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Get the answer provided for the captcha.
     * 
     * @return The answer.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set the answer for the captcha.
     * 
     * @param answer The answer to set.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}