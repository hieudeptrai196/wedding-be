package com.wedding.controller;

import com.wedding.common.constant.AppConstant;
import com.wedding.common.response.ApiResponse;
import com.wedding.dto.request.LoginRequest;
import com.wedding.dto.request.RegisterRequest;
import com.wedding.dto.response.AuthResponse;
import com.wedding.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstant.AUTH_PATH)
@Tag(name = "Authentication", description = "API đăng nhập & đăng ký")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Đăng nhập", description = "Đăng nhập bằng email & mật khẩu")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse data = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success(AppConstant.MSG_LOGIN_SUCCESS, data));
    }

    @PostMapping("/register")
    @Operation(summary = "Đăng ký", description = "Đăng ký tài khoản mới")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse data = authService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(AppConstant.MSG_REGISTER_SUCCESS, data));
    }
}
