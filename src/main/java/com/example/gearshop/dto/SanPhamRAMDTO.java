package com.example.gearshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SanPhamRAMDTO extends SanPhamDTO {
    private String chuanRam;
    private String dungLuong;
    private String mota;
}
