package com.example.gearshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NguoiDungDTO {
    private Integer id;
    private String tenNguoiDung;
    private String tenDangNhap;
    private String sdt;
    private String diaChi;
    private String email;
    private String vaiTro; // "Khách hàng" hoặc "Nhân viên"
    private String ghiChu;

    // Getters + Setters
}