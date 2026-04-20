package com.wedding.dto.response;

/**
 * DTO trả về sau khi đăng nhập/đăng ký thành công.
 * Chứa thông tin user + token (tạm thời fake token).
 */
public class AuthResponse {

    private Long userId;
    private String fullName;
    private String email;
    private String role;
    private String accessToken;

    // --- Constructors ---
    public AuthResponse() {}

    public AuthResponse(Long userId, String fullName, String email, String role, String accessToken) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.accessToken = accessToken;
    }

    // --- Getters & Setters ---
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
}
