package com.example.gearshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.KhachHang;
import com.example.gearshop.model.NguoiDung;
import com.example.gearshop.repository.KhachHangRepository;
import com.example.gearshop.repository.NguoiDungRepository;

@Service
public class DangKyService {

    @Autowired
    private NguoiDungRepository nguoiDungRepo;

    @Autowired
    private KhachHangRepository khachHangRepo;

    public String generateMaNguoiDungMoi() {
        String max = nguoiDungRepo.findMaxMaNguoiDung();
        int next = (max != null && max.startsWith("ND")) ? Integer.parseInt(max.substring(2)) + 1 : 1;
        return String.format("ND%04d", next);
    }

    public String generateMaKhachHangMoi() {
        String max = khachHangRepo.findMaxMaKhachHang();
        int next = (max != null && max.startsWith("KH")) ? Integer.parseInt(max.substring(2)) + 1 : 1;
        return String.format("KH%04d", next);
    }

    public boolean dangKyTaiKhoan(String tenDangNhap, String matKhau, String matKhauNhapLai,
            String email, String sdt, String diaChi, String tenNguoiDung, StringBuilder thongBao) {
        if (tenDangNhap.isEmpty() || matKhau.isEmpty() || matKhauNhapLai.isEmpty()
                || email.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
            thongBao.append("Vui lòng điền đầy đủ thông tin.");
            return false;
        }

        if (!matKhau.equals(matKhauNhapLai)) {
            thongBao.append("Mật khẩu xác nhận không khớp.");
            return false;
        }
        if (nguoiDungRepo.existsByTenDangNhap(tenDangNhap)) {
            thongBao.append("Tên đăng nhập đã được sử dụng.");
            return false;
        }

        if (nguoiDungRepo.existsByEmail(email)) {
            thongBao.append("Email đã được sử dụng.");
            return false;
        }

        // Tạo người dùng mới
        NguoiDung nd = new NguoiDung();
        nd.setMaNguoiDung(generateMaNguoiDungMoi());
        nd.setTenDangNhap(tenDangNhap);
        nd.setMatKhau(matKhau);
        nd.setEmail(email);
        nd.setSdt(sdt);
        nd.setDiaChi(diaChi);
        nd.setTenNguoiDung(tenNguoiDung);

        nguoiDungRepo.save(nd); // Lưu và sinh ID tự động

        // Tạo khách hàng tương ứng
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(generateMaKhachHangMoi());
        kh.setGhiChu("");
        kh.setNguoiDung(nd);

        khachHangRepo.save(kh);

        thongBao.append("Đăng ký thành công! Vui long đăng nhập để tiếp tục.");
        return true;
    }
}
