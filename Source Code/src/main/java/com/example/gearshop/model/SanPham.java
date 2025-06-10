package com.example.gearshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sanpham")
@Entity
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maSanPham;
    private String tenSanPham;
    private String hinhAnh;
    private LocalDateTime ngayThem;
    private BigDecimal gia;
    private Integer tonKho;
    private Integer daBan;

    @ManyToOne
    @JoinColumn(name = "nguoiThemID")
    private NhanVien nguoiThem;

    @ManyToOne
    @JoinColumn(name = "loaiSPID")
    private LoaiSanPham loaiSanPham;

    @ManyToOne
    @JoinColumn(name = "thuongHieuID")
    private ThuongHieu thuongHieu;

}
