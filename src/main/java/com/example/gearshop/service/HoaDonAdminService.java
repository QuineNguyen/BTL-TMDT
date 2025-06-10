package com.example.gearshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.HoaDon;
import com.example.gearshop.model.HoaDonChiTiet;
import com.example.gearshop.model.KhachHang;
import com.example.gearshop.model.SanPham;
import com.example.gearshop.repository.HoaDonChiTietRepository;
import com.example.gearshop.repository.HoaDonRepository;
import com.example.gearshop.repository.KhachHangRepository;
import com.example.gearshop.repository.NguoiDungRepository;
import com.example.gearshop.repository.SanPhamRepository;

@Service
public class HoaDonAdminService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public List<HoaDon> getAllHoaDons() {
        return hoaDonRepository.findAll();
    }

    public HoaDon getHoaDonById(Integer id) {
        return hoaDonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn ID: " + id));
    }

    public String getTenKhachHangByThongTinNhanHangID(Integer khachHangID) {
        KhachHang khachHang = khachHangRepository.findById(khachHangID)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng ID: " + khachHangID));
        return khachHang.getNguoiDung().getTenNguoiDung();
    }

    public List<Map<String, Object>> getSanPhamTrongHoaDon(Integer hoaDonID) {
        List<HoaDonChiTiet> chiTietList = hoaDonChiTietRepository.findByHoaDonID(hoaDonID);
        List<Map<String, Object>> result = new ArrayList<>();

        for (HoaDonChiTiet chiTiet : chiTietList) {
            SanPham sanPham = sanPhamRepository.findById(chiTiet.getSanPhamID());
            if (sanPham == null) {
                throw new RuntimeException("Không tìm thấy sản phẩm ID: " + chiTiet.getSanPhamID());
            }

            Map<String, Object> item = new HashMap<>();
            item.put("tenSanPham", sanPham.getTenSanPham());
            item.put("soLuong", chiTiet.getSoLuongSP());
            item.put("thanhTien", chiTiet.getThanhTien());
            result.add(item);
        }
        return result;
    }

    public List<HoaDon> getAllHoaDonsSorted(String sortOrder) {
        Sort sort = Sort.by("ngayTao");
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return hoaDonRepository.findAll(sort);
    }

    public List<HoaDon> getHoaDonByTenKhachHang(String tenNguoiDung) {
        return hoaDonRepository.findByTenNguoiDungContainingIgnoreCase(tenNguoiDung);
    }
}
