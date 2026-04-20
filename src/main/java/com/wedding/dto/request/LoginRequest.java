package com.wedding.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO cho request đăng nhập.
 */
public class LoginRequest {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    // --- Constructors ---
    public LoginRequest() {}

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // --- Getters & Setters ---
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
