package com.example.gearshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gearshop.model.SanPham;
import com.example.gearshop.model.SanPhamCase;

@Repository
public interface SanPhamCaseRepository extends JpaRepository<SanPhamCase, Integer> {

        @Query("SELECT c FROM SanPhamCase c WHERE "
                        + "(:thuongHieu IS NULL OR c.sanPham.thuongHieu.tenThuongHieu = :thuongHieu) "
                        + "AND (:hoTroMain IS NULL OR c.hoTroMain = :hoTroMain) "
                        + "AND (:mauCase IS NULL OR c.mauCase = :mauCase) "
                        + "AND (:giaMin IS NULL OR c.sanPham.gia >= :giaMin) "
                        + "AND (:giaMax IS NULL OR c.sanPham.gia <= :giaMax) "
                        + "ORDER BY "
                        + "CASE WHEN :sort = 'giaAsc' THEN c.sanPham.gia END ASC, "
                        + "CASE WHEN :sort = 'giaDesc' THEN c.sanPham.gia END DESC")
        List<SanPhamCase> filterCases(String thuongHieu, String hoTroMain, String mauCase,
                        Long giaMin, Long giaMax, String sort);

        @Query("SELECT DISTINCT c.hoTroMain FROM SanPhamCase c")
        List<String> findAllHoTroMain();

        @Query("SELECT DISTINCT c.mauCase FROM SanPhamCase c")
        List<String> findAllMauCase();

        @Query("SELECT DISTINCT c.sanPham.thuongHieu.tenThuongHieu FROM SanPhamCase c")
        List<String> findAllThuongHieu();

        @Query("SELECT c FROM SanPhamCase c WHERE c.sanPham.id = :sanPhamID")
        SanPhamCase findBySanPhamID(@Param("sanPhamID") Integer sanPhamID);

        SanPhamCase findBySanPham(SanPham sp);

        void deleteBySanPham(SanPham sp);

        @Query("SELECT MAX(c.maCase) FROM SanPhamCase c")
        String findMaxMaCase();
}