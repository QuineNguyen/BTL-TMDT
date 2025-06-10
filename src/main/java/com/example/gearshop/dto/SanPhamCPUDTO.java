package com.example.gearshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SanPhamCPUDTO extends SanPhamDTO {
    private String loaiCPU;
    private String soNhansoLuong;
    private String mota;
}
