package com.example.gearshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "danhgia")
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int soSao;
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "khachHangID")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "sanPhamID")
    private SanPham sanPham;

}
