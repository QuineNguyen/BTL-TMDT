package com.example.gearshop.controller;

import com.example.gearshop.model.SanPhamRAM;
import com.example.gearshop.service.SanPhamRAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sanphamram")
public class SanPhamRAMController {

    @Autowired
    private SanPhamRAMService ramService;

    @GetMapping("/{sanPhamID}")
    public String getRAMBySanPhamID(@PathVariable Integer sanPhamID, Model model) {
        // Lấy chi tiết sản phẩm RAM theo sanPhamID
        SanPhamRAM ram = ramService.findBySanPhamID(sanPhamID);

        // Kiểm tra nếu sản phẩm không tồn tại hoặc không phải RAM
        if (ram == null || ram.getSanPham().getLoaiSanPham().getId() != 3) {
            return "redirect:/error"; // Chuyển hướng đến trang lỗi nếu không tìm thấy sản phẩm
        }

        // Tách chuỗi mô tả thành danh sách
        List<String> motaList = ram.getMota() != null
                ? Arrays.asList(ram.getMota().split("\\|"))
                : null;

        // Thêm dữ liệu vào model
        model.addAttribute("sanPham", ram.getSanPham());
        model.addAttribute("chiTietSanPham", ram);
        model.addAttribute("motaList", motaList);
        model.addAttribute("loaiSanPham", "RAM");

        // Trả về giao diện chung
        return "clientTemplate/chitietsanpham";
    }
}
