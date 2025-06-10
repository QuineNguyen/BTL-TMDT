package com.example.gearshop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.HoaDonChiTiet;
import com.example.gearshop.model.YeuCauHoanTien;
import com.example.gearshop.repository.YeuCauHoanTienRepository;

@Service
public class YeuCauHoanTienService {
    @Autowired
    private YeuCauHoanTienRepository yeuCauHoanTienRepository;

    /**
     * Tạo mới yêu cầu hoàn tiền
     */
    public YeuCauHoanTien createYeuCauHoanTien(HoaDonChiTiet hoaDonChiTiet, String lyDo) {
        YeuCauHoanTien yeuCau = new YeuCauHoanTien();
        yeuCau.setMaYeuCauHoanTien(generateMaYeuCauHoanTien());
        yeuCau.setNgayYeuCau(LocalDateTime.now());
        yeuCau.setTrangThai("Chua duyet");
        yeuCau.setHoaDonChiTiet(hoaDonChiTiet);
        yeuCau.setLoiNhan(lyDo);
        return yeuCauHoanTienRepository.save(yeuCau);
    }

    /**
     * Lấy tất cả yêu cầu hoàn tiền (cho admin)
     */
    public List<YeuCauHoanTien> getAllYeuCauHoanTien() {
        return yeuCauHoanTienRepository.findAll();
    }

    /**
     * Cập nhật trạng thái duyệt
     */
    public void updateTrangThai(Integer id, String trangThai) {
        YeuCauHoanTien yeuCau = (YeuCauHoanTien) yeuCauHoanTienRepository.findById(id).get();
        yeuCau.setTrangThai(trangThai);
        yeuCauHoanTienRepository.save(yeuCau);
    }

    /**
     * Tạo mã yêu cầu hoàn tiền mới
     */
    private String generateMaYeuCauHoanTien() {
        // Ví dụ: YCHT + 5 chữ số ngẫu nhiên
        return "YCHT" + String.format("%05d", new Random().nextInt(100000));
    }
}
