package com.example.gearshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.SanPhamManHinh;
import com.example.gearshop.repository.SanPhamManHinhRepository;

@Service
public class SanPhamManHinhService {
    @Autowired
    private SanPhamManHinhRepository manHinhRepository;

    public List<SanPhamManHinh> filterManHinh(String thuongHieu, Integer kichThuoc, String beMat, Integer tanSoQuet,
            String tamNen, String doPhanGiai, Long giaMin, Long giaMax, String sort) {

        List<SanPhamManHinh> manHinhs = manHinhRepository.findAll();

        return manHinhs.stream()
                .filter(mh -> thuongHieu == null || thuongHieu.isEmpty()
                        || thuongHieu.equals(mh.getSanPham().getThuongHieu().getTenThuongHieu()))
                .filter(mh -> kichThuoc == null || kichThuoc.equals(mh.getKichThuoc()))
                .filter(mh -> beMat == null || beMat.isEmpty() || beMat.equals(mh.getBeMat()))
                .filter(mh -> tanSoQuet == null || tanSoQuet.equals(mh.getTanSoQuet()))
                .filter(mh -> tamNen == null || tamNen.isEmpty() || tamNen.equals(mh.getTamNen()))
                .filter(mh -> doPhanGiai == null || doPhanGiai.isEmpty() || doPhanGiai.equals(mh.getDoPhanGiai()))
                .filter(mh -> giaMin == null || mh.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMin)) >= 0)
                .filter(mh -> giaMax == null || mh.getSanPham().getGia().compareTo(BigDecimal.valueOf(giaMax)) <= 0)
                .sorted((mh1, mh2) -> {
                    if ("giaAsc".equals(sort)) {
                        return mh1.getSanPham().getGia().compareTo(mh2.getSanPham().getGia());
                    } else if ("giaDesc".equals(sort)) {
                        return mh2.getSanPham().getGia().compareTo(mh1.getSanPham().getGia());
                    }
                    return 0;
                })
                .collect(Collectors.toList());
    }

    public List<Integer> getAllKichThuoc() {
        return manHinhRepository.findAllKichThuoc();
    }

    public List<String> getAllBeMat() {
        return manHinhRepository.findAllBeMat();
    }

    public List<Integer> getAllTanSoQuet() {
        return manHinhRepository.findAllTanSoQuet();
    }

    public List<String> getAllTamNen() {
        return manHinhRepository.findAllTamNen();
    }

    public List<String> getAllDoPhanGiai() {
        return manHinhRepository.findAllDoPhanGiai();
    }

    public List<String> getAllThuongHieu() {
        return manHinhRepository.findAllThuongHieu();
    }

    public SanPhamManHinh findBySanPhamID(Integer sanPhamID) {
        return manHinhRepository.findBySanPhamID(sanPhamID);
    }

    public String taoMaManHinhMoi() {
        String maSanPhamCuoi = manHinhRepository.findMaxMaManHinh();
        if (maSanPhamCuoi == null) {
            return "MH00001"; // Mã đầu tiên nếu không có sản phẩm nào
        }
        int soThuTu = Integer.parseInt(maSanPhamCuoi.substring(2)) + 1; // Lấy số thứ tự sau "MNH"
        return String.format("MH%05d", soThuTu); // Định dạng lại mã mới
    }

    public SanPhamManHinh luuSanPhamManHinh(SanPhamManHinh sanPhamManHinh) {
        return manHinhRepository.save(sanPhamManHinh);
    }
}
