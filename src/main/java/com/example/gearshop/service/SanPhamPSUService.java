package com.example.gearshop.service;

import com.example.gearshop.model.SanPhamPSU;
import com.example.gearshop.repository.SanPhamPSURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SanPhamPSUService {

    @Autowired
    private SanPhamPSURepository psuRepository;

    public List<SanPhamPSU> filterPSUs(Integer dienApVao, Integer congSuat,
            Long giaMin, Long giaMax, String sort, String thuongHieu) {
        List<SanPhamPSU> ds = psuRepository.findAll();

        return ds.stream()
                .filter(p -> thuongHieu == null || thuongHieu.isEmpty() ||
                        thuongHieu.equals(p.getSanPham().getThuongHieu().getTenThuongHieu()))
                .filter(p -> dienApVao == null || p.getDienApVao().equals(dienApVao))
                .filter(p -> congSuat == null || p.getCongSuat().equals(congSuat))
                .filter(p -> giaMin == null ||
                        p.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMin)) >= 0)
                .filter(p -> giaMax == null ||
                        p.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMax)) <= 0)
                .sorted((p1, p2) -> {
                    if ("giaAsc".equals(sort)) {
                        return p1.getSanPham().getGia().compareTo(p2.getSanPham().getGia());
                    } else if ("giaDesc".equals(sort)) {
                        return p2.getSanPham().getGia().compareTo(p1.getSanPham().getGia());
                    }
                    return 0;
                })
                .collect(Collectors.toList());
    }

    public List<Integer> getAllDienApVao() {
        return psuRepository.findAllDienApVao();
    }

    public List<Integer> getAllCongSuat() {
        return psuRepository.findAllCongSuat();
    }

    public List<String> getAllThuongHieu() {
        return psuRepository.findAllThuongHieu();
    }

    public SanPhamPSU findBySanPhamID(Integer sanPhamID) {
        return psuRepository.findBySanPhamID(sanPhamID);
    }

    public String taoMaPSUMoi() {
        String maSanPhamCuoi = psuRepository.findMaxMaPSU();
        if (maSanPhamCuoi == null) {
            return "PSU00001"; // Mã đầu tiên nếu không có sản phẩm nào
        }
        int so = Integer.parseInt(maSanPhamCuoi.substring(3)); // Lấy phần số sau "PSU"
        so++;
        return String.format("PSU%05d", so); // Tạo mã mới với định dạng PSUxxxx
    }

    public SanPhamPSU luuSanPhamPSU(SanPhamPSU sanPhamPSU) {
        return psuRepository.save(sanPhamPSU);
    }
}
