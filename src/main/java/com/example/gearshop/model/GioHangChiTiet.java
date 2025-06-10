package com.example.gearshop.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GioHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maGioHangChiTiet;
    private int soLuong;
    private BigDecimal donGia;

    @ManyToOne
    @JoinColumn(name = "gioHangID")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "sanPhamID")
    private SanPham sanPham;
}
