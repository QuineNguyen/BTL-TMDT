package com.example.gearshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SanPhamPSUDTO extends SanPhamDTO {
    private Integer congSuat;
    private Integer dienApVao;
    private String mota;

    // Getters and Setters

}
