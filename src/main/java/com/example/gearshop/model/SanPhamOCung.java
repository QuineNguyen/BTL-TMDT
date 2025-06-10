package com.example.gearshop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sanphamocung")
public class SanPhamOCung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String maOCung;

    @ManyToOne
    @JoinColumn(name = "sanPhamID", nullable = false)
    private SanPham sanPham;

    @Column(length = 6)
    private String loaiOCung;

    private String dungLuong;

    @Column(length = 500)
    private String mota;

    // getters và setters
    public Integer getId() {
        return ID;
    }

    public String getMaOCung() {
        return maOCung;
    }

    public void setMaOCung(String maOCung) {
        this.maOCung = maOCung;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public String getLoaiOCung() {
        return loaiOCung;
    }

    public void setLoaiOCung(String loaiOCung) {
        this.loaiOCung = loaiOCung;
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
