package com.ra.ss8.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {
    @NotBlank(message = "Tài khoản không được để trống!")
    private String username;
    @NotBlank(message = "Mật khẩu không được để trống!")
    @Length(min = 6, message = "Mật khẩu phải từ 6 ký tự!")
    private String password;
    @NotBlank(message = "Tên đầy đủ không được để trống!")
    private String fullname;
    @NotNull(message = "Giới tính không được để trống!")
    private Boolean gender;
    private String address;
    @NotBlank(message = "Email không được để trống!")
    @Email(regexp ="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Email không hợp lệ!")
    private String email;
    @NotBlank(message = "Số điện thoại không được để trống!")
    private String phone;
}
