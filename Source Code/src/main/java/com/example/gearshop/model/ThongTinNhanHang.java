package com.example.gearshop.model;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "thongTinNhanHang")
@Entity
public class ThongTinNhanHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int khachHangID; // Lưu ID của khách hàng thay vì đối tượng

    private String tenNguoiNhan;

    private String email;

    private String sdt;

    private String diachi;

    // Getters và Setters (nếu không dùng Lombok)
}