package com.wedding.exception;

import com.wedding.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Bắt tất cả exception trong hệ thống và trả về format chuẩn ApiResponse.
 * FE sẽ luôn nhận được JSON thống nhất, không bao giờ nhận HTML error page.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Xử lý lỗi validation từ @Valid (DTO annotation).
     * Trả về danh sách các field bị lỗi kèm message cụ thể.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                400, "Dữ liệu không hợp lệ", errors
        );
        return ResponseEntity.badRequest().body(response);
    }

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
