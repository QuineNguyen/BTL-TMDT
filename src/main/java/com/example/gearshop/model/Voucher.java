package com.example.gearshop.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voucher")
@Entity
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maVoucher;

    private String tenVoucher;

    private Integer giamGiaTheoPhanTram;

    private BigDecimal giamGiaCuThe;

    private LocalDateTime thoiHan;

    private BigDecimal donToiThieu;

    // Getters và Setters
}
