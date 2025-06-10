package com.example.gearshop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sanphammanhinh")
public class SanPhamManHinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String maMH;

    @ManyToOne
    @JoinColumn(name = "sanPhamID", nullable = false)
    private SanPham sanPham;

    private Integer kichThuoc;
    private String beMat;
    private Integer tanSoQuet;
    private String tamNen;
    private String doPhanGiai;

    @Column(length = 500)
    private String mota;

    // getters và setters
    public Integer getId() {
        return ID;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Integer getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(Integer kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getBeMat() {
        return beMat;
    }

    public void setBeMat(String beMat) {
        this.beMat = beMat;
    }

    public Integer getTanSoQuet() {
        return tanSoQuet;
    }

    public void setTanSoQuet(Integer tanSoQuet) {
        this.tanSoQuet = tanSoQuet;
    }

    public String getTamNen() {
        return tamNen;
    }

    public void setTamNen(String tamNen) {
        this.tamNen = tamNen;
    }

    public String getDoPhanGiai() {
        return doPhanGiai;
    }

    public void setDoPhanGiai(String doPhanGiai) {
        this.doPhanGiai = doPhanGiai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
