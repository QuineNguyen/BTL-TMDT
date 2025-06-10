package com.example.gearshop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sanphamvga")
public class SanPhamVGA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String maVGA;

    @ManyToOne
    @JoinColumn(name = "sanPhamID", nullable = false)
    private SanPham sanPham;

    private String kieuBoNho;
    private String dungLuongBoNho;

    @Column(length = 50)
    private String chipGPU;

    @Column(length = 500)
    private String mota;

    // getters và setters
    public Integer getId() {
        return ID;
    }

    public String getMaVGA() {
        return maVGA;
    }

    public void setMaVGA(String maVGA) {
        this.maVGA = maVGA;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public String getKieuBoNho() {
        return kieuBoNho;
    }

    public void setKieuBoNho(String kieuBoNho) {
        this.kieuBoNho = kieuBoNho;
    }

    public String getDungLuongBoNho() {
        return dungLuongBoNho;
    }

    public void setDungLuongBoNho(String dungLuongBoNho) {
        this.dungLuongBoNho = dungLuongBoNho;
    }

    public String getChipGPU() {
        return chipGPU;
    }

    public void setChipGPU(String chipGPU) {
        this.chipGPU = chipGPU;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
