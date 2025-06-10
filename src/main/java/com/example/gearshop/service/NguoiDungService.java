package com.example.gearshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.KhachHang;
import com.example.gearshop.model.NguoiDung;
import com.example.gearshop.model.NhanVien;
import com.example.gearshop.repository.KhachHangRepository;
import com.example.gearshop.repository.NguoiDungRepository;
import com.example.gearshop.repository.NhanVienRepository;

import jakarta.transaction.Transactional;

@Service
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public void capNhatThongTin(String tenDangNhap, String sdtMoi, String diaChiMoi) {
        NguoiDung nd = nguoiDungRepository.findByTenDangNhap(tenDangNhap);
        if (nd != null) {
            nd.setSdt(sdtMoi);
            nd.setDiaChi(diaChiMoi);
            nguoiDungRepository.save(nd);
        }
    }

    public String doiMatKhau(String tenDangNhap, String matKhauCu, String matKhauMoi, String xacNhan) {
        NguoiDung nd = nguoiDungRepository.findByTenDangNhap(tenDangNhap);
        if (nd == null) {
            return "Không tìm thấy người dùng.";
        }

        if (!matKhauCu.equals(nd.getMatKhau())) {
            return "Mật khẩu cũ không đúng.";
        }

        if (!matKhauMoi.equals(xacNhan)) {
            return "Mật khẩu mới và xác nhận không khớp.";
        }

        nd.setMatKhau(matKhauMoi);
        nguoiDungRepository.save(nd);
        return "Đổi mật khẩu thành công.";
    }

    public void capNhat(NguoiDung nguoiDung) {
        nguoiDungRepository.save(nguoiDung);
    }

    @Transactional
    public void capNhatQuyenAdmin(Integer id) {
        Optional<NguoiDung> nguoiDungOpt = nguoiDungRepository.findById(id);
        if (nguoiDungOpt.isPresent()) {
            NguoiDung nd = nguoiDungOpt.get();

            // Xóa KhachHang nếu tồn tại
            khachHangRepository.deleteByNguoiDung_Id(nd.getId());

            // Tạo mới NhanVien
            NhanVien nv = new NhanVien();
            nv.setMaNhanVien(String.format("NV%04d", nd.getId()));
            nv.setNguoiDung(nd);
            nv.setGhiChu("Cấp quyền admin từ hệ thống");
            nhanVienRepository.save(nv);
        }
    }

    @Transactional
    public void goQuyenAdmin(Integer id) {
        Optional<NguoiDung> nguoiDungOpt = nguoiDungRepository.findById(id);
        if (nguoiDungOpt.isPresent()) {
            NguoiDung nd = nguoiDungOpt.get();

            nhanVienRepository.deleteByNguoiDung_Id(nd.getId());

            // Có thể gán lại thành khách hàng nếu muốn
            KhachHang kh = new KhachHang();
            kh.setMaKhachHang(String.format("KH%04d", nd.getId()));
            kh.setNguoiDung(nd);
            kh.setGhiChu("Gỡ quyền admin");
            kh.setDoanhThu(0L);
            khachHangRepository.save(kh);
        }
    }
}
