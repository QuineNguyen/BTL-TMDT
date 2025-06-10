package com.example.gearshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gearshop.model.NguoiDung;
import com.example.gearshop.repository.KhachHangRepository;
import com.example.gearshop.service.ThongBaoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/thongbao")
public class ThongBaoController {

    @Autowired
    private ThongBaoService thongBaoService;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private HttpSession session;

    @ResponseBody
    @PostMapping("/markAsRead")
    public ResponseEntity<String> markAsRead() {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung != null) {
            return khachHangRepository.findByNguoiDung_Id(nguoiDung.getId())
                    .map(khachHang -> {
                        thongBaoService.danhDauTatCaDaDoc(khachHang.getId());
                        return ResponseEntity.ok("Đã đánh dấu đã đọc");
                    })
                    .orElseGet(() -> ResponseEntity.badRequest().body("Khách hàng không tồn tại"));
        }
        return ResponseEntity.badRequest().body("Người dùng chưa đăng nhập");
    }
}