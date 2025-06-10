package com.example.gearshop.controller;

import com.example.gearshop.model.HoaDon;
import com.example.gearshop.model.ThongTinNhanHang;
import com.example.gearshop.model.KhachHang;
import com.example.gearshop.service.HoaDonService;
import com.example.gearshop.service.ThongTinNhanHangService;
import com.example.gearshop.model.Voucher;
import com.example.gearshop.service.VoucherService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class HoaDonController {

    @Autowired
    private ThongTinNhanHangService thongTinNhanHangService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private VoucherService voucherService;

    @PostMapping("/save-order")
    public String saveOrder(HttpSession session, @RequestParam int thongTinNhanHangID, @RequestParam(required = false) String voucherCode, Model model) {
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
        if (khachHang == null) {
            model.addAttribute("error", "Không tìm thấy thông tin khách hàng.");
            return "redirect:/order";
        }

        List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("selectedItems");
        if (cart == null || cart.isEmpty()) {
            model.addAttribute("error", "Giỏ hàng của bạn đang trống.");
            return "redirect:/order";
        }

        double tongGia = 0;
        for (Map<String, Object> item : cart) {
            int quantity = Integer.parseInt(item.get("quantity").toString());
            double price = Double.parseDouble(item.get("price").toString().replace(",", "").replace("₫", "").trim());
            tongGia += quantity * price;
        }

        // Áp dụng giảm giá từ voucher (nếu có)
        double tongGiaSauGiam = tongGia;
        if (voucherCode != null && !voucherCode.isEmpty()) {
            Voucher voucher = voucherService.getVoucherByMaVoucher(voucherCode);
            if (voucher != null) {
                tongGiaSauGiam -= (tongGia * voucher.getGiamGiaTheoPhanTram() / 100);
            }
        }

        tongGiaSauGiam = Math.max(tongGiaSauGiam, 0); // Đảm bảo tổng tiền không âm

        // Lưu hóa đơn
        HoaDon hoaDon = hoaDonService.createHoaDon("HD", thongTinNhanHangID, tongGiaSauGiam);
        System.out.println("Đã tạo hóa đơn với ID: " + hoaDon.getId());
        System.out.println("Tổng tiền sau giảm: " + hoaDon.getTongGia());

        // Lưu chi tiết hóa đơn
        for (Map<String, Object> item : cart) {
            Object quantityObj = item.get("quantity");
            Object priceObj = item.get("priceNumeric");
            Object sanPhamIDObj = item.get("sanPhamID");

            if (quantityObj == null || priceObj == null || sanPhamIDObj == null) {
                model.addAttribute("error", "Dữ liệu giỏ hàng không hợp lệ.");
                return "redirect:/order";
            }

            int quantity = Integer.parseInt(quantityObj.toString());
            double price = Double.parseDouble(priceObj.toString().replace(",", "").replace("₫", "").trim());
            int sanPhamID = Integer.parseInt(sanPhamIDObj.toString());

            hoaDonService.createHoaDonChiTiet("HDCT", hoaDon.getId(), sanPhamID, quantity, quantity * price);
        }
        System.out.println("Đã lưu chi tiết hóa đơn với ID: " + hoaDon.getId());

        model.addAttribute("hoaDon", hoaDon);
        session.setAttribute("hoaDon", hoaDon);
        return "redirect:/checkout";
    }
}
