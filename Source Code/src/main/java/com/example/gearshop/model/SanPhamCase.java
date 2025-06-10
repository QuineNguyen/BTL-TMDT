package com.example.gearshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sanphamcase")
@Getter
@Setter
@NoArgsConstructor
public class SanPhamCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maCase;

    @ManyToOne
    @JoinColumn(name = "sanPhamID", nullable = false)
    private SanPham sanPham;

    private String hoTroMain;
    private String mauCase;

    @Column(length = 500)
    private String mota;

}
