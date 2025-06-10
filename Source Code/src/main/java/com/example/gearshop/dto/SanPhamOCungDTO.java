package com.example.gearshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SanPhamOCungDTO extends SanPhamDTO {
    private String loaiOCung;
    private String dungLuong;
    private String mota;
}
