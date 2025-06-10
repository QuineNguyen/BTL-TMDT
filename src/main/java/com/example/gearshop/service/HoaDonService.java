package com.example.gearshop.service;

import com.example.gearshop.dto.SanPhamTrongHoaDonDTO;
import com.example.gearshop.model.HoaDon;
import com.example.gearshop.model.HoaDonChiTiet;
import com.example.gearshop.model.SanPham;
import com.example.gearshop.repository.HoaDonRepository;
import com.example.gearshop.repository.SanPhamRepository;
import com.example.gearshop.repository.ThongTinNhanHangRepository;
import com.example.gearshop.repository.HoaDonChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private ThongTinNhanHangRepository thongTinNhanHangRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    public HoaDon createHoaDon(String maHoaDonPrefix, int thongTinNhanHangID, double tongGia) {
        String maHoaDon = generateMaHoaDon(maHoaDonPrefix); // Tạo mã hóa đơn tự động

        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(maHoaDon);
        hoaDon.setThongTinNhanHang(thongTinNhanHangRepository.findById(thongTinNhanHangID));
        hoaDon.setNgayTao(java.time.LocalDateTime.now());
        hoaDon.setTongGia(BigDecimal.valueOf(tongGia));
        hoaDon.setTrangThaiDonHang("Unpaid");

        return hoaDonRepository.save(hoaDon); // Lưu vào cơ sở dữ liệu
    }

    public HoaDonChiTiet createHoaDonChiTiet(String maHoaDonChiTietPrefix, int hoaDonID, int sanPhamID, int soLuongSP,
            double thanhTien) {
        System.out.println("Dang luu chi tiet hoa don...");
        System.out.println("Ma hoa don chi tiet: " + maHoaDonChiTietPrefix);
        System.out.println("Hoa don ID: " + hoaDonID);
        System.out.println("San pham ID: " + sanPhamID);
        System.out.println("So luong: " + soLuongSP);
        System.out.println("Thanh tien: " + thanhTien);

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setMaHoaDonChiTiet(maHoaDonChiTietPrefix);
        hoaDonChiTiet.setHoaDonID(hoaDonID);
        hoaDonChiTiet.setSanPhamID(sanPhamID);
        hoaDonChiTiet.setSoLuongSP(soLuongSP);
        hoaDonChiTiet.setThanhTien(BigDecimal.valueOf(thanhTien));

        return hoaDonChiTietRepository.save(hoaDonChiTiet); // Lưu vào cơ sở dữ liệu
    }

    private String generateMaHoaDon(String prefix) {
        Optional<HoaDon> lastHoaDon = hoaDonRepository.findTopByOrderByIdDesc();
        if (lastHoaDon.isPresent()) {
            String lastMaHoaDon = lastHoaDon.get().getMaHoaDon();
            int lastNumber = Integer.parseInt(lastMaHoaDon.replace(prefix, ""));
            return prefix + String.format("%03d", lastNumber + 1);
        }
        return prefix + "001";
    }

    private String generateMaHoaDonChiTiet(String prefix) {
        Optional<HoaDonChiTiet> lastHoaDonChiTiet = hoaDonChiTietRepository.findTopByOrderByIdDesc();
        if (lastHoaDonChiTiet.isPresent()) {
            String lastMaHoaDonChiTiet = lastHoaDonChiTiet.get().getMaHoaDonChiTiet();
            int lastNumber = Integer.parseInt(lastMaHoaDonChiTiet.replace(prefix, ""));
            return prefix + String.format("%03d", lastNumber + 1);
        }
        return prefix + "001";
    }

    public HoaDon findById(int id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    public List<HoaDon> getHoaDonsByKhachHangID(Integer khachHangID, String sortBy, String trangThai) {
        List<HoaDon> hoaDons = hoaDonRepository.findByThongTinNhanHang_KhachHangID(khachHangID);

        // Lọc trạng thái nếu có
        if (trangThai != null && !trangThai.isEmpty()) {
            hoaDons = hoaDons.stream()
                    .filter(hd -> hd.getTrangThaiDonHang().equalsIgnoreCase(trangThai))
                    .toList();
        }

        // Sắp xếp
        if ("ngayTaoAsc".equals(sortBy)) {
            hoaDons.sort(Comparator.comparing(HoaDon::getNgayTao));
        } else if ("ngayTaoDesc".equals(sortBy)) {
            hoaDons.sort(Comparator.comparing(HoaDon::getNgayTao).reversed());
        } else if ("tongGia".equals(sortBy)) {
            hoaDons.sort(Comparator.comparing(HoaDon::getTongGia).reversed());
        }

        return hoaDons;
    }

    public HoaDon getHoaDonById(Integer hoaDonId) {
        return hoaDonRepository.findById(hoaDonId)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
    }

    public List<SanPhamTrongHoaDonDTO> getSanPhamTrongHoaDon(Integer hoaDonID) {
        List<HoaDonChiTiet> chiTietList = hoaDonChiTietRepository.findByHoaDonID(hoaDonID);
        List<SanPhamTrongHoaDonDTO> result = new ArrayList<>();
        for (HoaDonChiTiet chiTiet : chiTietList) {
            SanPham sanPham = sanPhamRepository.findById(chiTiet.getSanPhamID());
            result.add(new SanPhamTrongHoaDonDTO(chiTiet, sanPham));
        }
        return result;
    }

    public HoaDon save(HoaDon hoaDon) {
        if (hoaDon == null || hoaDon.getId() == null) {
            throw new IllegalArgumentException("HoaDon or HoaDon ID must not be null");
        }

        Optional<HoaDon> existingHoaDon = hoaDonRepository.findById(hoaDon.getId());
        if (existingHoaDon.isPresent()) {
            HoaDon updatedHoaDon = existingHoaDon.get();
            updatedHoaDon.setTrangThaiDonHang(hoaDon.getTrangThaiDonHang()); // Cập nhật trạng thái đơn hàng
            updatedHoaDon.setTongGia(hoaDon.getTongGia()); // Cập nhật tổng giá (nếu cần)
            updatedHoaDon.setNgayTao(hoaDon.getNgayTao()); // Cập nhật ngày tạo (nếu cần)
            return hoaDonRepository.save(updatedHoaDon); // Lưu vào cơ sở dữ liệu
        } else {
            throw new IllegalArgumentException("HoaDon with ID " + hoaDon.getId() + " does not exist");
        }
    }
}