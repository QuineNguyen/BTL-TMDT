package com.example.gearshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gearshop.model.HoaDon;
import com.example.gearshop.service.HoaDonAdminService;

@Controller
@RequestMapping("/admin/hoadon")
public class AdminHoaDonController {
    @Autowired
    private HoaDonAdminService hoaDonService;

    @GetMapping
    public String danhSachHoaDon(
            @RequestParam(value = "sortOrder", required = false, defaultValue = "desc") String sortOrder,
            @RequestParam(value = "tenKhachHang", required = false) String tenKhachHang,
            Model model) {

        List<HoaDon> hoaDons;

        if (tenKhachHang != null && !tenKhachHang.isEmpty()) {
            hoaDons = hoaDonService.getHoaDonByTenKhachHang(tenKhachHang);
        } else {
            hoaDons = hoaDonService.getAllHoaDonsSorted(sortOrder);
        }

        model.addAttribute("hoaDons", hoaDons);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("tenKhachHang", tenKhachHang);
        return "adminTemplate/hoadon";
    }

    @GetMapping("/chitiet/{id}")
    public String chiTietHoaDon(@PathVariable Integer id, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);
        String tenKhachHang = hoaDonService
                .getTenKhachHangByThongTinNhanHangID(hoaDon.getThongTinNhanHang().getKhachHangID());
        List<Map<String, Object>> sanPhamTrongHoaDon = hoaDonService.getSanPhamTrongHoaDon(id);

        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("tenKhachHang", tenKhachHang);
        model.addAttribute("sanPhamList", sanPhamTrongHoaDon);
        return "adminTemplate/hoadonchitiet";
    }
}