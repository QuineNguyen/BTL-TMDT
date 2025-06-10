package com.example.gearshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "khachhang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maKhachHang;
    private String ghiChu;
    private Long doanhThu;

    @ManyToOne
    @JoinColumn(name = "nguoiDungID")
    private NguoiDung nguoiDung;
}
