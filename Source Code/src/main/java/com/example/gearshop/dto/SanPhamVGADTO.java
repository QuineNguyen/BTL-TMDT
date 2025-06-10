package com.example.gearshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SanPhamVGADTO extends SanPhamDTO {
    private String kieuBoNho;
    private String dungLuongBoNho;
    private String chipGPU;
    private String mota;
}
