package com.example.gearshop.repository;

import com.example.gearshop.model.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {
    List<GioHangChiTiet> findByGioHang_Id(Integer gioHangId);
}