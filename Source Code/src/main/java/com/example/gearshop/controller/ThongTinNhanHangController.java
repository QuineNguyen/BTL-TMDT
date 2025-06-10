package com.example.gearshop.controller;

import com.example.gearshop.model.KhachHang;
import com.example.gearshop.model.ThongTinNhanHang;
import com.example.gearshop.service.ThongTinNhanHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receivers")
public class ThongTinNhanHangController {

    @Autowired
    private ThongTinNhanHangService thongTinNhanHangService;

    @PostMapping
    public ThongTinNhanHang addReceiver(@RequestBody ThongTinNhanHang receiver,
                                        HttpSession session) {
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
        if (khachHang == null) {
            throw new IllegalStateException("Không tìm thấy thông tin khách hàng. Vui lòng đăng nhập.");
        }
        return thongTinNhanHangService.createThongTinNhanHang(
            khachHang.getId(),
            receiver.getTenNguoiNhan(),
            receiver.getEmail(),
            receiver.getSdt(),
            receiver.getDiachi()
        );
    }

    @GetMapping("/{id}")
    public ThongTinNhanHang getReceiverById(@PathVariable Integer id) {
        return thongTinNhanHangService.getThongTinNhanHangById(id);
    }
}
