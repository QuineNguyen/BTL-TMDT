package com.example.gearshop.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gearshop.model.NguoiDung;
import com.example.gearshop.model.NhanVien;
import com.example.gearshop.model.SanPham;
import com.example.gearshop.model.SanPhamCPU;
import com.example.gearshop.model.SanPhamCase;
import com.example.gearshop.model.SanPhamCooler;
import com.example.gearshop.model.SanPhamMainBoard;
import com.example.gearshop.model.SanPhamManHinh;
import com.example.gearshop.model.SanPhamOCung;
import com.example.gearshop.model.SanPhamPSU;
import com.example.gearshop.model.SanPhamRAM;
import com.example.gearshop.model.SanPhamVGA;
import com.example.gearshop.model.ThuongHieu;
import com.example.gearshop.repository.SanPhamCPURepository;
import com.example.gearshop.repository.SanPhamCaseRepository;
import com.example.gearshop.repository.SanPhamCoolerRepository;
import com.example.gearshop.repository.SanPhamMainBoardRepository;
import com.example.gearshop.repository.SanPhamManHinhRepository;
import com.example.gearshop.repository.SanPhamOCungRepository;
import com.example.gearshop.repository.SanPhamPSURepository;
import com.example.gearshop.repository.SanPhamRAMRepository;
import com.example.gearshop.repository.SanPhamVGARepository;
import com.example.gearshop.service.LoaiSanPhamService;
import com.example.gearshop.service.MainboardService;
import com.example.gearshop.service.NguoiDungService;
import com.example.gearshop.service.NhanVienService;
import com.example.gearshop.service.SanPhamService;
import com.example.gearshop.service.ThuongHieuService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminQuanLySanPhamController {

    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @Autowired
    private SanPhamMainBoardRepository sanPhamMainBoardRepository;
    @Autowired
    private SanPhamCPURepository sanPhamCPURepository;
    @Autowired
    private SanPhamRAMRepository sanPhamRAMRepository;
    @Autowired
    private SanPhamVGARepository sanPhamVGARepository;
    @Autowired
    private SanPhamOCungRepository sanPhamOCungRepository;
    @Autowired
    private SanPhamPSURepository sanPhamPSURepository;
    @Autowired
    private SanPhamCoolerRepository sanPhamCoolerRepository;
    @Autowired
    private SanPhamManHinhRepository sanPhamManHinhRepository;
    @Autowired
    private SanPhamCaseRepository sanPhamCaseRepository;

    @GetMapping("/quanlysanpham")
    public String quanLySanPham(@RequestParam(value = "loai", required = false) String loai,
            @RequestParam(value = "sort", required = false) String sort,
            Model model) {
        List<SanPham> danhSachSanPham;
        if (loai == null || loai.isEmpty()) {
            danhSachSanPham = sanPhamService.getAllSanPham();
        } else {
            danhSachSanPham = sanPhamService.getByLoai(loai);
        }

        // Sắp xếp danh sách
        if (sort != null) {
            switch (sort) {
                case "maSanPham_asc" -> danhSachSanPham.sort(Comparator.comparing(SanPham::getMaSanPham));
                case "maSanPham_desc" -> danhSachSanPham.sort(Comparator.comparing(SanPham::getMaSanPham).reversed());

                case "tenSanPham_asc" -> danhSachSanPham.sort(Comparator.comparing(SanPham::getTenSanPham));
                case "tenSanPham_desc" -> danhSachSanPham.sort(Comparator.comparing(SanPham::getTenSanPham).reversed());

                case "thuongHieu_asc" ->
                    danhSachSanPham.sort(Comparator.comparing((SanPham sp) -> sp.getThuongHieu().getTenThuongHieu()));
                case "thuongHieu_desc" -> danhSachSanPham
                        .sort(Comparator.comparing((SanPham sp) -> sp.getThuongHieu().getTenThuongHieu()).reversed());

                case "ngayThem_asc" -> danhSachSanPham.sort(Comparator.comparing(SanPham::getNgayThem));
                case "ngayThem_desc" -> danhSachSanPham.sort(Comparator.comparing(SanPham::getNgayThem).reversed());

                case "gia_asc" -> danhSachSanPham.sort(Comparator.comparing(SanPham::getGia));
                case "gia_desc" -> danhSachSanPham.sort(Comparator.comparing(SanPham::getGia).reversed());
            }
        }
        List<String> loaiSanPhams = Arrays.asList("MainBoard", "CPU", "RAM", "VGA", "Ổ cứng", "PSU", "Tản", "Case",
                "Màn hình");

        model.addAttribute("dsSanPham", danhSachSanPham);
        model.addAttribute("sort", sort);
        model.addAttribute("dsLoaiSanPham", loaiSanPhams); // Gửi danh sách loại sản phẩm
        model.addAttribute("loaiDangChon", loai);
        return "adminTemplate/quanlysanpham";
    }

    @GetMapping("/quanlysanpham/chitiet/{id}")
    public String xemChiTietSanPham(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        if (sanPham == null)
            return "redirect:/admin/quanlysanpham";

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("thuongHieuList", sanPhamService.getAllThuongHieu());
        model.addAttribute("loaiSanPhamList", sanPhamService.getAllLoaiSanPham());
        model.addAttribute("loaiSanPham", sanPham.getLoaiSanPham().getTenLoaiSanPham());
        System.out.println("Loại sản phẩm: " + sanPham.getLoaiSanPham().getTenLoaiSanPham());

        if (sanPham.getLoaiSanPham().getTenLoaiSanPham().equals("Mainboard")) {
            SanPhamMainBoard mainBoard = (SanPhamMainBoard) sanPhamService.layChiTietTheoLoai(sanPham);
            model.addAttribute("chiTietSanPham", mainBoard);
            model.addAttribute("loaiSanPhamRutGon", "mainboard");
        }
        if (sanPham.getLoaiSanPham().getTenLoaiSanPham().equals("CPU")) {
            SanPhamCPU cpu = (SanPhamCPU) sanPhamService.layChiTietTheoLoai(sanPham);
            model.addAttribute("chiTietSanPham", cpu);
            model.addAttribute("loaiSanPhamRutGon", "cpu");
        }
        if (sanPham.getLoaiSanPham().getTenLoaiSanPham().equals("RAM")) {
            SanPhamRAM ram = (SanPhamRAM) sanPhamService.layChiTietTheoLoai(sanPham);
            model.addAttribute("chiTietSanPham", ram);
            model.addAttribute("loaiSanPhamRutGon", "ram");
        }
        if (sanPham.getLoaiSanPham().getTenLoaiSanPham().equals("VGA")) {
            SanPhamVGA vga = (SanPhamVGA) sanPhamService.layChiTietTheoLoai(sanPham);
            model.addAttribute("chiTietSanPham", vga);
            model.addAttribute("loaiSanPhamRutGon", "vga");
        }
        if (sanPham.getLoaiSanPham().getTenLoaiSanPham().equals("Ổ cứng")
                || sanPham.getLoaiSanPham().getMaLoaiSP().equals("LSP05")) {
            SanPhamOCung ocung = (SanPhamOCung) sanPhamService.layChiTietTheoLoai(sanPham);
            model.addAttribute("chiTietSanPham", ocung);
            model.addAttribute("loaiSanPham", "OCung");
            model.addAttribute("loaiSanPhamRutGon", "ocung");
        }
        if (sanPham.getLoaiSanPham().getTenLoaiSanPham().equals("PSU")) {
            SanPhamPSU psu = (SanPhamPSU) sanPhamService.layChiTietTheoLoai(sanPham);
            model.addAttribute("chiTietSanPham", psu);
            model.addAttribute("loaiSanPhamRutGon", "psu");
        }
        if (sanPham.getLoaiSanPham().getTenLoaiSanPham().equals("Cooler")) {
            SanPhamCooler cooler = (SanPhamCooler) sanPhamService.layChiTietTheoLoai(sanPham);
            model.addAttribute("chiTietSanPham", cooler);
            model.addAttribute("loaiSanPhamRutGon", "cooler");
        }
        if (sanPham.getLoaiSanPham().getTenLoaiSanPham().equals("Case")) {
            SanPhamCase caseSp = (SanPhamCase) sanPhamService.layChiTietTheoLoai(sanPham);
            model.addAttribute("chiTietSanPham", caseSp);
            model.addAttribute("loaiSanPhamRutGon", "case");
        }
        if (sanPham.getLoaiSanPham().getTenLoaiSanPham().equals("Màn hình")
                || sanPham.getLoaiSanPham().getMaLoaiSP().equals("LSP09")) {
            SanPhamManHinh manHinh = (SanPhamManHinh) sanPhamService.layChiTietTheoLoai(sanPham);
            model.addAttribute("chiTietSanPham", manHinh);
            model.addAttribute("loaiSanPham", "Man Hinh");
            model.addAttribute("loaiSanPhamRutGon", "manhinh");
        }
        return "adminTemplate/chitietsanpham";
    }

    @GetMapping("/quanlysanpham/capnhat/mainboard/{id}")
    public String hienThiFormCapNhat(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.findById(id);
        SanPhamMainBoard chiTiet = (SanPhamMainBoard) sanPhamService.layChiTietTheoLoai(sanPham);
        List<ThuongHieu> thuongHieus = thuongHieuService.findAll();

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("chiTietSanPham", chiTiet);
        model.addAttribute("loaiSanPham", "Mainboard");
        model.addAttribute("thuongHieus", thuongHieus);

        return "adminTemplate/capnhatsanpham";
    }

    @GetMapping("/quanlysanpham/capnhat/cpu/{id}")
    public String hienThiFormCapNhatCPU(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.findById(id);
        SanPhamCPU chiTiet = (SanPhamCPU) sanPhamService.layChiTietTheoLoai(sanPham);
        List<ThuongHieu> thuongHieus = thuongHieuService.findAll();

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("chiTietSanPham", chiTiet);
        model.addAttribute("loaiSanPham", "CPU");
        model.addAttribute("thuongHieus", thuongHieus);

        return "adminTemplate/capnhatsanpham";
    }

    @GetMapping("/quanlysanpham/capnhat/ram/{id}")
    public String hienThiFormCapNhatRAM(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.findById(id);
        SanPhamRAM chiTiet = (SanPhamRAM) sanPhamService.layChiTietTheoLoai(sanPham);
        List<ThuongHieu> thuongHieus = thuongHieuService.findAll();

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("chiTietSanPham", chiTiet);
        model.addAttribute("loaiSanPham", "RAM");
        model.addAttribute("thuongHieus", thuongHieus);

        return "adminTemplate/capnhatsanpham";
    }

    @GetMapping("/quanlysanpham/capnhat/vga/{id}")
    public String hienThiFormCapNhatVGA(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.findById(id);
        SanPhamVGA chiTiet = (SanPhamVGA) sanPhamService.layChiTietTheoLoai(sanPham);
        List<ThuongHieu> thuongHieus = thuongHieuService.findAll();

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("chiTietSanPham", chiTiet);
        model.addAttribute("loaiSanPham", "VGA");
        model.addAttribute("thuongHieus", thuongHieus);

        return "adminTemplate/capnhatsanpham";
    }

    @GetMapping("/quanlysanpham/capnhat/ocung/{id}")
    public String hienThiFormCapNhatOCung(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.findById(id);
        SanPhamOCung chiTiet = (SanPhamOCung) sanPhamService.layChiTietTheoLoai(sanPham);
        List<ThuongHieu> thuongHieus = thuongHieuService.findAll();

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("chiTietSanPham", chiTiet);
        model.addAttribute("loaiSanPham", "OCung");
        model.addAttribute("thuongHieus", thuongHieus);

        return "adminTemplate/capnhatsanpham";
    }

    @GetMapping("/quanlysanpham/capnhat/psu/{id}")
    public String hienThiFormCapNhatPSU(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.findById(id);
        SanPhamPSU chiTiet = (SanPhamPSU) sanPhamService.layChiTietTheoLoai(sanPham);
        List<ThuongHieu> thuongHieus = thuongHieuService.findAll();

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("chiTietSanPham", chiTiet);
        model.addAttribute("loaiSanPham", "PSU");
        model.addAttribute("thuongHieus", thuongHieus);

        return "adminTemplate/capnhatsanpham";
    }

    @GetMapping("/quanlysanpham/capnhat/cooler/{id}")
    public String hienThiFormCapNhatCooler(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.findById(id);
        SanPhamCooler chiTiet = (SanPhamCooler) sanPhamService.layChiTietTheoLoai(sanPham);
        List<ThuongHieu> thuongHieus = thuongHieuService.findAll();

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("chiTietSanPham", chiTiet);
        model.addAttribute("loaiSanPham", "Cooler");
        model.addAttribute("thuongHieus", thuongHieus);

        return "adminTemplate/capnhatsanpham";
    }

    @GetMapping("/quanlysanpham/capnhat/case/{id}")
    public String hienThiFormCapNhatCase(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.findById(id);
        SanPhamCase chiTiet = (SanPhamCase) sanPhamService.layChiTietTheoLoai(sanPham);
        List<ThuongHieu> thuongHieus = thuongHieuService.findAll();

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("chiTietSanPham", chiTiet);
        model.addAttribute("loaiSanPham", "Case");
        model.addAttribute("thuongHieus", thuongHieus);

        return "adminTemplate/capnhatsanpham";
    }

    @GetMapping("/quanlysanpham/capnhat/manhinh/{id}")
    public String hienThiFormCapNhatManHinh(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.findById(id);
        SanPhamManHinh chiTiet = (SanPhamManHinh) sanPhamService.layChiTietTheoLoai(sanPham);
        List<ThuongHieu> thuongHieus = thuongHieuService.findAll();

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("chiTietSanPham", chiTiet);
        model.addAttribute("loaiSanPham", "ManHinh");
        model.addAttribute("thuongHieus", thuongHieus);

        return "adminTemplate/capnhatsanpham";
    }

    @PostMapping("/quanlysanpham/capnhat/mainboard/{id}")
    public String capNhatSanPhamMainBoard(@ModelAttribute SanPhamMainBoard mainboard, @PathVariable Integer id,
            HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null)
            return "redirect:/dangnhap";

        NhanVien nhanVien = nhanVienService.getNhanVienByNguoiDung(nguoiDung);

        // Lấy SanPham từ mainboard, rồi cập nhật các thông tin chung
        SanPham sanPham = mainboard.getSanPham();
        sanPham.setLoaiSanPham(loaiSanPhamService.getByTenLoaiSanPham("Mainboard"));
        sanPham.setNgayThem(LocalDateTime.now());
        sanPham.setNguoiThem(nhanVien);

        // Gọi service để cập nhật cả SanPham và chi tiết
        sanPhamService.capNhatSanPhamVaChiTiet(sanPham, mainboard);

        return "redirect:/admin/quanlysanpham/chitiet/" + sanPham.getId();
    }

    @PostMapping("/quanlysanpham/capnhat/cpu/{id}")
    public String capNhatSanPhamCPU(@ModelAttribute SanPhamCPU cpu,
            @PathVariable Integer id,
            HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null)
            return "redirect:/dangnhap";
        NhanVien nhanVien = nhanVienService.getNhanVienByNguoiDung(nguoiDung);

        SanPham sanPham = cpu.getSanPham();
        sanPham.setLoaiSanPham(loaiSanPhamService.getByTenLoaiSanPham("CPU"));
        sanPham.setNgayThem(LocalDateTime.now());
        sanPham.setNguoiThem(nhanVien);
        sanPhamService.capNhatSanPhamVaChiTiet(sanPham, cpu);
        return "redirect:/admin/quanlysanpham/chitiet/" + sanPham.getId();
    }

    @PostMapping("/quanlysanpham/capnhat/ram/{id}")
    public String capNhatSanPhamRAM(@ModelAttribute SanPhamRAM ram,
            @PathVariable Integer id,
            HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null)
            return "redirect:/dangnhap";
        NhanVien nhanVien = nhanVienService.getNhanVienByNguoiDung(nguoiDung);
        SanPham sanPham = ram.getSanPham();
        sanPham.setLoaiSanPham(loaiSanPhamService.getByTenLoaiSanPham("RAM"));
        sanPham.setNgayThem(LocalDateTime.now());
        sanPham.setNguoiThem(nhanVien);
        sanPhamService.capNhatSanPhamVaChiTiet(sanPham, ram);
        return "redirect:/admin/quanlysanpham/chitiet/" + sanPham.getId();
    }

    @PostMapping("/quanlysanpham/capnhat/vga/{id}")
    public String capNhatSanPhamVGA(@ModelAttribute SanPhamVGA vga,
            @PathVariable Integer id,
            HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null)
            return "redirect:/dangnhap";
        NhanVien nhanVien = nhanVienService.getNhanVienByNguoiDung(nguoiDung);
        SanPham sanPham = vga.getSanPham();
        sanPham.setLoaiSanPham(loaiSanPhamService.getByTenLoaiSanPham("VGA"));
        sanPham.setNgayThem(LocalDateTime.now());
        sanPham.setNguoiThem(nhanVien);
        sanPhamService.capNhatSanPhamVaChiTiet(sanPham, vga);
        return "redirect:/admin/quanlysanpham/chitiet/" + sanPham.getId();
    }

    @PostMapping("/quanlysanpham/capnhat/ocung/{id}")
    public String capNhatSanPhamOCung(@ModelAttribute SanPhamOCung ocung,
            @PathVariable Integer id,
            HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null)
            return "redirect:/dangnhap";
        NhanVien nhanVien = nhanVienService.getNhanVienByNguoiDung(nguoiDung);
        SanPham sanPham = ocung.getSanPham();
        sanPham.setLoaiSanPham(loaiSanPhamService.getByIdLoaiSanPham(5));
        sanPham.setNgayThem(LocalDateTime.now());
        sanPham.setNguoiThem(nhanVien);
        sanPhamService.capNhatSanPhamVaChiTiet(sanPham, ocung);
        return "redirect:/admin/quanlysanpham/chitiet/" + sanPham.getId();
    }

    @PostMapping("/quanlysanpham/capnhat/psu/{id}")
    public String capNhatSanPhamPSU(@ModelAttribute SanPhamPSU psu,
            @PathVariable Integer id,
            HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null)
            return "redirect:/dangnhap";
        NhanVien nhanVien = nhanVienService.getNhanVienByNguoiDung(nguoiDung);
        SanPham sanPham = psu.getSanPham();
        sanPham.setLoaiSanPham(loaiSanPhamService.getByTenLoaiSanPham("PSU"));
        sanPham.setNgayThem(LocalDateTime.now());
        sanPham.setNguoiThem(nhanVien);
        sanPhamService.capNhatSanPhamVaChiTiet(sanPham, psu);
        return "redirect:/admin/quanlysanpham/chitiet/" + sanPham.getId();
    }

    @PostMapping("/quanlysanpham/capnhat/cooler/{id}")
    public String capNhatSanPhamCooler(@ModelAttribute SanPhamCooler cooler,
            @PathVariable Integer id,
            HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null)
            return "redirect:/dangnhap";
        NhanVien nhanVien = nhanVienService.getNhanVienByNguoiDung(nguoiDung);
        SanPham sanPham = cooler.getSanPham();
        sanPham.setLoaiSanPham(loaiSanPhamService.getByTenLoaiSanPham("Cooler"));
        sanPham.setNgayThem(LocalDateTime.now());
        sanPham.setNguoiThem(nhanVien);
        sanPhamService.capNhatSanPhamVaChiTiet(sanPham, cooler);
        return "redirect:/admin/quanlysanpham/chitiet/" + sanPham.getId();
    }

    @PostMapping("/quanlysanpham/capnhat/case/{id}")
    public String capNhatSanPhamCase(@ModelAttribute SanPhamCase caseSp,
            @PathVariable Integer id,
            HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null)
            return "redirect:/dangnhap";
        NhanVien nhanVien = nhanVienService.getNhanVienByNguoiDung(nguoiDung);
        SanPham sanPham = caseSp.getSanPham();
        sanPham.setLoaiSanPham(loaiSanPhamService.getByTenLoaiSanPham("Case"));
        sanPham.setNgayThem(LocalDateTime.now());
        sanPham.setNguoiThem(nhanVien);
        sanPhamService.capNhatSanPhamVaChiTiet(sanPham, caseSp);
        return "redirect:/admin/quanlysanpham/chitiet/" + sanPham.getId();
    }

    @PostMapping("/quanlysanpham/capnhat/manhinh/{id}")
    public String capNhatSanPhamManHinh(@ModelAttribute SanPhamManHinh manHinh,
            @PathVariable Integer id,
            HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null)
            return "redirect:/dangnhap";
        NhanVien nhanVien = nhanVienService.getNhanVienByNguoiDung(nguoiDung);
        SanPham sanPham = manHinh.getSanPham();
        sanPham.setLoaiSanPham(loaiSanPhamService.getByIdLoaiSanPham(9));
        sanPham.setNgayThem(LocalDateTime.now());
        sanPham.setNguoiThem(nhanVien);
        sanPhamService.capNhatSanPhamVaChiTiet(sanPham, manHinh);
        return "redirect:/admin/quanlysanpham/chitiet/" + sanPham.getId();
    }

    @GetMapping("/xoasanpham/{id}")
    public String xoaSanPham(@PathVariable Integer id) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        if (sanPham != null) {
            sanPhamService.xoaSanPhamVaChiTiet(sanPham);
        }
        return "redirect:/admin/quanlysanpham";
    }
}
