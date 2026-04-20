package com.wedding.exception;

import com.wedding.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Bắt tất cả exception trong hệ thống và trả về format chuẩn ApiResponse.
 * FE sẽ luôn nhận được JSON thống nhất, không bao giờ nhận HTML error page.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Xử lý các lỗi nghiệp vụ do mình chủ động ném (AppException).
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(AppException ex) {
        ApiResponse<Void> response = ApiResponse.error(ex.getCode(), ex.getMessage());
        return ResponseEntity.status(ex.getCode()).body(response);
    }

    /**
     * Xử lý tất cả các lỗi không lường trước được (NullPointer, DB error,...).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        ApiResponse<Void> response = ApiResponse.error(500, "Lỗi hệ thống: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
