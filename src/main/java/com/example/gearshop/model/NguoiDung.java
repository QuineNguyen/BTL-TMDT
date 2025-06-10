package com.example.gearshop.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "nguoidung")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maNguoiDung;
    private String tenNguoiDung;
    private String tenDangNhap;
    private String matKhau;
    private String email;
    private String sdt;
    private String diaChi;
}
