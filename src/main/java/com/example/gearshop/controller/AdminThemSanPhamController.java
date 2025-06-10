package com.example.gearshop.controller;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.example.gearshop.model.NguoiDung;
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
import com.example.gearshop.service.SanPhamCPUService;
import com.example.gearshop.service.SanPhamCaseService;
import com.example.gearshop.service.SanPhamCoolerService;
import com.example.gearshop.service.SanPhamMainBoardService;
import com.example.gearshop.service.SanPhamManHinhService;
import com.example.gearshop.service.SanPhamOCungService;
import com.example.gearshop.service.SanPhamPSUService;
import com.example.gearshop.service.SanPhamRAMService;
import com.example.gearshop.service.SanPhamService;
import com.example.gearshop.service.SanPhamVGAService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminThemSanPhamController {

    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private SanPhamMainBoardService sanPhamMainBoardService;
    @Autowired
    private SanPhamCPUService sanPhamCPUService;
    @Autowired
    private SanPhamRAMService sanPhamRAMService;
    @Autowired
    private SanPhamVGAService sanPhamVGAService;
    @Autowired
    private SanPhamOCungService sanPhamOCungService;
    @Autowired
    private SanPhamPSUService sanPhamPSUService;
    @Autowired
    private SanPhamCoolerService sanPhamCoolerService;
    @Autowired
    private SanPhamCaseService sanPhamCaseService;
    @Autowired
    private SanPhamManHinhService sanPhamManHinhService;

    @Autowired
    private HttpSession session;

    @GetMapping("/themsanpham")
    public String hienThiThemSanPham(Model model) {
        model.addAttribute("dsThuongHieu", sanPhamService.getAllThuongHieu());
        return "adminTemplate/themsanpham";
    }

    @PostMapping("/themsanpham")
    public String themSanPham(HttpServletRequest request, @RequestParam("tenSanPham") String tenSanPham,
            @RequestParam("hinhAnhFile") MultipartFile hinhAnhFile, @RequestParam("thuongHieuID") Integer thuongHieuID,
            @RequestParam("loaiSPID") Integer loaiSPID, @RequestParam("giaBan") BigDecimal giaBan,
            @RequestParam("tonKho") Integer tonKho, @RequestParam Map<String, String> allParams, HttpSession session,
            Model model) {

        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        String fileName = StringUtils.cleanPath(hinhAnhFile.getOriginalFilename());
        if (!hinhAnhFile.isEmpty()) {
            try {
                String uploadDir = new ClassPathResource("static/images/product/").getFile().getAbsolutePath();
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(hinhAnhFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "Lỗi khi lưu file hình ảnh: " + e.getMessage());
                return "adminTemplate/themsanpham";
            }
        }
        SanPham sanPham = sanPhamService.themSanPhamChung(tenSanPham, sanPhamService.sinhMaSanPham(), fileName,
                thuongHieuID, loaiSPID,
                tonKho, giaBan, nguoiDung);

        switch (loaiSPID) {
            case 1: // MainBoard
                String maMainBoard = sanPhamMainBoardService.taoMaMainBboardMoi();
                SanPhamMainBoard mb = new SanPhamMainBoard();
                mb.setMaMainBoard(maMainBoard);
                mb.setSanPham(sanPham);
                mb.setModelMain(allParams.get("modelMain"));
                mb.setChipset(allParams.get("chipSet"));
                mb.setSocketMain(allParams.get("socketMain"));
                mb.setKichThuoc(allParams.get("kichThuoc"));
                mb.setSoKheRAM(Integer.parseInt(allParams.get("soKheRAM")));
                mb.setMota(allParams.get("mota"));
                sanPhamMainBoardService.luuSanPhamMainBoard(mb);
                break;

            case 2: // CPU
                String maCPU = sanPhamCPUService.taoMaCPUMoi();
                SanPhamCPU cpu = new SanPhamCPU();
                cpu.setMaCPU(maCPU);
                cpu.setSanPham(sanPham);
                cpu.setLoaiCPU(allParams.get("loaiCPU"));
                cpu.setSoNhansoLuong(allParams.get("soNhansoLuong"));
                cpu.setMota(allParams.get("mota"));
                sanPhamCPUService.luuSanPhamCPU(cpu);
                break;

            // ... tương tự với RAM, VGA, OCung, PSU, Cooler, Case, ManHinh
            // Ví dụ RAM:
            case 3:
                String maRAM = sanPhamRAMService.taoMaRAMMoi();
                SanPhamRAM ram = new SanPhamRAM();
                ram.setMaRAM(maRAM);
                ram.setSanPham(sanPham);
                ram.setChuanRAM(allParams.get("chuanRAM"));
                ram.setDungLuong(allParams.get("dungLuong"));
                ram.setMota(allParams.get("mota"));
                sanPhamRAMService.luuSanPhamRAM(ram);
                break;

            case 4: // VGA
                String maVGA = sanPhamVGAService.taoMaVGAMoi();
                SanPhamVGA vga = new SanPhamVGA();
                vga.setMaVGA(maVGA);
                vga.setSanPham(sanPham);
                vga.setKieuBoNho(allParams.get("kieuBoNho"));
                vga.setDungLuongBoNho(allParams.get("dungLuongBoNho"));
                vga.setChipGPU(allParams.get("chipGPU"));
                vga.setMota(allParams.get("mota"));
                sanPhamVGAService.luuSanPhamVGA(vga);
                break;

            case 5:
                String maOCung = sanPhamOCungService.taoMaOCungMoi();
                SanPhamOCung ocung = new SanPhamOCung();
                ocung.setMaOCung(maOCung);
                ocung.setSanPham(sanPham);
                ocung.setLoaiOCung(allParams.get("loaiOCung"));
                ocung.setDungLuong(allParams.get("dungLuong"));
                ocung.setMota(allParams.get("mota"));
                sanPhamOCungService.luuSanPhamOCung(ocung);
                break;
            case 6:
                String maPSU = sanPhamPSUService.taoMaPSUMoi();
                SanPhamPSU psu = new SanPhamPSU();
                psu.setMaPSU(maPSU);
                psu.setSanPham(sanPham);
                psu.setCongSuat(Integer.parseInt(allParams.get("congSuat")));
                psu.setDienApVao(Integer.parseInt(allParams.get("dienApVao")));
                psu.setMota(allParams.get("mota"));
                sanPhamPSUService.luuSanPhamPSU(psu);
                break;
            case 7:
                String maCooler = sanPhamCoolerService.taoMaCoolerMoi();
                SanPhamCooler cooler = new SanPhamCooler();
                cooler.setMaCooler(maCooler);
                cooler.setSanPham(sanPham);
                cooler.setLoaiTan(allParams.get("loaiTan"));
                cooler.setCoLED(Boolean.parseBoolean(allParams.get("coLED")));
                cooler.setMota(allParams.get("mota"));
                sanPhamCoolerService.luuSanPhamCooler(cooler);
                break;

            case 8: // Case
                String maCase = sanPhamCaseService.taoMaCaseMoi();
                SanPhamCase caseProduct = new SanPhamCase();
                caseProduct.setMaCase(maCase);
                caseProduct.setSanPham(sanPham);
                caseProduct.setHoTroMain(allParams.get("hoTroMain"));
                caseProduct.setMauCase(allParams.get("mauCase"));
                caseProduct.setMota(allParams.get("mota"));
                sanPhamCaseService.luuSanPhamCase(caseProduct);

            case 9:
                String maManHinh = sanPhamManHinhService.taoMaManHinhMoi();
                SanPhamManHinh manHinh = new SanPhamManHinh();
                manHinh.setMaMH(maManHinh);
                manHinh.setSanPham(sanPham);
                manHinh.setKichThuoc(Integer.parseInt(allParams.get("kichThuoc")));
                manHinh.setDoPhanGiai(allParams.get("doPhanGiai"));
                manHinh.setBeMat(allParams.get("beMat"));
                manHinh.setTanSoQuet(Integer.parseInt(allParams.get("tanSoQuet")));
                manHinh.setTamNen(allParams.get("tamNen"));
                manHinh.setMota(allParams.get("mota"));
                sanPhamManHinhService.luuSanPhamManHinh(manHinh);
                break;

            default:
                // Nếu không đúng loại nào, bạn có thể xóa sản phẩm chung vừa tạo hoặc báo lỗi
                sanPhamService.xoaSanPham(sanPham);
                model.addAttribute("error", "Loại sản phẩm không hợp lệ");
                return "admin/themsanpham";
        }
        return "redirect:/admin/quanlysanpham";
    }

}
