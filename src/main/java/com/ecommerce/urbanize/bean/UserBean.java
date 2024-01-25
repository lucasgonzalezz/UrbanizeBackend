package com.ecommerce.urbanize.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean class representing user information.
 */
public class UserBean {

    private String username;

    // The @JsonProperty annotation is used to control the serialization of the
    // 'password' field.
    // In this case, it specifies that the 'password' field should only be written
    // during serialization.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * Get the username of the user.
     * 
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username for the user.
     * 
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password of the user.
     * 
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password for the user.
     * 
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}