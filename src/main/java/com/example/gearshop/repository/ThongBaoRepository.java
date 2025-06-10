package com.example.gearshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gearshop.model.KhachHang;
import com.example.gearshop.model.ThongBao;
import com.example.gearshop.model.TrangThaiThongBao;

public interface ThongBaoRepository extends JpaRepository<ThongBao, Integer> {
    long countByKhachHangIdAndTrangThaiThongBao(Integer khachHangId, TrangThaiThongBao trangThai);

    List<ThongBao> findByKhachHangIdOrderByNgayThongBaoDesc(Integer khachHangId);

    List<ThongBao> findByKhachHang(KhachHang khachHang);
}
