package com.example.gearshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sanphamcooler")
public class SanPhamCooler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maCooler;

    @ManyToOne
    @JoinColumn(name = "sanPhamID", nullable = false)
    private SanPham sanPham;

    private String loaiTan;
    private Boolean coLED;

    @Column(length = 500)
    private String mota;

}
