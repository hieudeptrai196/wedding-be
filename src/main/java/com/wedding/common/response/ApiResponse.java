package com.wedding.common.response;

/**
 * Response wrapper chuẩn cho tất cả API trong hệ thống.
 * Mọi API đều trả về cùng 1 format để FE dễ xử lý.
 */
public class ApiResponse<T> {

    private int code;
    private String message;
    private T data;

    // --- Constructors ---
    public ApiResponse() {}

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // --- Static factory methods (dùng cho gọn, không cần new) ---
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Thành công", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(201, message, data);
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    // --- Getters & Setters ---
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
