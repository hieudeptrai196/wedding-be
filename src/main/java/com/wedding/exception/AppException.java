package com.wedding.exception;

/**
 * Custom exception cho toàn bộ ứng dụng.
 * Dùng để ném lỗi nghiệp vụ (business logic error) với HTTP status code tương ứng.
 */
public class AppException extends RuntimeException {

    private final int code;

    public AppException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
