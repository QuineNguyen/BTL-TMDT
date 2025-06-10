package com.example.gearshop.model;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "giohang")
@Entity
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maGioHang;

    @OneToOne
    @JoinColumn(name = "thongTinNhanHangID")
    private ThongTinNhanHang thongTinNhanHang;

    // Getters và Setters
}
