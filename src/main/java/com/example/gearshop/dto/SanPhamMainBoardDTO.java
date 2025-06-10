package com.example.gearshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SanPhamMainBoardDTO extends SanPhamDTO {
    private String modelMain;
    private String chipSet;
    private String socketMain;
    private String soKheRam;
    private String mota;
}
