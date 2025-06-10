package com.example.gearshop.controller;

import com.example.gearshop.model.SanPhamOCung;
import com.example.gearshop.service.SanPhamOCungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sanphamocung")
public class SanPhamOCungController {

    @Autowired
    private SanPhamOCungService ocungService;

    @GetMapping("/{sanPhamID}")
    public String getOCungBySanPhamID(@PathVariable Integer sanPhamID, Model model) {
        // Lấy chi tiết sản phẩm ổ cứng theo sanPhamID
        SanPhamOCung ocung = ocungService.findBySanPhamID(sanPhamID);

        // Kiểm tra nếu sản phẩm không tồn tại hoặc không phải ổ cứng
        if (ocung == null || ocung.getSanPham().getLoaiSanPham().getId() != 5) {
            return "redirect:/error"; // Chuyển hướng đến trang lỗi nếu không tìm thấy sản phẩm
        }

        // Tách chuỗi mô tả thành danh sách
        List<String> motaList = ocung.getMota() != null
                ? Arrays.asList(ocung.getMota().split("\\|"))
                : null;

        // Thêm dữ liệu vào model
        model.addAttribute("sanPham", ocung.getSanPham());
        model.addAttribute("chiTietSanPham", ocung);
        model.addAttribute("motaList", motaList);
        model.addAttribute("loaiSanPham", "Ổ Cứng");

        // Trả về giao diện chung
        return "clientTemplate/chitietsanpham";
    }
}
