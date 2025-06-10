package com.example.gearshop.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maHoaDonChiTiet;
    private int soLuongSP;
    private BigDecimal thanhTien;

    private int hoaDonID; // Lưu ID của hóa đơn thay vì đối tượng

    private int sanPhamID; // Lưu ID của sản phẩm thay vì đối tượng

    @ManyToOne
    @JoinColumn(name = "hoaDonID", insertable = false, updatable = false)
    private HoaDon hoaDon;
}
