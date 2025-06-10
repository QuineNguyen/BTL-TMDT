package com.example.gearshop.dto;

import com.example.gearshop.model.YeuCauHoanTien;

import lombok.Getter;

@Getter
public class YeuCauHoanTienDTO {
    private YeuCauHoanTien yeuCau;
    private String tenNguoiDung;
    private String emailNguoiDung;
    private String tenSanPham;
    private String maHoaDon;
    private String loiNhan;

    // constructor, getters
    public YeuCauHoanTienDTO(YeuCauHoanTien yeuCau, String tenNguoiDung, String emailNguoiDung,
            String tenSanPham, String maHoaDon, String loiNhan) {
        this.yeuCau = yeuCau;
        this.tenNguoiDung = tenNguoiDung;
        this.emailNguoiDung = emailNguoiDung;
        this.tenSanPham = tenSanPham;
        this.maHoaDon = maHoaDon;
        this.loiNhan = loiNhan;
    }
    // getters
}