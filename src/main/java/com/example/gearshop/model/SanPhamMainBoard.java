package com.example.gearshop.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sanphammainboard")
public class SanPhamMainBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maMainBoard;

    @ManyToOne
    @JoinColumn(name = "sanPhamID", nullable = false)
    private SanPham sanPham;

    private String modelMain;
    private String chipset;
    private String socketMain;
    private String kichThuoc;
    private Integer soKheRAM;

    @Column(length = 500)
    private String mota;

}
