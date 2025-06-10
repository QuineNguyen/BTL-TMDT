package com.example.gearshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.SanPhamCooler;
import com.example.gearshop.repository.SanPhamCoolerRepository;

@Service
public class SanPhamCoolerService {

    @Autowired
    private SanPhamCoolerRepository coolerRepository;

    public List<SanPhamCooler> filterCooler(String loaiTan, Boolean coLED, Long giaMin, Long giaMax, String sort,
            String thuongHieu) {
        List<SanPhamCooler> coolers = coolerRepository.findAll();

        return coolers.stream()
                .filter(cooler -> loaiTan == null || loaiTan.isEmpty() || loaiTan.equals(cooler.getLoaiTan()))
                .filter(cooler -> coLED == null || coLED.equals(cooler.getCoLED()))
                .filter(cooler -> thuongHieu == null || thuongHieu.isEmpty()
                        || thuongHieu.equals(cooler.getSanPham().getThuongHieu().getTenThuongHieu()))
                .filter(cooler -> giaMin == null
                        || cooler.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMin)) >= 0)
                .filter(cooler -> giaMax == null
                        || cooler.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMax)) <= 0)
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
        return coolerRepository.findAllThuongHieu();
    }

    public List<String> getAllLoaiTan() {
        return coolerRepository.findAllLoaiTan();
    }

    public List<Boolean> getAllCoLED() {
        return coolerRepository.findAllCoLED();
    }

    public SanPhamCooler findBySanPhamID(Integer sanPhamID) {
        return coolerRepository.findBySanPhamID(sanPhamID);
    }

    public String taoMaCoolerMoi() {
        String maSanPhamCuoi = coolerRepository.findMaxMaCooler();
        if (maSanPhamCuoi == null) {
            return "TAN00001"; // Mã đầu tiên nếu không có sản phẩm nào
        }
        int soThuTu = Integer.parseInt(maSanPhamCuoi.substring(4)) + 1;
        return String.format("TAN%05d", soThuTu);
    }

    public SanPhamCooler luuSanPhamCooler(SanPhamCooler sanPhamCooler) {
        return coolerRepository.save(sanPhamCooler);
    }
}
