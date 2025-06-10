package com.example.gearshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.SanPhamVGA;
import com.example.gearshop.repository.SanPhamVGARepository;

@Service
public class SanPhamVGAService {
    @Autowired
    private SanPhamVGARepository vgaRepository;

    public List<SanPhamVGA> filterVGAs(String kieuBoNho, String dungLuongBoNho, String chipGPU,
            Long giaMin, Long giaMax, String sort, String thuongHieu) {
        List<SanPhamVGA> vgas = vgaRepository.findAll();

        return vgas.stream()
                .filter(vga -> kieuBoNho == null || kieuBoNho.isEmpty() || kieuBoNho.equals(vga.getKieuBoNho()))
                .filter(vga -> dungLuongBoNho == null || dungLuongBoNho.isEmpty()
                        || dungLuongBoNho.equals(vga.getDungLuongBoNho()))
                .filter(vga -> chipGPU == null || chipGPU.isEmpty() || chipGPU.equals(vga.getChipGPU()))
                .filter(vga -> thuongHieu == null || thuongHieu.isEmpty() ||
                        thuongHieu.equals(vga.getSanPham().getThuongHieu().getTenThuongHieu()))
                .filter(vga -> giaMin == null || vga.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMin)) >= 0)
                .filter(vga -> giaMax == null || vga.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMax)) <= 0)
                .sorted((v1, v2) -> {
                    if ("giaAsc".equals(sort)) {
                        return v1.getSanPham().getGia().compareTo(v2.getSanPham().getGia());
                    } else if ("giaDesc".equals(sort)) {
                        return v2.getSanPham().getGia().compareTo(v1.getSanPham().getGia());
                    }
                    return 0;
                })
                .collect(Collectors.toList());
    }

    public List<String> getAllKieuBoNho() {
        return vgaRepository.findAllKieuBoNho();
    }

    public List<String> getAllDungLuongBoNho() {
        return vgaRepository.findAllDungLuongBoNho();
    }

    public List<String> getAllChipGPU() {
        return vgaRepository.findAllChipGPU();
    }

    public List<String> getAllThuongHieu() {
        return vgaRepository.findAllThuongHieu();
    }

    public SanPhamVGA findBySanPhamID(Integer sanPhamID) {
        return vgaRepository.findBySanPhamID(sanPhamID);
    }

    public String taoMaVGAMoi() {
        String maSanPhamCuoi = vgaRepository.findMaxMaVGA();
        if (maSanPhamCuoi == null) {
            return "VGA0001"; // Mã đầu tiên nếu không có sản phẩm nào
        }
        int soHienTai = Integer.parseInt(maSanPhamCuoi.substring(3));
        int soTiepTheo = soHienTai + 1;
        return String.format("VGA%05d", soTiepTheo); // Tạo mã mới với định dạng VGAXXXX
    }

    public SanPhamVGA luuSanPhamVGA(SanPhamVGA sanPhamVGA) {
        return vgaRepository.save(sanPhamVGA);
    }
}
