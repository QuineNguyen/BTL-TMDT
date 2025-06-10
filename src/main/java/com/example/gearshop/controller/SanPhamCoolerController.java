package com.example.gearshop.controller;

import com.example.gearshop.model.SanPhamCooler;
import com.example.gearshop.service.SanPhamCoolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sanphamcooler")
public class SanPhamCoolerController {

    @Autowired
    private SanPhamCoolerService coolerService;

    @GetMapping("/{sanPhamID}")
    public String getCoolerBySanPhamID(@PathVariable Integer sanPhamID, Model model) {
        // Lấy chi tiết sản phẩm tản nhiệt theo sanPhamID
        SanPhamCooler cooler = coolerService.findBySanPhamID(sanPhamID);

        // Kiểm tra nếu sản phẩm không tồn tại hoặc không phải tản nhiệt
        if (cooler == null || cooler.getSanPham().getLoaiSanPham().getId() != 7) {
            return "redirect:/error"; // Chuyển hướng đến trang lỗi nếu không tìm thấy sản phẩm
        }

        // Tách chuỗi mô tả thành danh sách
        List<String> motaList = cooler.getMota() != null
                ? Arrays.asList(cooler.getMota().split("\\|"))
                : null;

        // Thêm dữ liệu vào model
        model.addAttribute("sanPham", cooler.getSanPham());
        model.addAttribute("chiTietSanPham", cooler);
        model.addAttribute("motaList", motaList);
        model.addAttribute("loaiSanPham", "Tản Nhiệt");

        // Trả về giao diện chung
        return "clientTemplate/chitietsanpham";
    }
}
