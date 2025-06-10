package com.example.gearshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gearshop.model.YeuCauHoanTien;

public interface YeuCauHoanTienRepository extends JpaRepository<YeuCauHoanTien, Integer> {
    // Define custom query methods if needed
    // For example, find by order detail ID or status
    List<YeuCauHoanTien> findByHoaDonChiTietId(Integer hoaDonChiTietId);

    List<YeuCauHoanTien> findByTrangThai(String trangThai);

    List<YeuCauHoanTien> findAll();

    Optional findById(Integer id);
}
