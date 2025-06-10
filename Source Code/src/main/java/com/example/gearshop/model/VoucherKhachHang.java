package com.example.gearshop.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voucherkhachhang")
@Entity
public class VoucherKhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maVoucherKhachHang;

    @ManyToOne
    @JoinColumn(name = "khachHangID")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "voucherID")
    private Voucher voucher;

    private Boolean daDung;

    // Getters và Setters
}
