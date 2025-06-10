package com.example.gearshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.gearshop.model.KhachHang;
import com.example.gearshop.model.ThongTinNhanHang;

public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {

    Optional<KhachHang> findByNguoiDung_Id(int nguoiDungId);

    @Query("SELECT k.maKhachHang FROM KhachHang k ORDER BY k.maKhachHang DESC LIMIT 1")
    String findMaxMaKhachHang();

    void deleteByNguoiDung_Id(Integer id);

    boolean existsByNguoiDung_Id(Integer id);

}
