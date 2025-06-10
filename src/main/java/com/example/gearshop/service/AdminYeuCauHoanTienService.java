package com.example.gearshop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.dto.YeuCauHoanTienDTO;
import com.example.gearshop.model.HoaDon;
import com.example.gearshop.model.HoaDonChiTiet;
import com.example.gearshop.model.KhachHang;
import com.example.gearshop.model.NguoiDung;
import com.example.gearshop.model.SanPham;
import com.example.gearshop.model.ThongBao;
import com.example.gearshop.model.ThongTinNhanHang;
import com.example.gearshop.model.TrangThaiThongBao;
import com.example.gearshop.model.YeuCauHoanTien;
import com.example.gearshop.repository.HoaDonChiTietRepository;
import com.example.gearshop.repository.HoaDonRepository;
import com.example.gearshop.repository.KhachHangRepository;
import com.example.gearshop.repository.NguoiDungRepository;
import com.example.gearshop.repository.SanPhamRepository;
import com.example.gearshop.repository.ThongBaoRepository;
import com.example.gearshop.repository.ThongTinNhanHangRepository;
import com.example.gearshop.repository.YeuCauHoanTienRepository;

@Service
public class AdminYeuCauHoanTienService {
    @Autowired
    private YeuCauHoanTienRepository yeuCauRepo;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepo;
    @Autowired
    private HoaDonRepository hoaDonRepo;
    @Autowired
    private SanPhamRepository sanPhamRepo;
    @Autowired
    private ThongTinNhanHangRepository thongTinNhanHangRepo;
    @Autowired
    private KhachHangRepository khachHangRepo;
    @Autowired
    private NguoiDungRepository nguoiDungRepo;
    @Autowired
    private ThongBaoRepository thongBaoRepo;

    public List<YeuCauHoanTien> getTatCaYeuCau() {
        return yeuCauRepo.findAll();
    }

    public YeuCauHoanTienDTO getChiTietYeuCau(Integer id) {
        YeuCauHoanTien yeuCau = (YeuCauHoanTien) yeuCauRepo.findById(id).get();

        HoaDonChiTiet hoaDonChiTiet = yeuCau.getHoaDonChiTiet();
        HoaDon hoaDon = hoaDonRepo.findById(hoaDonChiTiet.getHoaDonID())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

        SanPham sanPham = sanPhamRepo.findById(hoaDonChiTiet.getSanPhamID());

        ThongTinNhanHang thongTinNhanHang = thongTinNhanHangRepo.findById(hoaDon.getThongTinNhanHang().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin nhận hàng"));

        KhachHang khachHang = khachHangRepo.findById(thongTinNhanHang.getKhachHangID())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

        NguoiDung nguoiDung = nguoiDungRepo.findById(khachHang.getNguoiDung().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        return new YeuCauHoanTienDTO(yeuCau,
                nguoiDung.getTenNguoiDung(),
                nguoiDung.getEmail(),
                sanPham.getTenSanPham(),
                hoaDon.getMaHoaDon(), yeuCau.getLoiNhan());
    }

    public void capNhatTrangThai(Integer id, String trangThai) {
        YeuCauHoanTien yeuCau = (YeuCauHoanTien) yeuCauRepo.findById(id).get();
        yeuCau.setTrangThai(trangThai);
        yeuCauRepo.save(yeuCau);

        HoaDonChiTiet hoaDonChiTiet = yeuCau.getHoaDonChiTiet();
        HoaDon hoaDon = hoaDonRepo.findById(hoaDonChiTiet.getHoaDonID())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));
        ThongTinNhanHang thongTinNhanHang = thongTinNhanHangRepo.findById(hoaDon.getThongTinNhanHang().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin nhận hàng"));
        Integer khachHangID = thongTinNhanHang.getKhachHangID();
        KhachHang khachHang = khachHangRepo.findById(khachHangID).get();

        // Tạo thông báo
        ThongBao thongBao = new ThongBao();
        thongBao.setMaThongBao(generateMaThongBao());
        thongBao.setNgayThongBao(LocalDateTime.now());
        if ("Chap nhan".equalsIgnoreCase(trangThai)) {
            thongBao.setNoiDung("Yêu cầu hoàn tiền của bạn đã được chấp nhận. Vui lòng đến cửa hàng để hoàn tất.");
        } else if ("Tu choi".equalsIgnoreCase(trangThai)) {
            thongBao.setNoiDung(
                    "Yêu cầu hoàn tiền của bạn đã bị từ chối. Vui lòng liên hệ cửa hàng để biết thêm chi tiết.");
        }
        thongBao.setTrangThaiThongBao(TrangThaiThongBao.CHUA_DOC);
        thongBao.setKhachHang(khachHang);
        thongBaoRepo.save(thongBao);
    }

    private String generateMaThongBao() {
        return "TB" + String.format("%05d", new Random().nextInt(100000));
    }
}
