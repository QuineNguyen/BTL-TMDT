package com.example.gearshop.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "thongbao")
@Getter
@Setter
@NoArgsConstructor
public class ThongBao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String maThongBao;
    private String noiDung;

    private LocalDateTime ngayThongBao;

    @Enumerated(EnumType.STRING)
    private TrangThaiThongBao trangThaiThongBao;

    @ManyToOne
    @JoinColumn(name = "khachHangID", nullable = false)
    private KhachHang khachHang;
}
