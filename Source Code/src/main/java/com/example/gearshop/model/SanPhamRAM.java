package com.example.gearshop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sanphamram")
public class SanPhamRAM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String maRAM;

    @ManyToOne
    @JoinColumn(name = "sanPhamID", nullable = false)
    private SanPham sanPham;

    private String chuanRAM;
    private String dungLuong;

    @Column(length = 500)
    private String mota;

    // getters và setters
    public Integer getId() {
        return ID;
    }

    public String getMaRAM() {
        return maRAM;
    }

    public void setMaRAM(String maRAM) {
        this.maRAM = maRAM;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public String getChuanRAM() {
        return chuanRAM;
    }

    public void setChuanRAM(String chuanRAM) {
        this.chuanRAM = chuanRAM;
    }

    public String getDungLuong() {
        return dungLuong;
    }

    public void setDungLuong(String dungLuong) {
        this.dungLuong = dungLuong;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
