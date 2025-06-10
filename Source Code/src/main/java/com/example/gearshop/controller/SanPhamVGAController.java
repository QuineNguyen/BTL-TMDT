package com.example.gearshop.controller;

import com.example.gearshop.model.SanPhamVGA;
import com.example.gearshop.service.SanPhamVGAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sanphamvga")
public class SanPhamVGAController {

    @Autowired
    private SanPhamVGAService vgaService;

    @GetMapping("/{sanPhamID}")
    public String getVGABySanPhamID(@PathVariable Integer sanPhamID, Model model) {
        // Lấy chi tiết sản phẩm VGA theo sanPhamID
        SanPhamVGA vga = vgaService.findBySanPhamID(sanPhamID);

        // Kiểm tra nếu sản phẩm không tồn tại hoặc không phải VGA
        if (vga == null || vga.getSanPham().getLoaiSanPham().getId() != 4) {
            return "redirect:/error"; // Chuyển hướng đến trang lỗi nếu không tìm thấy sản phẩm
        }

        // Tách chuỗi mô tả thành danh sách
        List<String> motaList = vga.getMota() != null
                ? Arrays.asList(vga.getMota().split("\\|"))
                : null;

        // Thêm dữ liệu vào model
        model.addAttribute("sanPham", vga.getSanPham());
        model.addAttribute("chiTietSanPham", vga);
        model.addAttribute("motaList", motaList);
        model.addAttribute("loaiSanPham", "VGA");

        // Trả về giao diện chung
        return "clientTemplate/chitietsanpham";
    }
}
