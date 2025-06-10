package com.example.gearshop.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.gearshop.dto.SanPhamTrongHoaDonDTO;
import com.example.gearshop.model.HoaDon;
import com.example.gearshop.model.HoaDonChiTiet;
import com.example.gearshop.model.KhachHang;
import com.example.gearshop.model.NguoiDung;
import com.example.gearshop.repository.HoaDonChiTietRepository;
import com.example.gearshop.repository.KhachHangRepository;
import com.example.gearshop.repository.NguoiDungRepository;
import com.example.gearshop.service.HoaDonService;
import com.example.gearshop.service.YeuCauHoanTienService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GiaoDichController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private YeuCauHoanTienService yeuCauHoanTienService;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @GetMapping("/lichsugiaodich")
    public String lichSuGiaoDich(HttpSession session,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String trangThai,
            Model model) {

        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null) {
            return "redirect:/dangnhap"; // Hoặc trả về lỗi 401
        }
        KhachHang khachHang = khachHangRepository.findByNguoiDung_Id(nguoiDung.getId()).get();
        KhachHang khachHangs = (KhachHang) session.getAttribute("khachHang");
        List<HoaDon> hoaDoncuaKhachHang = hoaDonService.getHoaDonsByKhachHangID(khachHangs.getId(), sortBy, trangThai);
        model.addAttribute("hoaDonKhachHang", hoaDoncuaKhachHang);
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedTrangThai", trangThai);

        return "clientTemplate/lichsugiaodich";
    }

    @GetMapping("/lichsugiaodich/{id}")
    public String chiTietGiaoDich(@PathVariable Integer id,
            HttpSession session,
            Model model) throws AccessDeniedException {

        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null) {
            return "redirect:/dangnhap"; // Hoặc trả về lỗi 401
        }
        KhachHang khachHang = khachHangRepository.findByNguoiDung_Id(nguoiDung.getId()).get();

        HoaDon hoaDon = hoaDonService.getHoaDonById(id);

        List<SanPhamTrongHoaDonDTO> danhSachSanPham = hoaDonService.getSanPhamTrongHoaDon(id);

        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("danhSachSanPham", danhSachSanPham);

        return "clientTemplate/chitietgiaodich";
    }

    @PostMapping("/yeucauhoantien")
    public String yeuCauHoanTien(@RequestParam Integer hoaDonChiTietId,
            @RequestParam String loiNhan,
            RedirectAttributes redirectAttributes) {
        // Lưu yêu cầu hoàn tiền vào CSDL
        HoaDonChiTiet chiTiet = (HoaDonChiTiet) hoaDonChiTietRepository.findById(hoaDonChiTietId).get();
        yeuCauHoanTienService.createYeuCauHoanTien(chiTiet, loiNhan);
        redirectAttributes.addFlashAttribute("successMessage", "Yêu cầu hoàn tiền đã được gửi thành công!");
        return "redirect:/lichsugiaodich/" + chiTiet.getHoaDonID();
    }
}
