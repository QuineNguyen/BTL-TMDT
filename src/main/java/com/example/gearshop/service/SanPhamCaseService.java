package com.example.gearshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.SanPhamCase;
import com.example.gearshop.repository.SanPhamCaseRepository;

@Service
public class SanPhamCaseService {

    @Autowired
    private SanPhamCaseRepository caseRepository;

    public List<SanPhamCase> filterCases(String hoTroMain, String mauSac, Long giaMin,
            Long giaMax, String sort, String thuongHieu) {
        List<SanPhamCase> cases = caseRepository.findAll();

        return cases.stream()
                .filter(c -> thuongHieu == null || thuongHieu.isEmpty()
                        || thuongHieu.equals(c.getSanPham().getThuongHieu().getTenThuongHieu()))
                .filter(c -> hoTroMain == null || hoTroMain.isEmpty()
                        || hoTroMain.equals(c.getHoTroMain()))
                .filter(c -> mauSac == null || mauSac.isEmpty()
                        || mauSac.equals(c.getMauCase()))
                .filter(c -> giaMin == null
                        || c.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMin)) >= 0)
                .filter(c -> giaMax == null
                        || c.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMax)) <= 0)
                .sorted((c1, c2) -> {
                    if ("giaAsc".equals(sort)) {
                        return c1.getSanPham().getGia().compareTo(c2.getSanPham().getGia());
                    } else if ("giaDesc".equals(sort)) {
                        return c2.getSanPham().getGia().compareTo(c1.getSanPham().getGia());
                    }
                    return 0;
                })
                .collect(Collectors.toList());
    }

    public List<String> getAllThuongHieu() {
        return caseRepository.findAllThuongHieu();
    }

    public List<String> getAllHoTroMain() {
        return caseRepository.findAllHoTroMain();
    }

    public List<String> getAllMauCase() {
        return caseRepository.findAllMauCase();
    }

    public SanPhamCase findBySanPhamID(Integer sanPhamID) {
        return caseRepository.findBySanPhamID(sanPhamID);
    }

    public String taoMaCaseMoi() {
        String maSanPhamCuoi = caseRepository.findMaxMaCase();
        if (maSanPhamCuoi == null) {
            return "CASE0001"; // Mã đầu tiên nếu không có sản phẩm nào
        }
        int soThuTu = Integer.parseInt(maSanPhamCuoi.substring(4)) + 1;
        return String.format("CASE%04d", soThuTu);
    }

    public void luuSanPhamCase(SanPhamCase sanPhamCase) {
        caseRepository.save(sanPhamCase);
    }
}