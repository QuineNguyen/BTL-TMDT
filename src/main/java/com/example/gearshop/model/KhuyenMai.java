package com.example.gearshop.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maKM;
    private String tenKM;
    private int giamGiaPhanTram;
    private BigDecimal giamGiaCuThe;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String mota;
}
