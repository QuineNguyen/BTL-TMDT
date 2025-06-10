package com.example.gearshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SanPhamManHinhDTO extends SanPhamDTO {
    private String beMat;
    private int kichThuoc;
    private int tanSoQuet;
    private String tamNen;
    private String doPhanGiai;
    private String mota;
}
