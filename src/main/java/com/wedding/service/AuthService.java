package com.wedding.service;

import com.wedding.common.constant.AppConstant;
import com.wedding.dto.request.LoginRequest;
import com.wedding.dto.request.RegisterRequest;
import com.wedding.dto.response.AuthResponse;
import com.wedding.entity.User;
import com.wedding.exception.AppException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service xử lý logic đăng nhập, đăng ký.
 *
 * FAKE DATA: Đang dùng List trong bộ nhớ thay cho DB.
 * TODO: Khi có DB thì inject UserRepository vào đây và thay thế logic bên dưới.
 */
@Service
public class AuthService {

    // ======== FAKE DATABASE (Xóa khi có DB thật) ========
    private final List<User> fakeUsers = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public AuthService() {
        // Tạo sẵn 1 tài khoản admin để test
        User admin = new User(
                idCounter.getAndIncrement(),
                "Admin",
                "admin@gmail.com",
                "admin123",
                "0901234567",
                AppConstant.ROLE_ADMIN
        );
        fakeUsers.add(admin);

        // Tạo sẵn 1 tài khoản user thường
        User user = new User(
                idCounter.getAndIncrement(),
                "Nguyễn Thọ Hiếu",
                "hieulatoi1962@gmail.com",
                "hieu123",
                "0987654321",
                AppConstant.ROLE_USER
        );
        fakeUsers.add(user);
    }
    // ======== END FAKE DATABASE ========

    /**
     * Đăng nhập: Kiểm tra email + password, trả về thông tin user + fake token.
     */
    public AuthResponse login(LoginRequest request) {
        // Validate đầu vào
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new AppException(400, "Email không được để trống");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new AppException(400, "Mật khẩu không được để trống");
        }

        // Tìm user theo email
        User user = fakeUsers.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(request.getEmail()))
                .findFirst()
                .orElseThrow(() -> new AppException(401, AppConstant.MSG_INVALID_CREDENTIALS));

        // So sánh mật khẩu (TODO: Dùng BCrypt khi có DB thật)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new AppException(401, AppConstant.MSG_INVALID_CREDENTIALS);
        }

        // Tạo fake token (TODO: Thay bằng JWT khi implement security)
        String fakeToken = "fake-jwt-" + UUID.randomUUID();

        return new AuthResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                fakeToken
        );
    }

    /**
     * Đăng ký: Kiểm tra email trùng, tạo user mới, trả về thông tin + fake token.
     */
    public AuthResponse register(RegisterRequest request) {
        // Validate đầu vào
        if (request.getFullName() == null || request.getFullName().isBlank()) {
            throw new AppException(400, "Họ tên không được để trống");
        }
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new AppException(400, "Email không được để trống");
        }
        if (request.getPassword() == null || request.getPassword().length() < 6) {
            throw new AppException(400, "Mật khẩu phải có ít nhất 6 ký tự");
        }

        // Kiểm tra email đã tồn tại chưa
        boolean emailExists = fakeUsers.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(request.getEmail()));
        if (emailExists) {
            throw new AppException(409, AppConstant.MSG_EMAIL_EXISTED);
        }

        // Tạo user mới
        User newUser = new User(
                idCounter.getAndIncrement(),
                request.getFullName(),
                request.getEmail(),
                request.getPassword(), // TODO: Hash bằng BCrypt khi có DB thật
                request.getPhone(),
                AppConstant.ROLE_USER
        );
        fakeUsers.add(newUser);

        // Tạo fake token
        String fakeToken = "fake-jwt-" + UUID.randomUUID();

        return new AuthResponse(
                newUser.getId(),
                newUser.getFullName(),
                newUser.getEmail(),
                newUser.getRole(),
                fakeToken
        );
    }
}
