package com.example.gearshop.repository;

import com.example.gearshop.model.SanPham;
import com.example.gearshop.model.LoaiSanPham;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    List<SanPham> findTop10ByOrderByDaBanDesc(); // top sản phẩm bán chạy

    List<SanPham> findTop10ByLoaiSanPhamOrderByDaBanDesc(LoaiSanPham loaiSanPham); // theo loại

    List<SanPham> findByTenSanPhamContainingIgnoreCaseOrderByGiaAsc(String keyword);

    List<SanPham> findByTenSanPhamContainingIgnoreCaseOrderByGiaDesc(String keyword);

    List<SanPham> findByTenSanPhamContainingIgnoreCase(String keyword);

    List<SanPham> findByLoaiSanPham_TenLoaiSanPham(String tenLoaiSanPham);

    @Query("SELECT sp FROM SanPham sp WHERE sp.id = :sanPhamID")
    SanPham findById(@Param("sanPhamID") Integer sanPhamID);

    @Query("SELECT MAX(sp.maSanPham) FROM SanPham sp")
    String findMaxMaSanPham();

    Boolean existsByThuongHieu_Id(Integer id);

    @Query("SELECT s FROM SanPham s " +
            "WHERE s.loaiSanPham.id = :loaiSPID " +
            "AND s.id <> :sanPhamID " +
            "AND s.gia BETWEEN :minGia AND :maxGia")
    List<SanPham> findSanPhamTuongTu(
            @Param("loaiSPID") Integer loaiSPID,
            @Param("sanPhamID") Integer sanPhamID,
            @Param("minGia") BigDecimal minGia,
            @Param("maxGia") BigDecimal maxGia,
            Pageable pageable);

}