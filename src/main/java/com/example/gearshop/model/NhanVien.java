package com.example.gearshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nhanvien")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maNhanVien;

    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "nguoiDungID")
    private NguoiDung nguoiDung;
}
