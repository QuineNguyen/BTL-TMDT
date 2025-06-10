package com.example.gearshop.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class SanPhamDTO {
    private String tenSanPham;
    private MultipartFile hinhAnh;
    private Long thuongHieuID;
    private Long loaiSPID;
    private int tonKho;
}
