package com.example.gearshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.gearshop.model.NguoiDung;
import com.example.gearshop.repository.KhachHangRepository;
import com.example.gearshop.repository.NguoiDungRepository;
import com.example.gearshop.service.ThongBaoService;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private ThongBaoService thongBaoService;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @ModelAttribute("nguoiDung")
    public NguoiDung thongTinNguoiDungDangNhap(HttpSession session) {
        return (NguoiDung) session.getAttribute("nguoiDung");
    }

    @ModelAttribute
    public void thongTinThongBaoMoi(Model model, @ModelAttribute("nguoiDung") NguoiDung nguoiDung) {
        if (nguoiDung != null) {
            // Lấy KhachHang theo nguoiDungId
            khachHangRepository.findByNguoiDung_Id(nguoiDung.getId()).ifPresent(khachHang -> {
                Integer khachHangId = khachHang.getId();
                model.addAttribute("thongBaos", thongBaoService.layThongBaoTheoKhachHang(khachHangId));
                model.addAttribute("soLuongThongBaoChuaDoc", thongBaoService.demSoThongBaoChuaDoc(khachHangId));
            });
        }
    }
}
