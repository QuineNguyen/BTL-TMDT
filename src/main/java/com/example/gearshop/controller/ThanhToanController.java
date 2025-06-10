package com.example.gearshop.controller;

import com.example.gearshop.dto.PaymentRequest;
import com.example.gearshop.model.HoaDon;
import com.example.gearshop.repository.KhachHangRepository;
import com.example.gearshop.repository.ThongTinNhanHangRepository;
import com.example.gearshop.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ThanhToanController {

    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private ThongTinNhanHangRepository thongTinNhanHangRepository;

    @GetMapping("/checkout")
    public String showCheckoutPage(HttpSession session, Model model) {
        HoaDon hoaDon = (HoaDon) session.getAttribute("hoaDon");
        if (hoaDon == null) {
            model.addAttribute("error", "Không tìm thấy hóa đơn.");
            return "redirect:/order";
        }

        double totalPrice = hoaDon.getTongGia().doubleValue();
        int qrAmount = (int) (totalPrice / 1000);

        model.addAttribute("qrAmount", qrAmount);
        model.addAttribute("hoaDon", hoaDon);

        return "clientTemplate/thanhtoan";
    }

    @PostMapping(value = "/update-payment-status",
                 consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updatePaymentStatus(
            @RequestBody PaymentRequest req) {
        try {
            int orderId = req.getOrderId();
            double amount = req.getAmount();

            HoaDon hoaDon = hoaDonService.findById(orderId);
            if (hoaDon != null) {
                hoaDon.setTrangThaiDonHang("Paid");
                hoaDonService.save(hoaDon);
            }

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Trạng thái thanh toán đã được cập nhật"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Lỗi cập nhật trạng thái thanh toán: " + e.getMessage()
            ));
        }
    }

    @PostMapping(value = "/update-payment-status-extra-missing",
                 consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updatePaymentStatusExtraMissing(
            @RequestBody PaymentRequest req) {
        try {
            int orderId = req.getOrderId();
            String type = req.getType();
            double amount = req.getAmount();

            HoaDon hoaDon = hoaDonService.findById(orderId);
            if (hoaDon == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Invoice not found with ID: " + orderId
                ));
            }

            String statusPay;
            if ("extra".equals(type)) {
                statusPay = "Extra";
            } else if ("missing".equals(type)) {
                statusPay = "Missing";
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Invalid payment type"
                ));
            }

            hoaDon.setTrangThaiDonHang(statusPay);
            hoaDonService.save(hoaDon);

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Payment status updated successfully"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Error updating payment status: " + e.getMessage()
            ));
        }
    }
}
