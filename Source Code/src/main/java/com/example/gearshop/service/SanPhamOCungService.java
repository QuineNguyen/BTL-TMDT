package com.example.gearshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.SanPhamOCung;
import com.example.gearshop.repository.SanPhamOCungRepository;

@Service
public class SanPhamOCungService {

    @Autowired
    private SanPhamOCungRepository repository;

    public List<SanPhamOCung> filterOCung(String loaiOCung, String dungLuong, Long giaMin, Long giaMax, String sort,
            String thuongHieu) {
        List<SanPhamOCung> danhSach = repository.findAll();

        return danhSach.stream()
                .filter(o -> loaiOCung == null || loaiOCung.isEmpty() || loaiOCung.equals(o.getLoaiOCung()))
                .filter(o -> dungLuong == null || dungLuong.isEmpty() || dungLuong.equals(o.getDungLuong()))
                .filter(o -> thuongHieu == null || thuongHieu.isEmpty()
                        || thuongHieu.equals(o.getSanPham().getThuongHieu().getTenThuongHieu()))
                .filter(o -> giaMin == null || o.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMin)) >= 0)
                .filter(o -> giaMax == null || o.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMax)) <= 0)
                .sorted((o1, o2) -> {
                    if ("giaAsc".equals(sort)) {
                        return o1.getSanPham().getGia().compareTo(o2.getSanPham().getGia());
                    } else if ("giaDesc".equals(sort)) {
                        return o2.getSanPham().getGia().compareTo(o1.getSanPham().getGia());
                    }
                    return 0;
                }).collect(Collectors.toList());
    }

    public List<String> getAllLoaiOCung() {
        return repository.findAllLoaiOCung();
    }

    public List<String> getAllDungLuong() {
        return repository.findAllDungLuong();
    }

    public List<String> getAllThuongHieu() {
        return repository.findAllThuongHieu();
    }

    public SanPhamOCung findBySanPhamID(Integer sanPhamID) {
        return repository.findBySanPhamID(sanPhamID);
    }

    public String taoMaOCungMoi() {
        String maSanPhamCuoi = repository.findMaxMaOCung();
        if (maSanPhamCuoi == null) {
            return "STR00001"; // Mã đầu tiên nếu không có sản phẩm nào
        }
        int soThuTu = Integer.parseInt(maSanPhamCuoi.substring(2)) + 1;
        return String.format("STR%05d", soThuTu); // Tạo mã mới với định dạng OCxxxx
    }

    public SanPhamOCung luuSanPhamOCung(SanPhamOCung sanPhamOCung) {
        return repository.save(sanPhamOCung);
    }
}
