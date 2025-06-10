package com.example.gearshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gearshop.model.SanPhamPSU;

@Repository
public interface SanPhamPSURepository extends JpaRepository<SanPhamPSU, Integer> {
        // Custom query methods can be defined here if needed
        @Query("SELECT p FROM SanPhamPSU p WHERE "
                        + "(:thuongHieu IS NULL OR p.sanPham.thuongHieu.tenThuongHieu = :thuongHieu) "
                        + "AND (:dienApVao IS NULL OR p.dienApVao = :dienApVao) "
                        + "AND (:congSuat IS NULL OR p.congSuat = :congSuat) "
                        + "AND (:giaMin IS NULL OR p.sanPham.gia >= :giaMin) "
                        + "AND (:giaMax IS NULL OR p.sanPham.gia <= :giaMax) "
                        + "ORDER BY "
                        + "CASE WHEN :sort = 'giaAsc' THEN p.sanPham.gia END ASC, "
                        + "CASE WHEN :sort = 'giaDesc' THEN p.sanPham.gia END DESC")
        List<SanPhamPSU> filterPSUs(String thuongHieu, Integer dienApVao, Integer congSuat,
                        Long giaMin, Long giaMax, String sort);

        @Query("SELECT DISTINCT p.dienApVao FROM SanPhamPSU p")
        List<Integer> findAllDienApVao();

        @Query("SELECT DISTINCT p.congSuat FROM SanPhamPSU p")
        List<Integer> findAllCongSuat();

        @Query("SELECT DISTINCT p.sanPham.thuongHieu.tenThuongHieu FROM SanPhamPSU p")
        List<String> findAllThuongHieu();

        @Query("SELECT p FROM SanPhamPSU p WHERE p.sanPham.id = :sanPhamID")
        SanPhamPSU findBySanPhamID(@Param("sanPhamID") Integer sanPhamID);

        SanPhamPSU findBySanPham(com.example.gearshop.model.SanPham sp);

        void deleteBySanPham(com.example.gearshop.model.SanPham sp);

        @Query("SELECT MAX(p.maPSU) FROM SanPhamPSU p")
        String findMaxMaPSU();
}
