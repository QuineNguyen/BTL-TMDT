package com.example.gearshop.dto;

import com.example.gearshop.model.HoaDonChiTiet;
import com.example.gearshop.model.SanPham;

import lombok.Getter;

@Getter
public class SanPhamTrongHoaDonDTO {
    private HoaDonChiTiet hoaDonChiTiet;
    private SanPham sanPham;

    public SanPhamTrongHoaDonDTO(HoaDonChiTiet hoaDonChiTiet, SanPham sanPham) {
        this.hoaDonChiTiet = hoaDonChiTiet;
        this.sanPham = sanPham;
    }
}
