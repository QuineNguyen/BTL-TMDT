package com.example.gearshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sanphamcpu")
public class SanPhamCPU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maCPU;

    @ManyToOne
    @JoinColumn(name = "sanPhamID", nullable = false)
    private SanPham sanPham;

    private String loaiCPU;
    private String soNhansoLuong;

    @Column(length = 500)
    private String mota;

}
