package com.wedding.common.constant;

/**
 * Chứa các hằng số dùng chung trong toàn bộ hệ thống.
 * Tập trung ở 1 chỗ để dễ quản lý, tránh hard-code rải rác.
 */
public final class AppConstant {

    private AppConstant() {
        // Không cho phép tạo instance
    }

    // --- Roles ---
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    // --- API Paths ---
    public static final String API_PREFIX = "/api";
    public static final String AUTH_PATH = API_PREFIX + "/auth";
    public static final String MEDIA_PATH = API_PREFIX + "/media";
    public static final String TEMPLATE_PATH = API_PREFIX + "/templates";
    public static final String TEST_PATH = API_PREFIX + "/test";

    // --- Messages ---
    public static final String MSG_LOGIN_SUCCESS = "Đăng nhập thành công";
    public static final String MSG_REGISTER_SUCCESS = "Đăng ký thành công";
    public static final String MSG_INVALID_CREDENTIALS = "Email hoặc mật khẩu không đúng";
    public static final String MSG_EMAIL_EXISTED = "Email đã tồn tại trong hệ thống";
    public static final String MSG_USER_NOT_FOUND = "Không tìm thấy người dùng";
}
