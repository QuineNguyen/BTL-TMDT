package com.example.gearshop.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PhieuBaoHanh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maBH;

    private int thoiHanBaoHanh; // số tháng

    private LocalDate ngayHetHan;

    @ManyToOne
    @JoinColumn(name = "khachHangID")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "sanPhamID")
    private SanPham sanPham;

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaBH() {
        return maBH;
    }

    public void setMaBH(String maBH) {
        this.maBH = maBH;
    }

    public int getThoiHanBaoHanh() {
        return thoiHanBaoHanh;
    }

    public void setThoiHanBaoHanh(int thoiHanBaoHanh) {
        this.thoiHanBaoHanh = thoiHanBaoHanh;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
}
