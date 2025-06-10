package com.example.gearshop.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gearshop.dto.NguoiDungDTO;
import com.example.gearshop.model.KhachHang;
import com.example.gearshop.model.NguoiDung;
import com.example.gearshop.model.NhanVien;
import com.example.gearshop.model.SanPham;
import com.example.gearshop.model.SanPhamMainBoard;
import com.example.gearshop.repository.KhachHangRepository;
import com.example.gearshop.repository.NguoiDungRepository;
import com.example.gearshop.repository.NhanVienRepository;
import com.example.gearshop.service.NguoiDungService;
import com.example.gearshop.service.SanPhamService;
import com.example.gearshop.service.LoaiSanPhamService;
import com.example.gearshop.service.ThuongHieuService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private NguoiDungRepository nguoiDungRepo;
    @Autowired
    private KhachHangRepository khachHangRepo;
    @Autowired
    private NhanVienRepository nhanVienRepo;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/trangchu")
    public String adminHome() {
        return "adminTemplate/trangchuadmin";
    }

    @GetMapping("/nguoidung")
    public String danhSachNguoiDung(
            @RequestParam(name = "roleFilter", defaultValue = "all") String roleFilter,
            Model model) {

        List<NguoiDung> nguoiDungs = nguoiDungRepo.findAll();
        List<NguoiDungDTO> danhSach = new ArrayList<>();

        for (NguoiDung nd : nguoiDungs) {
            Optional<KhachHang> khachHang = khachHangRepo.findByNguoiDung_Id(nd.getId());
            Optional<NhanVien> nhanVien = nhanVienRepo.findByNguoiDung_Id(nd.getId());

            // Kiểm tra roleFilter
            if (roleFilter.equals("khachhang") && khachHang.isEmpty())
                continue;
            if (roleFilter.equals("nhanvien") && nhanVien.isEmpty())
                continue;

            NguoiDungDTO dto = new NguoiDungDTO();
            dto.setId(nd.getId());
            dto.setTenNguoiDung(nd.getTenNguoiDung());
            dto.setEmail(nd.getEmail());

            if (khachHang.isPresent()) {
                dto.setVaiTro("Khách hàng");
                dto.setGhiChu(khachHang.get().getGhiChu());
            } else if (nhanVien.isPresent()) {
                dto.setVaiTro("Nhân viên");
                dto.setGhiChu(nhanVien.get().getGhiChu());
            } else {
                dto.setVaiTro("Không xác định");
                dto.setGhiChu("");
            }

            danhSach.add(dto);
        }

        model.addAttribute("danhSachNguoiDung", danhSach);
        model.addAttribute("roleFilter", roleFilter); // Để giữ lựa chọn đã chọn
        return "adminTemplate/nguoidung";
    }

    @GetMapping("/nguoidung/{id}")
    public String xemChiTietNguoiDung(@PathVariable("id") Integer id, Model model) {
        Optional<NguoiDung> optional = nguoiDungRepo.findById(id);

        if (optional.isPresent()) {
            NguoiDung nd = optional.get();
            boolean isKhachHang = khachHangRepo.findByNguoiDung_Id(nd.getId()).isPresent();
            boolean isNhanVien = nhanVienRepo.findByNguoiDung_Id(nd.getId()).isPresent();

            String vaiTro = isKhachHang ? "Khách hàng" : isNhanVien ? "Nhân viên" : "Không xác định";
            String ghiChu = isKhachHang
                    ? khachHangRepo.findByNguoiDung_Id(nd.getId()).get().getGhiChu()
                    : isNhanVien
                            ? nhanVienRepo.findByNguoiDung_Id(nd.getId()).get().getGhiChu()
                            : "";

            model.addAttribute("nguoiDung", nd);
            model.addAttribute("vaiTro", vaiTro);
            model.addAttribute("ghiChu", ghiChu);
            model.addAttribute("isKhachHang", isKhachHang);
            model.addAttribute("isNhanVien", isNhanVien);

            return "adminTemplate/chitietnguoidung";
        } else {
            return "redirect:/admin/nguoidung?error=notfound";
        }
    }

    @PostMapping("/nguoidung/xoa/{id}")
    @Transactional
    public String xoaNguoiDung(@PathVariable("id") Integer id) {
        // Xóa bản ghi trong bảng khachhang nếu tồn tại
        khachHangRepo.deleteByNguoiDung_Id(id);

        // Xóa bản ghi trong bảng nhanvien nếu tồn tại
        nhanVienRepo.deleteByNguoiDung_Id(id);

        nguoiDungRepo.deleteById(id); // bạn cần xoá cascade trong DB hoặc code
        return "redirect:/admin/nguoidung";
    }

    @PostMapping("/nguoidung/capquyen/{id}")
    public String capQuyenAdmin(@PathVariable("id") Integer id) {
        nguoiDungService.capNhatQuyenAdmin(id);
        return "redirect:/admin/nguoidung/" + id;
    }

    @PostMapping("/nguoidung/goquyen/{id}")
    public String goQuyenAdmin(@PathVariable("id") Integer id) {
        nguoiDungService.goQuyenAdmin(id);
        return "redirect:/admin/nguoidung/" + id;
    }

    @PostMapping("/nguoidung/capnhatghichu")
    public String capNhatGhiChu(@RequestParam("nguoiDungId") Integer id,
            @RequestParam("ghiChu") String ghiChu) {
        Optional<NguoiDung> nguoiDungOpt = nguoiDungRepo.findById(id);
        if (nguoiDungOpt.isPresent()) {
            NguoiDung nd = nguoiDungOpt.get();
            if (khachHangRepo.existsByNguoiDung_Id(id)) {
                KhachHang kh = khachHangRepo.findByNguoiDung_Id(id).get();
                kh.setGhiChu(ghiChu);
                khachHangRepo.save(kh);
            } else if (nhanVienRepo.existsByNguoiDung_Id(id)) {
                NhanVien nv = nhanVienRepo.findByNguoiDung_Id(id).get();
                nv.setGhiChu(ghiChu);
                nhanVienRepo.save(nv);
            }
        }
        return "redirect:/admin/nguoidung/" + id;
    }

}
