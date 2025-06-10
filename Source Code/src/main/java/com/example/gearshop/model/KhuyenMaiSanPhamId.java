package com.example.gearshop.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class KhuyenMaiSanPhamId implements Serializable {
    private Integer sanPham;
    private Integer khuyenMai;
}
