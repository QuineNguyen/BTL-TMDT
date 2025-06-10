package com.example.gearshop.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoadon")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maHoaDon;

    @ManyToOne
    @JoinColumn(name = "thongTinNhanHangID", nullable = false)
    private ThongTinNhanHang thongTinNhanHang;

    private LocalDateTime ngayTao;

    private BigDecimal tongGia;

    private String trangThaiDonHang;

    // Getters và Setters
}
