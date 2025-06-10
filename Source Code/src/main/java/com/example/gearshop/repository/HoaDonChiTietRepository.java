package com.example.gearshop.repository;

import com.example.gearshop.model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {
    Optional<HoaDonChiTiet> findTopByOrderByIdDesc();

    List<HoaDonChiTiet> findByHoaDonID(Integer hoaDonID);

    Optional findById(Integer id);

    @Query("SELECT CASE " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP01%' THEN 'SanPhamMainBoard' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP02%' THEN 'SanPhamCPU' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP03%' THEN 'SanPhamRAM' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP04%' THEN 'SanPhamVGA' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP05%' THEN 'SanPhamOCung' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP06%' THEN 'SanPhamPSU' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP07%' THEN 'SanPhamCooler' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP08%' THEN 'SanPhamCase' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP09%' THEN 'SanPhamManHinh' " +
            "ELSE 'Khác' END, " +
            "SUM(hdct.thanhTien) " +
            "FROM HoaDonChiTiet hdct " +
            "JOIN SanPham sp ON hdct.sanPhamID = sp.id " +
            "GROUP BY CASE " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP01%' THEN 'SanPhamMainBoard' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP02%' THEN 'SanPhamCPU' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP03%' THEN 'SanPhamRAM' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP04%' THEN 'SanPhamVGA' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP05%' THEN 'SanPhamOCung' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP06%' THEN 'SanPhamPSU' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP07%' THEN 'SanPhamCooler' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP08%' THEN 'SanPhamCase' " +
            "WHEN sp.loaiSanPham.maLoaiSP LIKE '%LSP09%' THEN 'SanPhamManHinh' " +
            "ELSE 'Khác' END")
    List<Object[]> doanhThuTheoLoaiSanPham();
}
