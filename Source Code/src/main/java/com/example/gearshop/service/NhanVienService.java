package com.example.gearshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.NguoiDung;
import com.example.gearshop.model.NhanVien;
import com.example.gearshop.repository.NhanVienRepository;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public NhanVien getNhanVienByNguoiDung(NguoiDung nguoiDung) {
        // Giả sử bạn có một phương thức để lấy NhanVien từ NguoiDung
        // Đây chỉ là một ví dụ, bạn cần thay thế bằng logic thực tế của bạn
        return nhanVienRepository.findByNguoiDung(nguoiDung)
                .orElse(null);
    }
}
