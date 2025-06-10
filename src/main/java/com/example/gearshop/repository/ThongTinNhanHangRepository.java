package com.example.gearshop.repository;

import com.example.gearshop.model.ThongTinNhanHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThongTinNhanHangRepository extends JpaRepository<ThongTinNhanHang, Integer> {
    List<ThongTinNhanHang> findByKhachHangID(int khachHangID);

    ThongTinNhanHang findById(int id);
}
