package com.example.gearshop.repository;

import com.example.gearshop.model.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GioHangRepository extends JpaRepository<GioHang, Integer> {
    GioHang findByMaGioHang(String maGioHang);
}
