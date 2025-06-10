package com.example.gearshop.service;

import com.example.gearshop.model.LoaiSanPham;
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
import com.example.gearshop.repository.*;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Autowired
    private SanPhamCPURepository cpuRepo;
    @Autowired
    private SanPhamRAMRepository ramRepo;
    @Autowired
    private SanPhamMainBoardRepository mainBoardRepo;
    @Autowired
    private SanPhamVGARepository vgaRepo;
    @Autowired
    private SanPhamOCungRepository oCungRepo;
    @Autowired
    private SanPhamPSURepository psuRepo;
    @Autowired
    private SanPhamCoolerRepository coolerRepo;
    @Autowired
    private SanPhamCaseRepository caseRepo;
    @Autowired
    private SanPhamManHinhRepository manHinhRepo;
    @Autowired
    private ThuongHieuRepository thuongHieuRepository;
    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;
    @Autowired
    private NhanVienRepository nhanVienRepo;
    @Autowired
    private ThuongHieuRepository thuongHieuRepo;
    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepo;
    private SanPhamVGARepository sanPhamVGARepository;

    SanPhamService(SanPhamVGARepository sanPhamVGARepository) {
        this.sanPhamVGARepository = sanPhamVGARepository;
    }

    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    public List<SanPham> getByLoai(String tenLoai) {
        return sanPhamRepository.findByLoaiSanPham_TenLoaiSanPham(tenLoai);
    }

    public SanPham getSanPhamById(Integer id) {
        return sanPhamRepository.findById(id);
    }

    public List<ThuongHieu> getAllThuongHieu() {
        return thuongHieuRepository.findAll();
    }

    public List<LoaiSanPham> getAllLoaiSanPham() {
        return loaiSanPhamRepository.findAll();
    }

    public Object layChiTietTheoLoai(SanPham sp) {
        String tenLoai = sp.getLoaiSanPham().getMaLoaiSP();
        System.out.println("Loại sản phẩm: " + tenLoai);
        switch (tenLoai) {
            case "LSP02":
                return (SanPhamCPU) cpuRepo.findBySanPhamID(sp.getId());
            case "LSP03":
                return (SanPhamRAM) ramRepo.findBySanPhamID(sp.getId());
            case "LSP01":
                System.out.println(((SanPhamMainBoard) mainBoardRepo.findBySanPhamID(sp.getId())).getModelMain());
                return (SanPhamMainBoard) mainBoardRepo.findBySanPhamID(sp.getId());
            case "LSP04":
                return (SanPhamVGA) vgaRepo.findBySanPhamID(sp.getId());
            case "LSP05":
                return (SanPhamOCung) oCungRepo.findBySanPhamID(sp.getId());
            case "LSP06":
                return (SanPhamPSU) psuRepo.findBySanPhamID(sp.getId());
            case "LSP07":
                return (SanPhamCooler) coolerRepo.findBySanPhamID(sp.getId());
            case "LSP08":
                return (SanPhamCase) caseRepo.findBySanPhamID(sp.getId());
            case "LSP09":
                return (SanPhamManHinh) manHinhRepo.findBySanPhamID(sp.getId());
            default:
                return null;
        }
    }

    public void capNhatSanPhamVaChiTiet(SanPham sanPham, Object sanPhamMoi) {

        switch (sanPham.getLoaiSanPham().getMaLoaiSP().toLowerCase()) {
            case "lsp02":
                if (sanPhamMoi instanceof SanPhamCPU cpuMoi) {
                    SanPham sanPhamTuForm = cpuMoi.getSanPham();
                    sanPham.setTenSanPham(sanPhamTuForm.getTenSanPham());
                    sanPham.setGia(sanPhamTuForm.getGia());
                    sanPham.setLoaiSanPham(sanPhamTuForm.getLoaiSanPham());
                    sanPham.setThuongHieu(sanPhamTuForm.getThuongHieu());
                    sanPham.setNguoiThem(sanPhamTuForm.getNguoiThem());
                    sanPham.setNgayThem(LocalDateTime.now());
                    sanPhamRepository.save(sanPham);
                    SanPhamCPU cpu = cpuRepo.findBySanPham(sanPham);
                    if (cpu != null) {
                        cpu.setLoaiCPU(cpuMoi.getLoaiCPU());
                        cpu.setSoNhansoLuong(cpuMoi.getSoNhansoLuong());
                        cpu.setMota(cpuMoi.getMota());
                        cpuRepo.save(cpu);
                    } else {
                        cpuMoi.setSanPham(sanPham);
                        cpuRepo.save(cpuMoi);
                    }
                }
                break;

            case "lsp01":
                if (sanPhamMoi instanceof SanPhamMainBoard mbMoi) {
                    SanPham sanPhamTuForm = mbMoi.getSanPham();
                    sanPham.setTenSanPham(sanPhamTuForm.getTenSanPham());
                    sanPham.setGia(sanPhamTuForm.getGia());
                    sanPham.setLoaiSanPham(sanPhamTuForm.getLoaiSanPham());
                    sanPham.setThuongHieu(sanPhamTuForm.getThuongHieu());
                    sanPham.setNguoiThem(sanPhamTuForm.getNguoiThem());
                    sanPham.setNgayThem(LocalDateTime.now());
                    sanPhamRepository.save(sanPham);
                    SanPhamMainBoard mb = mainBoardRepo.findBySanPham(sanPham);
                    if (mb != null) {
                        mb.setModelMain(mbMoi.getModelMain());
                        mb.setChipset(mbMoi.getChipset());
                        mb.setSocketMain(mbMoi.getSocketMain());
                        mb.setKichThuoc(mbMoi.getKichThuoc());
                        mb.setSoKheRAM(mbMoi.getSoKheRAM());
                        mb.setMota(mbMoi.getMota());
                        mainBoardRepo.save(mb);
                    } else {
                        mbMoi.setSanPham(sanPham);
                        mainBoardRepo.save(mbMoi);
                    }
                }
                break;

            case "lsp03":
                if (sanPhamMoi instanceof SanPhamRAM ramMoi) {
                    SanPham sanPhamTuForm = ramMoi.getSanPham();
                    sanPham.setTenSanPham(sanPhamTuForm.getTenSanPham());
                    sanPham.setGia(sanPhamTuForm.getGia());
                    sanPham.setLoaiSanPham(sanPhamTuForm.getLoaiSanPham());
                    sanPham.setThuongHieu(sanPhamTuForm.getThuongHieu());
                    sanPham.setNguoiThem(sanPhamTuForm.getNguoiThem());
                    sanPham.setNgayThem(LocalDateTime.now());
                    sanPhamRepository.save(sanPham);
                    SanPhamRAM ram = ramRepo.findBySanPham(sanPham);
                    if (ram != null) {
                        ram.setChuanRAM(ramMoi.getChuanRAM());
                        ram.setDungLuong(ramMoi.getDungLuong());
                        ram.setMota(ramMoi.getMota());
                        ramRepo.save(ram);
                    } else {
                        ramMoi.setSanPham(sanPham);
                        ramRepo.save(ramMoi);
                    }
                }
                break;

            case "lsp04":
                if (sanPhamMoi instanceof SanPhamVGA vgaMoi) {
                    SanPham sanPhamTuForm = vgaMoi.getSanPham();
                    sanPham.setTenSanPham(sanPhamTuForm.getTenSanPham());
                    sanPham.setGia(sanPhamTuForm.getGia());
                    sanPham.setLoaiSanPham(sanPhamTuForm.getLoaiSanPham());
                    sanPham.setThuongHieu(sanPhamTuForm.getThuongHieu());
                    sanPham.setNguoiThem(sanPhamTuForm.getNguoiThem());
                    sanPham.setNgayThem(LocalDateTime.now());
                    sanPhamRepository.save(sanPham);
                    SanPhamVGA vga = vgaRepo.findBySanPham(sanPham);
                    if (vga != null) {
                        vga.setKieuBoNho(vgaMoi.getKieuBoNho());
                        vga.setDungLuongBoNho(vgaMoi.getDungLuongBoNho());
                        vga.setChipGPU(vgaMoi.getChipGPU());
                        vga.setMota(vgaMoi.getMota());
                        vgaRepo.save(vga);
                    } else {
                        vgaMoi.setSanPham(sanPham);
                        vgaRepo.save(vgaMoi);
                    }
                }
                break;

            case "lsp05":
                if (sanPhamMoi instanceof SanPhamOCung ocungMoi) {
                    SanPham sanPhamTuForm = ocungMoi.getSanPham();
                    sanPham.setTenSanPham(sanPhamTuForm.getTenSanPham());
                    sanPham.setGia(sanPhamTuForm.getGia());
                    sanPham.setLoaiSanPham(sanPhamTuForm.getLoaiSanPham());
                    sanPham.setThuongHieu(sanPhamTuForm.getThuongHieu());
                    sanPham.setNguoiThem(sanPhamTuForm.getNguoiThem());
                    sanPham.setNgayThem(LocalDateTime.now());
                    sanPhamRepository.save(sanPham);
                    SanPhamOCung ocung = oCungRepo.findBySanPham(sanPham);
                    if (ocung != null) {
                        ocung.setLoaiOCung(ocungMoi.getLoaiOCung());
                        ocung.setDungLuong(ocungMoi.getDungLuong());
                        ocung.setMota(ocungMoi.getMota());
                        oCungRepo.save(ocung);
                    } else {
                        ocungMoi.setSanPham(sanPham);
                        oCungRepo.save(ocungMoi);
                    }
                }
                break;

            case "lsp06":
                if (sanPhamMoi instanceof SanPhamPSU psuMoi) {
                    SanPham sanPhamTuForm = psuMoi.getSanPham();
                    sanPham.setTenSanPham(sanPhamTuForm.getTenSanPham());
                    sanPham.setGia(sanPhamTuForm.getGia());
                    sanPham.setLoaiSanPham(sanPhamTuForm.getLoaiSanPham());
                    sanPham.setThuongHieu(sanPhamTuForm.getThuongHieu());
                    sanPham.setNguoiThem(sanPhamTuForm.getNguoiThem());
                    sanPham.setNgayThem(LocalDateTime.now());
                    sanPhamRepository.save(sanPham);
                    SanPhamPSU psu = psuRepo.findBySanPham(sanPham);
                    if (psu != null) {
                        psu.setDienApVao(psuMoi.getDienApVao());
                        psu.setCongSuat(psuMoi.getCongSuat());
                        psu.setMota(psuMoi.getMota());
                        psuRepo.save(psu);
                    } else {
                        psuMoi.setSanPham(sanPham);
                        psuRepo.save(psuMoi);
                    }
                }
                break;

            case "lsp07":
                if (sanPhamMoi instanceof SanPhamCooler coolerMoi) {
                    SanPham sanPhamTuForm = coolerMoi.getSanPham();
                    sanPham.setTenSanPham(sanPhamTuForm.getTenSanPham());
                    sanPham.setGia(sanPhamTuForm.getGia());
                    sanPham.setLoaiSanPham(sanPhamTuForm.getLoaiSanPham());
                    sanPham.setThuongHieu(sanPhamTuForm.getThuongHieu());
                    sanPham.setNguoiThem(sanPhamTuForm.getNguoiThem());
                    sanPham.setNgayThem(LocalDateTime.now());
                    sanPhamRepository.save(sanPham);
                    SanPhamCooler cooler = coolerRepo.findBySanPham(sanPham);
                    if (cooler != null) {
                        cooler.setLoaiTan(coolerMoi.getLoaiTan());
                        cooler.setCoLED(coolerMoi.getCoLED());
                        cooler.setMota(coolerMoi.getMota());
                        coolerRepo.save(cooler);
                    } else {
                        coolerMoi.setSanPham(sanPham);
                        coolerRepo.save(coolerMoi);
                    }
                }
                break;

            case "lsp08":
                if (sanPhamMoi instanceof SanPhamCase caseMoi) {
                    SanPham sanPhamTuForm = caseMoi.getSanPham();
                    sanPham.setTenSanPham(sanPhamTuForm.getTenSanPham());
                    sanPham.setGia(sanPhamTuForm.getGia());
                    sanPham.setLoaiSanPham(sanPhamTuForm.getLoaiSanPham());
                    sanPham.setThuongHieu(sanPhamTuForm.getThuongHieu());
                    sanPham.setNguoiThem(sanPhamTuForm.getNguoiThem());
                    sanPham.setNgayThem(LocalDateTime.now());
                    sanPhamRepository.save(sanPham);
                    SanPhamCase spCase = caseRepo.findBySanPham(sanPham);
                    if (spCase != null) {
                        spCase.setHoTroMain(caseMoi.getHoTroMain());
                        spCase.setMauCase(caseMoi.getMauCase());
                        spCase.setMota(caseMoi.getMota());
                        caseRepo.save(spCase);
                    } else {
                        caseMoi.setSanPham(sanPham);
                        caseRepo.save(caseMoi);
                    }
                }
                break;

            case "lsp09":
                if (sanPhamMoi instanceof SanPhamManHinh mhMoi) {
                    SanPham sanPhamTuForm = mhMoi.getSanPham();
                    sanPham.setTenSanPham(sanPhamTuForm.getTenSanPham());
                    sanPham.setGia(sanPhamTuForm.getGia());
                    sanPham.setLoaiSanPham(sanPhamTuForm.getLoaiSanPham());
                    sanPham.setThuongHieu(sanPhamTuForm.getThuongHieu());
                    sanPham.setNguoiThem(sanPhamTuForm.getNguoiThem());
                    sanPham.setNgayThem(LocalDateTime.now());
                    sanPhamRepository.save(sanPham);
                    SanPhamManHinh mh = manHinhRepo.findBySanPham(sanPham);
                    if (mh != null) {
                        mh.setKichThuoc(mhMoi.getKichThuoc());
                        mh.setBeMat(mhMoi.getBeMat());
                        mh.setTanSoQuet(mhMoi.getTanSoQuet());
                        mh.setTamNen(mhMoi.getTamNen());
                        mh.setDoPhanGiai(mhMoi.getDoPhanGiai());
                        mh.setMota(mhMoi.getMota());
                        manHinhRepo.save(mh);
                    } else {
                        mhMoi.setSanPham(sanPham);
                        manHinhRepo.save(mhMoi);
                    }
                }
                break;

            default:
                throw new IllegalArgumentException(
                        "Loại sản phẩm không hợp lệ: " + sanPham.getLoaiSanPham().getTenLoaiSanPham().toLowerCase());
        }
    };

    @Transactional
    public void xoaSanPhamVaChiTiet(SanPham sanPham) {
        int idLoai = sanPham.getLoaiSanPham().getId();
        switch (idLoai) {
            case 2: // CPU
                cpuRepo.deleteBySanPham(sanPham);
                break;
            case 3: // RAM
                ramRepo.deleteBySanPham(sanPham);
                break;
            case 1: // MainBoard
                mainBoardRepo.deleteBySanPham(sanPham);
                break;
            case 4: // VGA
                vgaRepo.deleteBySanPham(sanPham);
                break;
            case 5: // OCung
                oCungRepo.deleteBySanPham(sanPham);
                break;
            case 6: // PSU
                psuRepo.deleteBySanPham(sanPham);
                break;
            case 7: // Cooler
                coolerRepo.deleteBySanPham(sanPham);
                break;
            case 8: // Case
                caseRepo.deleteBySanPham(sanPham);
                break;
            case 9: // ManHinh
                manHinhRepo.deleteBySanPham(sanPham);
                break;
        }
        sanPhamRepository.delete(sanPham);
    }

    public SanPham findById(Integer id) {
        return sanPhamRepository.findById(id);
    }

    public String sinhMaSanPham() {
        String maxMaSanPham = sanPhamRepository.findMaxMaSanPham();
        if (maxMaSanPham == null) {
            return "SP00001"; // Trường hợp chưa có sản phẩm nào
        }

        // Tách phần số (bỏ "SP")
        int soThuTu = Integer.parseInt(maxMaSanPham.substring(2));

        // Tăng 1
        soThuTu++;

        // Định dạng lại 5 chữ số, ví dụ: 00001, 00124
        return String.format("SP%05d", soThuTu);

    }

    public SanPham themSanPhamChung(String tenSanPham, String maSanPham, String hinhAnh, Integer thuongHieuID,
            Integer loaiSPID, Integer tonKho, BigDecimal giaBan, NguoiDung nguoiDung) {
        SanPham sanPham = new SanPham();
        Optional<NhanVien> nhanVien = nhanVienRepo.findByNguoiDung_Id(nguoiDung.getId());
        ThuongHieu thuongHieu = thuongHieuRepo.findById(thuongHieuID.longValue()).get();
        LoaiSanPham loaiSanPham = loaiSanPhamRepo.findById(loaiSPID.longValue()).get();
        sanPham.setTenSanPham(tenSanPham);
        sanPham.setMaSanPham(maSanPham);
        sanPham.setHinhAnh(hinhAnh);
        sanPham.setThuongHieu(thuongHieu);
        sanPham.setLoaiSanPham(loaiSanPham);
        sanPham.setTonKho(tonKho);
        sanPham.setDaBan(0);
        sanPham.setGia(giaBan);
        sanPham.setNguoiThem(nhanVien.get());
        sanPham.setNgayThem(LocalDateTime.now());

        sanPhamRepository.save(sanPham);

        return sanPham;
    }

    public void xoaSanPham(SanPham sanPham) {
        sanPhamRepository.delete(sanPham);
    }

    public List<SanPham> timKiemTheoGiaTangDan(String keyword) {
        return sanPhamRepository.findByTenSanPhamContainingIgnoreCaseOrderByGiaAsc(keyword);
    }

    public List<SanPham> timKiemTheoGiaGiamDan(String keyword) {
        return sanPhamRepository.findByTenSanPhamContainingIgnoreCaseOrderByGiaDesc(keyword);
    }

    public List<SanPham> timKiemSanPham(String keyword) {
        return sanPhamRepository.findByTenSanPhamContainingIgnoreCase(keyword);
    }

    public List<SanPham> getSanPhamTuongTu(SanPham sanPham) {
        BigDecimal gia = sanPham.getGia();
        BigDecimal khoangGia = gia.multiply(BigDecimal.valueOf(0.5)); // ±20%
        BigDecimal minGia = gia.subtract(khoangGia);
        BigDecimal maxGia = gia.add(khoangGia);

        Pageable top6 = PageRequest.of(0, 6);
        return sanPhamRepository.findSanPhamTuongTu(
                sanPham.getLoaiSanPham().getId(),
                sanPham.getId(),
                minGia,
                maxGia,
                top6);
    }
}
