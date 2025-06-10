package com.example.gearshop.controller;

import com.example.gearshop.model.SanPhamMainBoard;
import com.example.gearshop.service.MainboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sanphammainboard")
public class SanPhamMainBoardController {

    @Autowired
    private MainboardService mainboardService;

    @GetMapping("/{id}")
    public String getMainboardById(@PathVariable Integer id, Model model) {
        // Lấy chi tiết sản phẩm Mainboard theo ID
        SanPhamMainBoard mainboard = mainboardService.findById(id);

        // Kiểm tra nếu sản phẩm không tồn tại hoặc không phải Mainboard
        if (mainboard == null || mainboard.getSanPham().getLoaiSanPham().getId() != 1) {
            return "redirect:/error"; // Chuyển hướng đến trang lỗi nếu không tìm thấy sản phẩm
        }

        // Tách chuỗi mô tả thành danh sách
        List<String> motaList = mainboard.getMota() != null
                ? Arrays.asList(mainboard.getMota().split("\\|"))
                : null;

        // Thêm dữ liệu vào model
        model.addAttribute("sanPham", mainboard.getSanPham());
        model.addAttribute("chiTietSanPham", mainboard);
        model.addAttribute("motaList", motaList);
        model.addAttribute("loaiSanPham", "Mainboard");

        // Trả về tên template
        return "clientTemplate/chitietsanpham";
    }
}