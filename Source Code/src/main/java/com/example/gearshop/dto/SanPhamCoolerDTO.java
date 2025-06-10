package com.example.gearshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SanPhamCoolerDTO extends SanPhamDTO {
    private String loaiTan;
    private Boolean coLED;
    private String mota;
}
