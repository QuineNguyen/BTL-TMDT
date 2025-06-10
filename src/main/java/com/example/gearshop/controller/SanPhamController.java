package com.example.gearshop.controller;

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
import com.example.gearshop.repository.NguoiDungRepository;
import com.example.gearshop.repository.SanPhamMainBoardRepository;
import com.example.gearshop.service.MainboardService;
import com.example.gearshop.service.SanPhamCPUService;
import com.example.gearshop.service.SanPhamCaseService;
import com.example.gearshop.service.SanPhamCoolerService;
import com.example.gearshop.service.SanPhamManHinhService;
import com.example.gearshop.service.SanPhamOCungService;
import com.example.gearshop.service.SanPhamPSUService;
import com.example.gearshop.service.SanPhamRAMService;
import com.example.gearshop.service.SanPhamService;
import com.example.gearshop.service.SanPhamVGAService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private SanPhamMainBoardRepository sanPhamMainBoardRepo;
    @Autowired
    private MainboardService mainboardService;
    @Autowired
    private SanPhamCPUService cpuService;
    @Autowired
    private SanPhamRAMService RService;
    @Autowired
    private SanPhamVGAService vgaService;
    @Autowired
    private SanPhamOCungService ocungService;
    @Autowired
    private SanPhamCoolerService coolerService;
    @Autowired
    private SanPhamPSUService psuService;
    @Autowired
    private SanPhamCaseService caseService;
    @Autowired
    private SanPhamManHinhService manHinhService;

    @GetMapping("/sanpham")
    public String danhSachSanPham(Model model) {
        List<SanPham> list = sanPhamService.getAllSanPham();
        model.addAttribute("sanphams", list);
        return "sanpham"; // Trả về tên view Thymeleaf: sanpham.html
    }

    @GetMapping("/sanphammainboard")
    public String listMainboards(
            @RequestParam(required = false) String[] thuongHieu,
            @RequestParam(required = false) String[] modelMain,
            @RequestParam(required = false) String[] chipset,
            @RequestParam(required = false) String[] socketMain,
            @RequestParam(required = false) String[] kichThuoc,
            @RequestParam(required = false) String[] soKheRAM,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) String sort,
            Model model) {
        List<SanPhamMainBoard> mainboards = mainboardService.findFiltered(
                thuongHieu, modelMain, chipset, socketMain, kichThuoc, soKheRAM, minPrice, maxPrice, sort);
        System.out.println("Tìm thấy " + mainboards.size() + " mainboard");

        model.addAttribute("mainboards", mainboards);
        model.addAttribute("thuongHieuSet",
                extractSet(mainboards, mb -> mb.getSanPham().getThuongHieu().getTenThuongHieu()));
        model.addAttribute("modelMainSet", extractSet(mainboards, SanPhamMainBoard::getModelMain));
        model.addAttribute("chipsetSet", extractSet(mainboards, SanPhamMainBoard::getChipset));
        model.addAttribute("socketMainSet", extractSet(mainboards, SanPhamMainBoard::getSocketMain));
        model.addAttribute("kichThuocSet", extractSet(mainboards, SanPhamMainBoard::getKichThuoc));
        model.addAttribute("soKheRAMSet",
                extractSet(mainboards, mb -> mb.getSoKheRAM() == null ? null : mb.getSoKheRAM().toString()));

        model.addAttribute("selectedThuongHieu", thuongHieu != null ? thuongHieu : new String[0]);
        model.addAttribute("selectedModelMain", modelMain != null ? modelMain : new String[0]);
        model.addAttribute("selectedChipset", chipset != null ? chipset : new String[0]);
        model.addAttribute("selectedSocketMain", socketMain != null ? socketMain : new String[0]);
        model.addAttribute("selectedKichThuoc", kichThuoc != null ? kichThuoc : new String[0]);
        model.addAttribute("selectedSoKheRAM", soKheRAM != null ? soKheRAM : new String[0]);
        model.addAttribute("minPrice", minPrice != null ? minPrice : 0);
        model.addAttribute("maxPrice", maxPrice != null ? maxPrice : 1000000000);
        model.addAttribute("sort", sort != null ? sort : "default");

        return "clientTemplate/sanphammainboard";
    }

    private Set<String> extractSet(List<SanPhamMainBoard> list, Function<SanPhamMainBoard, String> getter) {
        return list.stream().map(getter).filter(Objects::nonNull).collect(Collectors.toCollection(TreeSet::new));
    }

    @GetMapping("/sanphamcpu")
    public String hienThiCPU(Model model,
            @RequestParam(required = false) String loaiCPU,
            @RequestParam(required = false) String soNhanSoLuong,
            @RequestParam(required = false) Long giaMin,
            @RequestParam(required = false) Long giaMax,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String thuongHieu) {

        List<SanPhamCPU> danhSachCPU = cpuService.filterCPUs(loaiCPU, soNhanSoLuong, giaMin, giaMax, sort, thuongHieu);
        System.out.println("Tìm thấy " + danhSachCPU.size() + " mainboard");
        model.addAttribute("dsCPU", danhSachCPU);
        model.addAttribute("dsLoaiCPU", cpuService.getAllLoaiCPU());
        model.addAttribute("dsSoNhanSoLuong", cpuService.getAllSoNhanSoLuong());
        model.addAttribute("dsThuongHieu", cpuService.getAllThuongHieu());

        model.addAttribute("dsCPU", danhSachCPU);
        model.addAttribute("loaiCPU", loaiCPU);
        model.addAttribute("soNhanSoLuong", soNhanSoLuong);
        model.addAttribute("giaMin", giaMin);
        model.addAttribute("giaMax", giaMax);
        model.addAttribute("sort", sort);

        return "clientTemplate/sanphamcpu";
    }

    @GetMapping("/sanphamram")
    public String hienThiSanPhamRAM(Model model,
            @RequestParam(required = false) String thuongHieu,
            @RequestParam(required = false) String chuanRAM,
            @RequestParam(required = false) String dungLuong,
            @RequestParam(required = false) Long giaMin,
            @RequestParam(required = false) Long giaMax,
            @RequestParam(required = false) String sort) {

        List<SanPhamRAM> danhSach = RService.locSanPham(thuongHieu, chuanRAM, dungLuong, giaMin, giaMax, sort);
        model.addAttribute("dsSanPhamRAM", danhSach);

        model.addAttribute("dsThuongHieu", RService.getTatCaThuongHieu());
        model.addAttribute("dsChuanRAM", RService.getTatCaChuanRAM());
        model.addAttribute("dsDungLuong", RService.getTatCaDungLuong());

        model.addAttribute("dsSanPhamRAM", danhSach);
        model.addAttribute("thuongHieu", thuongHieu);
        model.addAttribute("chuanRAM", chuanRAM);
        model.addAttribute("dungLuong", dungLuong);
        model.addAttribute("giaMin", giaMin);
        model.addAttribute("giaMax", giaMax);
        model.addAttribute("sort", sort);

        return "clientTemplate/sanphamram";
    }

    @GetMapping("/sanphamvga")
    public String hienThiVGA(Model model,
            @RequestParam(required = false) String kieuBoNho,
            @RequestParam(required = false) String dungLuongBoNho,
            @RequestParam(required = false) String chipGPU,
            @RequestParam(required = false) Long giaMin,
            @RequestParam(required = false) Long giaMax,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String thuongHieu) {

        List<SanPhamVGA> danhSachVGA = vgaService.filterVGAs(kieuBoNho, dungLuongBoNho, chipGPU, giaMin, giaMax, sort,
                thuongHieu);

        model.addAttribute("dsVGA", danhSachVGA);
        model.addAttribute("dsKieuBoNho", vgaService.getAllKieuBoNho());
        model.addAttribute("dsDungLuongBoNho", vgaService.getAllDungLuongBoNho());
        model.addAttribute("dsChipGPU", vgaService.getAllChipGPU());
        model.addAttribute("dsThuongHieu", vgaService.getAllThuongHieu());

        model.addAttribute("kieuBoNho", kieuBoNho);
        model.addAttribute("dungLuongBoNho", dungLuongBoNho);
        model.addAttribute("chipGPU", chipGPU);
        model.addAttribute("giaMin", giaMin);
        model.addAttribute("giaMax", giaMax);
        model.addAttribute("sort", sort);
        model.addAttribute("thuongHieu", thuongHieu);

        return "clientTemplate/sanphamvga";
    }

    @GetMapping("/sanphamocung")
    public String hienThiOCung(Model model,
            @RequestParam(required = false) String loaiOCung,
            @RequestParam(required = false) String dungLuong,
            @RequestParam(required = false) Long giaMin,
            @RequestParam(required = false) Long giaMax,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String thuongHieu) {

        List<SanPhamOCung> dsOCung = ocungService.filterOCung(loaiOCung, dungLuong, giaMin, giaMax, sort, thuongHieu);

        model.addAttribute("dsOCung", dsOCung);
        model.addAttribute("dsLoaiOCung", ocungService.getAllLoaiOCung());
        model.addAttribute("dsDungLuong", ocungService.getAllDungLuong());
        model.addAttribute("dsThuongHieu", ocungService.getAllThuongHieu());

        model.addAttribute("loaiOCung", loaiOCung);
        model.addAttribute("dungLuong", dungLuong);
        model.addAttribute("giaMin", giaMin);
        model.addAttribute("giaMax", giaMax);
        model.addAttribute("sort", sort);
        model.addAttribute("thuongHieu", thuongHieu);

        return "clientTemplate/sanphamocung";
    }

    @GetMapping("/sanphamcooler")
    public String hienThiCooler(Model model,
            @RequestParam(required = false) String loaiTan,
            @RequestParam(required = false) Boolean coLED,
            @RequestParam(required = false) Long giaMin,
            @RequestParam(required = false) Long giaMax,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String thuongHieu) {

        List<SanPhamCooler> danhSachCooler = coolerService.filterCooler(loaiTan, coLED, giaMin, giaMax, sort,
                thuongHieu);

        model.addAttribute("dsCooler", danhSachCooler);
        model.addAttribute("dsLoaiTan", coolerService.getAllLoaiTan());
        model.addAttribute("dsCoLED", coolerService.getAllCoLED());
        model.addAttribute("dsThuongHieu", coolerService.getAllThuongHieu());

        model.addAttribute("loaiTan", loaiTan);
        model.addAttribute("coLED", coLED);
        model.addAttribute("giaMin", giaMin);
        model.addAttribute("giaMax", giaMax);
        model.addAttribute("sort", sort);
        model.addAttribute("thuongHieu", thuongHieu);

        return "clientTemplate/sanphamcooler";
    }

    @GetMapping("/sanphampsu")
    public String hienThiPSU(Model model,
            @RequestParam(required = false) Integer dienApVao,
            @RequestParam(required = false) Integer congSuat,
            @RequestParam(required = false) Long giaMin,
            @RequestParam(required = false) Long giaMax,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String thuongHieu) {

        List<SanPhamPSU> danhSachPSU = psuService.filterPSUs(dienApVao, congSuat, giaMin, giaMax, sort, thuongHieu);

        model.addAttribute("dsPSU", danhSachPSU);
        model.addAttribute("dsDienApVao", psuService.getAllDienApVao());
        model.addAttribute("dsCongSuat", psuService.getAllCongSuat());
        model.addAttribute("dsThuongHieu", psuService.getAllThuongHieu());

        model.addAttribute("dienApVao", dienApVao);
        model.addAttribute("congSuat", congSuat);
        model.addAttribute("giaMin", giaMin);
        model.addAttribute("giaMax", giaMax);
        model.addAttribute("sort", sort);
        model.addAttribute("thuongHieu", thuongHieu);

        return "clientTemplate/sanphampsu";
    }

    @GetMapping("/sanphamcase")
    public String hienThiCase(Model model,
            @RequestParam(required = false) String hoTroMain,
            @RequestParam(required = false) String mauCase,
            @RequestParam(required = false) Long giaMin,
            @RequestParam(required = false) Long giaMax,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String thuongHieu) {

        List<SanPhamCase> danhSachCase = caseService.filterCases(hoTroMain, mauCase, giaMin, giaMax, sort, thuongHieu);

        model.addAttribute("dsCase", danhSachCase);
        model.addAttribute("dsHoTroMain", caseService.getAllHoTroMain());
        model.addAttribute("dsMauCase", caseService.getAllMauCase());
        model.addAttribute("dsThuongHieu", caseService.getAllThuongHieu());

        // Trả lại giá trị lọc cho giao diện
        model.addAttribute("hoTroMain", hoTroMain);
        model.addAttribute("mauCase", mauCase);
        model.addAttribute("giaMin", giaMin);
        model.addAttribute("giaMax", giaMax);
        model.addAttribute("sort", sort);
        model.addAttribute("thuongHieu", thuongHieu);

        return "clientTemplate/sanphamcase";
    }

    @GetMapping("/sanphammanhinh")
    public String hienThiManHinh(Model model,
            @RequestParam(required = false) String thuongHieu,
            @RequestParam(required = false) Integer kichThuoc,
            @RequestParam(required = false) String beMat,
            @RequestParam(required = false) Integer tanSoQuet,
            @RequestParam(required = false) String tamNen,
            @RequestParam(required = false) String doPhanGiai,
            @RequestParam(required = false) Long giaMin,
            @RequestParam(required = false) Long giaMax,
            @RequestParam(required = false) String sort) {

        List<SanPhamManHinh> danhSachManHinh = manHinhService.filterManHinh(
                thuongHieu, kichThuoc, beMat, tanSoQuet, tamNen, doPhanGiai, giaMin, giaMax, sort);

        model.addAttribute("dsManHinh", danhSachManHinh);
        model.addAttribute("dsThuongHieu", manHinhService.getAllThuongHieu());
        model.addAttribute("dsKichThuoc", manHinhService.getAllKichThuoc());
        model.addAttribute("dsBeMat", manHinhService.getAllBeMat());
        model.addAttribute("dsTanSoQuet", manHinhService.getAllTanSoQuet());
        model.addAttribute("dsTamNen", manHinhService.getAllTamNen());
        model.addAttribute("dsDoPhanGiai", manHinhService.getAllDoPhanGiai());

        model.addAttribute("thuongHieu", thuongHieu);
        model.addAttribute("kichThuoc", kichThuoc);
        model.addAttribute("beMat", beMat);
        model.addAttribute("tanSoQuet", tanSoQuet);
        model.addAttribute("tamNen", tamNen);
        model.addAttribute("doPhanGiai", doPhanGiai);
        model.addAttribute("giaMin", giaMin);
        model.addAttribute("giaMax", giaMax);
        model.addAttribute("sort", sort);

        return "clientTemplate/sanphammanhinh";
    }
}
