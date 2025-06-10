package com.example.gearshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gearshop.dto.YeuCauHoanTienDTO;
import com.example.gearshop.model.YeuCauHoanTien;
import com.example.gearshop.service.AdminYeuCauHoanTienService;

@Controller
@RequestMapping("/admin/yeucauhoantien")
public class AdminYeuCauHoanTienController {
    @Autowired
    private AdminYeuCauHoanTienService yeuCauService;

    @GetMapping
    public String danhSachYeuCau(Model model) {
        List<YeuCauHoanTien> danhSach = yeuCauService.getTatCaYeuCau();
        model.addAttribute("yeuCauHoanTiens", danhSach);
        return "adminTemplate/yeucauhoantien";
    }

    @GetMapping("/chitiet/{id}")
    public String chiTietYeuCau(@PathVariable Integer id, Model model) {
        YeuCauHoanTienDTO dto = yeuCauService.getChiTietYeuCau(id);
        model.addAttribute("yeuCauDTO", dto);
        return "adminTemplate/modalchitietyeucau :: chiTietYeuCauModal";
    }

    @PostMapping("/duyet")
    public String duyetYeuCau(@RequestParam Integer id) {
        yeuCauService.capNhatTrangThai(id, "Chap nhan");
        return "redirect:/admin/yeucauhoantien";
    }

    @PostMapping("/huy")
    public String huyYeuCau(@RequestParam Integer id) {
        yeuCauService.capNhatTrangThai(id, "Tu choi");
        return "redirect:/admin/yeucauhoantien";
    }
}