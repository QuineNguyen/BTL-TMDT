package com.example.gearshop.repository;

import com.example.gearshop.model.LoaiSanPham;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Long> {
    LoaiSanPham findByTenLoaiSanPham(String tenLoaiSanPham);

    List<LoaiSanPham> findAll();

    LoaiSanPham findById(Integer id);

}