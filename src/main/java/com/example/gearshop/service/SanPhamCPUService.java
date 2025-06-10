package com.example.gearshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.SanPhamCPU;
import com.example.gearshop.repository.SanPhamCPURepository;

@Service
public class SanPhamCPUService {
    @Autowired
    private SanPhamCPURepository cpuRepository;

    public List<SanPhamCPU> filterCPUs(String loaiCPU, String soNhanSoLuong, Long giaMin, Long giaMax, String sort,
            String thuongHieu) {
        List<SanPhamCPU> cpus = cpuRepository.findAll();

        return cpus.stream()
                .filter(cpu -> loaiCPU == null || loaiCPU.isEmpty() || loaiCPU.equals(cpu.getLoaiCPU()))
                .filter(cpu -> soNhanSoLuong == null || soNhanSoLuong.isEmpty()
                        || soNhanSoLuong.equals(cpu.getSoNhansoLuong()))
                .filter(cpu -> thuongHieu == null || thuongHieu.isEmpty() ||
                        thuongHieu.equals(cpu.getSanPham().getThuongHieu().getTenThuongHieu()))
                .filter(cpu -> giaMin == null
                        || cpu.getSanPham().getGia().compareTo(java.math.BigDecimal.valueOf(giaMin)) >= 0)
                .filter(cpu -> giaMax == null
                        || cpu.getSanPham().getGia().compareTo(java.math.BigDecimal.valueOf(giaMax)) <= 0)
                .sorted((cpu1, cpu2) -> {
                    if ("giaAsc".equals(sort)) {
                        return cpu1.getSanPham().getGia().compareTo(cpu2.getSanPham().getGia());
                    } else if ("giaDesc".equals(sort)) {
                        return cpu2.getSanPham().getGia().compareTo(cpu1.getSanPham().getGia());
                    }
                    return 0;
                })
                .collect(Collectors.toList());
    }

    public List<String> getAllThuongHieu() {
        return cpuRepository.findAllThuongHieu();
    }

    public List<String> getAllLoaiCPU() {
        return cpuRepository.findAllLoaiCPU();
    }

    public List<String> getAllSoNhanSoLuong() {
        return cpuRepository.findAllSoNhanSoLuong();
    }

    public SanPhamCPU findBySanPhamID(Integer sanPhamID) {
        return cpuRepository.findBySanPhamID(sanPhamID);
    }

    public String taoMaCPUMoi() {
        String maSanPhamCuoi = cpuRepository.findMaxMaCPU();
        if (maSanPhamCuoi == null) {
            return "CPU0001"; // Mã đầu tiên nếu không có sản phẩm nào
        }
        int so = Integer.parseInt(maSanPhamCuoi.substring(3)); // Lấy phần số sau "CPU"
        so++;
        return String.format("CPU%05d", so); // Tạo mã mới với định dạng CPUxxxx
    }

    public SanPhamCPU luuSanPhamCPU(SanPhamCPU sanPhamCPU) {
        return cpuRepository.save(sanPhamCPU);
    }
}
