package com.example.gearshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "yeucauhoantien")
@Entity
public class YeuCauHoanTien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maYeuCauHoanTien;
    private LocalDateTime ngayYeuCau;
    private String trangThai;
    private String loiNhan;

    @ManyToOne
    @JoinColumn(name = "hoaDonChiTietID")
    private HoaDonChiTiet hoaDonChiTiet;
}
