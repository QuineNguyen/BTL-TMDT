package com.example.gearshop.controller;

import com.example.gearshop.model.*;
import com.example.gearshop.service.SanPhamService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/chitietsanpham")
public class ChiTietSanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/{id}")
    public String chiTietSanPham(@PathVariable("id") Integer id, Model model, HttpSession session) {
        // Lấy thông tin sản phẩm từ cơ sở dữ liệu dựa trên ID
        SanPham sanPham = sanPhamService.getSanPhamById(id);

        // Kiểm tra nếu sản phẩm không tồn tại
        if (sanPham == null) {
            return "redirect:/error"; // Chuyển hướng đến trang lỗi nếu không tìm thấy sản phẩm
        }

        // Thêm dữ liệu sản phẩm chung vào model
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("loaiSanPham", sanPham.getLoaiSanPham().getTenLoaiSanPham());
        System.out.println("Loai San Pham: " + sanPham.getLoaiSanPham().getTenLoaiSanPham());

        // Lấy thông tin chi tiết theo loại sản phẩm
        Object chiTietSanPham = sanPhamService.layChiTietTheoLoai(sanPham);
        model.addAttribute("chiTietSanPham", chiTietSanPham);

        // Ép kiểu đối tượng chi tiết sản phẩm theo loại
        if (chiTietSanPham instanceof SanPhamMainBoard) {
            SanPhamMainBoard mainBoard = (SanPhamMainBoard) chiTietSanPham;
            List<String> motaList = mainBoard.getMota() != null
                    ? Arrays.asList(mainBoard.getMota().split("\\|"))
                    : null;
            model.addAttribute("motaList", motaList);
        } else if (chiTietSanPham instanceof SanPhamCPU) {
            SanPhamCPU cpu = (SanPhamCPU) chiTietSanPham;
            List<String> motaList = cpu.getMota() != null
                    ? Arrays.asList(cpu.getMota().split("\\|"))
                    : null;
            model.addAttribute("motaList", motaList);
        } else if (chiTietSanPham instanceof SanPhamRAM) {
            SanPhamRAM ram = (SanPhamRAM) chiTietSanPham;
            List<String> motaList = ram.getMota() != null
                    ? Arrays.asList(ram.getMota().split("\\|"))
                    : null;
            model.addAttribute("motaList", motaList);
        } else if (chiTietSanPham instanceof SanPhamVGA) {
            SanPhamVGA vga = (SanPhamVGA) chiTietSanPham;
            List<String> motaList = vga.getMota() != null
                    ? Arrays.asList(vga.getMota().split("\\|"))
                    : null;
            model.addAttribute("motaList", motaList);
        } else if (chiTietSanPham instanceof SanPhamOCung) {
            SanPhamOCung oCung = (SanPhamOCung) chiTietSanPham;
            List<String> motaList = oCung.getMota() != null
                    ? Arrays.asList(oCung.getMota().split("\\|"))
                    : null;
            model.addAttribute("motaList", motaList);
        } else if (chiTietSanPham instanceof SanPhamPSU) {
            SanPhamPSU psu = (SanPhamPSU) chiTietSanPham;
            List<String> motaList = psu.getMota() != null
                    ? Arrays.asList(psu.getMota().split("\\|"))
                    : null;
            model.addAttribute("motaList", motaList);
        } else if (chiTietSanPham instanceof SanPhamCooler) {
            SanPhamCooler cooler = (SanPhamCooler) chiTietSanPham;
            List<String> motaList = cooler.getMota() != null
                    ? Arrays.asList(cooler.getMota().split("\\|"))
                    : null;
            model.addAttribute("motaList", motaList);
        } else if (chiTietSanPham instanceof SanPhamCase) {
            SanPhamCase spCase = (SanPhamCase) chiTietSanPham;
            List<String> motaList = spCase.getMota() != null
                    ? Arrays.asList(spCase.getMota().split("\\|"))
                    : null;
            model.addAttribute("motaList", motaList);
        } else if (chiTietSanPham instanceof SanPhamManHinh) {
            SanPhamManHinh manHinh = (SanPhamManHinh) chiTietSanPham;
            List<String> motaList = manHinh.getMota() != null
                    ? Arrays.asList(manHinh.getMota().split("\\|"))
                    : null;
            model.addAttribute("motaList", motaList);
        }

        List<Integer> sanPhamDaXem = (List<Integer>) session.getAttribute("sanPhamDaXem");
        if (sanPhamDaXem == null) {
            sanPhamDaXem = new ArrayList<>();
        }

        // Nếu chưa có trong danh sách thì thêm vào
        if (!sanPhamDaXem.contains(sanPham.getId())) {
            sanPhamDaXem.add(0, sanPham.getId()); // thêm vào đầu để hiển thị gần nhất trước
            // Nếu chỉ muốn lưu 10 sản phẩm gần nhất:
            if (sanPhamDaXem.size() > 10) {
                sanPhamDaXem = sanPhamDaXem.subList(0, 10);
            }
        }
        session.setAttribute("sanPhamDaXem", sanPhamDaXem);

        // Lưu riêng sản phẩm mới xem nhất để gợi ý
        session.setAttribute("sanPhamMoiXem", sanPham);

        // Trả về template chi tiết sản phẩm
        return "clientTemplate/chitietsanpham";
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<Void> addToCart(@RequestBody Map<String, Object> product, HttpSession session) {
        List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        int sanPhamID = Integer.parseInt(product.get("sanPhamID").toString());
        int quantity = Integer.parseInt(product.get("quantity").toString());

        Optional<Map<String, Object>> existingProduct = cart.stream()
                .filter(item -> Integer.parseInt(item.get("sanPhamID").toString()) == sanPhamID)
                .findFirst();

        if (existingProduct.isPresent()) {
            existingProduct.get().put("quantity",
                    Integer.parseInt(existingProduct.get().get("quantity").toString()) + quantity);
        } else {
            cart.add(product);
        }

        // Lưu giỏ hàng vào session
        System.out.println("Cac san pham trong gio hang:");
        cart.forEach(item -> System.out.println(item));
        session.setAttribute("cart", cart);

        return ResponseEntity.ok().build();
    }
}
