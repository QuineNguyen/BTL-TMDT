package com.example.gearshop.service;

import com.example.gearshop.model.ThongTinNhanHang;
import com.example.gearshop.repository.ThongTinNhanHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThongTinNhanHangService {

    @Autowired
    private ThongTinNhanHangRepository thongTinNhanHangRepository;

    public ThongTinNhanHang createThongTinNhanHang(int khachHangID, String tenNguoiNhan, String email, String sdt, String diachi) {
        ThongTinNhanHang thongTinNhanHang = new ThongTinNhanHang();
        thongTinNhanHang.setKhachHangID(khachHangID);
        thongTinNhanHang.setTenNguoiNhan(tenNguoiNhan);
        thongTinNhanHang.setEmail(email);
        thongTinNhanHang.setSdt(sdt);
        thongTinNhanHang.setDiachi(diachi);
        return thongTinNhanHangRepository.save(thongTinNhanHang);
    }

    public List<ThongTinNhanHang> getThongTinNhanHangByKhachHangID(int khachHangID) {
        return thongTinNhanHangRepository.findByKhachHangID(khachHangID);
    }

    public ThongTinNhanHang getThongTinNhanHangById(Integer id) {
        return thongTinNhanHangRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thông tin nhận hàng với ID: " + id));
    }
}
