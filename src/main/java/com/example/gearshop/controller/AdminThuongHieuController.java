package com.example.gearshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.gearshop.model.ThuongHieu;
import com.example.gearshop.repository.SanPhamRepository;
import com.example.gearshop.repository.ThuongHieuRepository;

@Controller
@RequestMapping("/admin/thuonghieu")
public class AdminThuongHieuController {

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @GetMapping
    public String hienThiThuongHieu(@RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
            Model model) {
        List<ThuongHieu> danhSach;
        switch (sort) {
            case "tenaz":
                danhSach = thuongHieuRepository.findAllByOrderByTenThuongHieuAsc();
                break;
            case "tenza":
                danhSach = thuongHieuRepository.findAllByOrderByTenThuongHieuDesc();
                break;
            default:
                danhSach = thuongHieuRepository.findAllByOrderByIdAsc();
        }
        model.addAttribute("thuongHieus", danhSach);
        model.addAttribute("sort", sort);
        model.addAttribute("thuongHieuMoi", new ThuongHieu());
        return "adminTemplate/thuonghieu";
    }

    @PostMapping("/them")
    public String themThuongHieu(@ModelAttribute ThuongHieu thuongHieuMoi, RedirectAttributes redirect) {
        if (thuongHieuRepository.existsByMaThuongHieu(thuongHieuMoi.getMaThuongHieu())) {
            redirect.addFlashAttribute("error", "Mã thương hiệu đã tồn tại.");
            return "redirect:/admin/thuonghieu";
        }
        thuongHieuRepository.save(thuongHieuMoi);
        redirect.addFlashAttribute("success", "Thêm thương hiệu thành công.");
        return "redirect:/admin/thuonghieu";
    }

    @PostMapping("/xoa/{id}")
    public String xoaThuongHieu(@PathVariable Integer id, RedirectAttributes redirect) {
        if (sanPhamRepository.existsByThuongHieu_Id(id)) {
            redirect.addFlashAttribute("error", "Không thể xóa vì thương hiệu đang được sử dụng.");
        } else {
            thuongHieuRepository.deleteById(id);
            redirect.addFlashAttribute("success", "Xóa thương hiệu thành công.");
        }
        return "redirect:/admin/thuonghieu";
    }

    @PostMapping("/sua")
    public String suaThuongHieu(@ModelAttribute ThuongHieu thuongHieu, RedirectAttributes redirect) {
        thuongHieuRepository.save(thuongHieu);
        redirect.addFlashAttribute("success", "Cập nhật thương hiệu thành công.");
        return "redirect:/admin/thuonghieu";
    }
}
