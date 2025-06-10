package com.example.gearshop.controller;

import com.example.gearshop.model.SanPhamCase;
import com.example.gearshop.service.SanPhamCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sanphamcase")
public class SanPhamCaseController {

    @Autowired
    private SanPhamCaseService caseService;

    @GetMapping("/{sanPhamID}")
    public String getCaseBySanPhamID(@PathVariable Integer sanPhamID, Model model) {
        // Lấy chi tiết sản phẩm case theo sanPhamID
        SanPhamCase caseProduct = caseService.findBySanPhamID(sanPhamID);

        // Kiểm tra nếu sản phẩm không tồn tại hoặc không phải case
        if (caseProduct == null || caseProduct.getSanPham().getLoaiSanPham().getId() != 8) {
            return "redirect:/error"; // Chuyển hướng đến trang lỗi nếu không tìm thấy sản phẩm
        }

        // Tách chuỗi mô tả thành danh sách
        List<String> motaList = caseProduct.getMota() != null
                ? Arrays.asList(caseProduct.getMota().split("\\|"))
                : null;

        // Thêm dữ liệu vào model
        model.addAttribute("sanPham", caseProduct.getSanPham());
        model.addAttribute("chiTietSanPham", caseProduct);
        model.addAttribute("motaList", motaList);
        model.addAttribute("loaiSanPham", "Case");

        // Trả về giao diện chung
        return "clientTemplate/chitietsanpham";
    }
}
