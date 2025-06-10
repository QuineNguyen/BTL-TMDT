package com.example.gearshop.service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.repository.HoaDonChiTietRepository;
import com.example.gearshop.repository.HoaDonRepository;

@Service
public class ThongKeService {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public Map<String, BigDecimal> getDoanhThuTheoNgay() {
        List<Object[]> data = hoaDonRepository.doanhThuTheoNgay();
        Map<String, BigDecimal> result = new LinkedHashMap<>();
        for (Object[] row : data) {
            result.put(row[0].toString(), (BigDecimal) row[1]);
        }
        return result;
    }

    public Map<String, BigDecimal> getDoanhThuTheoThang() {
        List<Object[]> data = hoaDonRepository.doanhThuTheoThang();
        Map<String, BigDecimal> result = new LinkedHashMap<>();
        for (Object[] row : data) {
            result.put("Tháng " + row[0].toString(), (BigDecimal) row[1]);
        }
        return result;
    }

    public Map<String, BigDecimal> getDoanhThuTheoLoaiSanPham() {
        List<Object[]> data = hoaDonChiTietRepository.doanhThuTheoLoaiSanPham();
        Map<String, BigDecimal> result = new LinkedHashMap<>();
        for (Object[] row : data) {
            result.put((String) row[0], (BigDecimal) row[1]);
        }
        return result;
    }
}
