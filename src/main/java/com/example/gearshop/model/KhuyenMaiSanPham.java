package com.example.gearshop.model;

import jakarta.persistence.*;

@Entity
@IdClass(KhuyenMaiSanPhamId.class)
public class KhuyenMaiSanPham {
    @Id
    @ManyToOne
    @JoinColumn(name = "sanPhamID")
    private SanPham sanPham;

    @Id
    @ManyToOne
    @JoinColumn(name = "khuyenMaiID")
    private KhuyenMai khuyenMai;
}
