package com.example.gearshop.service;

import com.example.gearshop.model.SanPhamMainBoard;

import java.util.List;

public interface MainboardService {
    List<SanPhamMainBoard> findFiltered(String[] thuongHieu, String[] modelMain,
                                        String[] chipset, String[] socketMain,
                                        String[] kichThuoc, String[] soKheRAM,
                                        Integer minPrice, Integer maxPrice, String sort);

    SanPhamMainBoard findById(Integer id); // Thêm phương thức này
}
