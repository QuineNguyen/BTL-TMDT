package com.example.gearshop.controller;

import com.example.gearshop.model.SanPhamManHinh;
import com.example.gearshop.service.SanPhamManHinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sanphammanhinh")
public class SanPhamManHinhController {

    @Autowired
    private SanPhamManHinhService manHinhService;

    @GetMapping("/{sanPhamID}")
    public String getManHinhBySanPhamID(@PathVariable Integer sanPhamID, Model model) {
        // Lấy chi tiết sản phẩm màn hình theo sanPhamID
        SanPhamManHinh manHinh = manHinhService.findBySanPhamID(sanPhamID);

        // Kiểm tra nếu sản phẩm không tồn tại hoặc không phải màn hình
        if (manHinh == null || manHinh.getSanPham().getLoaiSanPham().getId() != 9) {
            return "redirect:/error"; // Chuyển hướng đến trang lỗi nếu không tìm thấy sản phẩm
        }

        // Tách chuỗi mô tả thành danh sách
        List<String> motaList = manHinh.getMota() != null
                ? Arrays.asList(manHinh.getMota().split("\\|"))
                : null;

        // Thêm dữ liệu vào model
        model.addAttribute("sanPham", manHinh.getSanPham());
        model.addAttribute("chiTietSanPham", manHinh);
        model.addAttribute("motaList", motaList);
        model.addAttribute("loaiSanPham", "Màn Hình");

        // Trả về giao diện chung
        return "clientTemplate/chitietsanpham";
    }
}
