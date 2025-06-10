package com.example.gearshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SanPhamCaseDTO extends SanPhamDTO {
    private String hoTroMain;
    private String mauCase;
    private String mota;
}
