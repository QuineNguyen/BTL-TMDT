package com.example.gearshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.gearshop.model.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    Optional<NguoiDung> findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);

    NguoiDung findByTenDangNhap(String tenDangNhap);

    @Query(value = "SELECT maNguoiDung FROM nguoiDung ORDER BY maNguoiDung DESC LIMIT 1", nativeQuery = true)
    String findMaxMaNguoiDung();

    boolean existsByTenDangNhap(String tenDangNhap);

    boolean existsByEmail(String email);

}
