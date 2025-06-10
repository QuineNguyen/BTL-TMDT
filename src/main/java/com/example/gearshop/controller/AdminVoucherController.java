package com.example.gearshop.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.gearshop.model.KhachHang;
import com.example.gearshop.model.Voucher;
import com.example.gearshop.model.VoucherKhachHang;
import com.example.gearshop.repository.KhachHangRepository;
import com.example.gearshop.repository.VoucherKhachHangRepository;
import com.example.gearshop.repository.VoucherRepository;
import com.example.gearshop.service.VoucherService;

@Controller
@RequestMapping("/admin/voucher")
public class AdminVoucherController {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherKhachHangRepository voucherKhachHangRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private VoucherService voucherService;

    // Trang danh sách voucher
    @GetMapping
    public String danhSachVoucher(
            @RequestParam(value = "sort", required = false, defaultValue = "") String sort,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            Model model) {
        List<Voucher> vouchers;

        if (keyword != null && !keyword.isEmpty()) {
            vouchers = voucherRepository.findByTenVoucherContainingIgnoreCase(keyword);
        } else {
            switch (sort) {
                case "dateDesc":
                    vouchers = voucherRepository.findAllByOrderByThoiHanDesc();
                    break;
                case "dateAsc":
                    vouchers = voucherRepository.findAllByOrderByThoiHanAsc();
                    break;
                case "nameAsc":
                    vouchers = voucherRepository.findAllByOrderByTenVoucherAsc();
                    break;
                case "nameDesc":
                    vouchers = voucherRepository.findAllByOrderByTenVoucherDesc();
                    break;
                default:
                    vouchers = voucherRepository.findAll();
            }
        }

        // Xử lý danh sách người được cấp
        Map<Integer, String> nguoiDuocCapMap = new HashMap<>();
        for (Voucher v : vouchers) {
            List<VoucherKhachHang> mappings = voucherKhachHangRepository.findByVoucherId(v.getId());
            if (mappings == null || mappings.isEmpty()) {
                nguoiDuocCapMap.put(v.getId(), "Tất cả khách hàng");
            } else {
                nguoiDuocCapMap.put(v.getId(), "Khách hàng cụ thể");
            }
        }

        model.addAttribute("vouchers", vouchers);
        model.addAttribute("keyword", keyword);
        model.addAttribute("nguoiDuocCapMap", nguoiDuocCapMap);
        return "adminTemplate/voucher"; // Thymeleaf template
    }

    // Xem chi tiết voucher
    @GetMapping("/chitiet/{id}")
    public String chiTietVoucher(@PathVariable Integer id, Model model) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow();
        List<VoucherKhachHang> mappings = voucherKhachHangRepository.findByVoucherId(id);
        String nguoiDuocCap;
        if (mappings.isEmpty()) {
            nguoiDuocCap = "Tất cả khách hàng";
        } else {
            nguoiDuocCap = "Khách hàng cụ thể";
        }

        model.addAttribute("voucher", voucher);
        model.addAttribute("nguoiDuocCap", nguoiDuocCap);
        model.addAttribute("danhSachKhachHang", mappings);

        return "adminTemplate/chitietvoucher";
    }

    // Form thêm voucher
    @GetMapping("/them")
    public String formThemVoucher(Model model, RedirectAttributes redirectAttributes) {
        List<KhachHang> danhSachKhachHang = khachHangRepository.findAll();
        model.addAttribute("danhSachKhachHang", danhSachKhachHang);

        return "adminTemplate/themvoucher";
    }

    // Xử lý thêm voucher
    @PostMapping("/them")
    public String themVoucher(
            @RequestParam String tenVoucher,
            @RequestParam(required = false) Integer giamGiaTheoPhanTram,
            @RequestParam(required = false) BigDecimal giamGiaCuThe,
            @RequestParam String thoiHan,
            @RequestParam BigDecimal donToiThieu,
            @RequestParam(value = "khachHangIds", required = false) String khachHangIdsString,
            RedirectAttributes redirectAttributes) {

        Voucher voucher = new Voucher();
        voucher.setTenVoucher(tenVoucher);
        voucher.setGiamGiaTheoPhanTram(giamGiaTheoPhanTram);
        voucher.setGiamGiaCuThe(giamGiaCuThe);
        voucher.setDonToiThieu(donToiThieu);
        voucher.setThoiHan(LocalDateTime.parse(thoiHan));

        // Tạo mã voucher tự động
        Voucher lastVoucher = voucherRepository.findTopByOrderByIdDesc();
        String maVoucher;

        if (lastVoucher == null) {
            maVoucher = "VC0001";
        } else {
            // Tách phần số
            String lastMa = lastVoucher.getMaVoucher(); // ví dụ "VC0123"
            String so = lastMa.substring(2); // "0123"
            int nextNumber = Integer.parseInt(so) + 1;
            maVoucher = String.format("VC%04d", nextNumber);
        }
        voucher.setMaVoucher(maVoucher);

        voucherRepository.save(voucher);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm voucher thành công.");
        // Mapping với khách hàng
        if (khachHangIdsString != null && !khachHangIdsString.isEmpty()) {
            String[] ids = khachHangIdsString.split(",");
            for (String idStr : ids) {
                Integer id = Integer.parseInt(idStr.trim());
                VoucherKhachHang vkh = new VoucherKhachHang();
                vkh.setVoucher(voucher);
                vkh.setKhachHang(khachHangRepository.findById(id).orElseThrow());
                vkh.setMaVoucherKhachHang("VCKH" + voucher.getId() + id);
                vkh.setDaDung(false);
                voucherKhachHangRepository.save(vkh);
            }
        }

        return "redirect:/admin/voucher";
    }

    // Xoá voucher
    @GetMapping("/xoa/{id}")
    public String xoaVoucher(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            voucherService.xoaVoucherVaLienQuan(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa voucher thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa voucher: " + e.getMessage());
        }
        return "redirect:/admin/voucher"; // redirect về trang danh sách voucher
    }

    // Form sửa voucher
    @GetMapping("/sua/{id}")
    public String formSuaVoucher(@PathVariable Integer id, Model model) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow();
        model.addAttribute("voucher", voucher);

        return "adminTemplate/suavoucher";
    }

    // Xử lý sửa voucher
    @PostMapping("/sua/{id}")
    public String suaVoucher(@PathVariable Integer id,
            @RequestParam String tenVoucher,
            @RequestParam(required = false) Integer giamGiaTheoPhanTram,
            @RequestParam(required = false) BigDecimal giamGiaCuThe,
            @RequestParam String thoiHan,
            @RequestParam BigDecimal donToiThieu, RedirectAttributes redirectAttributes) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow();
        voucher.setTenVoucher(tenVoucher);
        voucher.setGiamGiaTheoPhanTram(giamGiaTheoPhanTram);
        voucher.setGiamGiaCuThe(giamGiaCuThe);
        voucher.setThoiHan(LocalDateTime.parse(thoiHan));
        voucher.setDonToiThieu(donToiThieu);
        voucherRepository.save(voucher);
        redirectAttributes.addFlashAttribute("successMessage", "Sửa voucher thành công.");
        return "redirect:/admin/voucher";
    }
}
