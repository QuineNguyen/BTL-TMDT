package com.example.gearshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gearshop.model.SanPhamRAM;

@Repository
public interface SanPhamRAMRepository extends JpaRepository<SanPhamRAM, Integer> {

        @Query("SELECT DISTINCT r.chuanRAM FROM SanPhamRAM r")
        List<String> findDistinctChuanRAM();

        @Query("SELECT DISTINCT r.dungLuong FROM SanPhamRAM r")
        List<String> findDistinctDungLuong();

        @Query("SELECT DISTINCT sp.thuongHieu.tenThuongHieu FROM SanPhamRAM cpu JOIN cpu.sanPham sp")
        List<String> findAllThuongHieu();

        @Query("SELECT r FROM SanPhamRAM r WHERE r.sanPham.id = :sanPhamID")
        SanPhamRAM findBySanPhamID(@Param("sanPhamID") Integer sanPhamID);

        @Query("SELECT r FROM SanPhamRAM r " +
                        "WHERE (:thuongHieu IS NULL OR r.sanPham.thuongHieu.tenThuongHieu = :thuongHieu) " +
                        "AND (:chuanRAM IS NULL OR r.chuanRAM = :chuanRAM) " +
                        "AND (:dungLuong IS NULL OR r.dungLuong = :dungLuong) " +
                        "AND (:giaMin IS NULL OR r.sanPham.gia >= :giaMin) " +
                        "AND (:giaMax IS NULL OR r.sanPham.gia <= :giaMax) " +
                        "ORDER BY " +
                        "CASE WHEN :sort = 'giaTangDan' THEN r.sanPham.gia END ASC, " +
                        "CASE WHEN :sort = 'giaGiamDan' THEN r.sanPham.gia END DESC")
        List<SanPhamRAM> locSanPham(@Param("thuongHieu") String thuongHieu,
                        @Param("chuanRAM") String chuanRAM,
                        @Param("dungLuong") String dungLuong,
                        @Param("giaMin") Long giaMin,
                        @Param("giaMax") Long giaMax,
                        @Param("sort") String sort);

        SanPhamRAM findBySanPham(com.example.gearshop.model.SanPham sp);

        void deleteBySanPham(com.example.gearshop.model.SanPham sp);

        @Query("SELECT MAX(r.maRAM) FROM SanPhamRAM r")
        String findMaxMaRAM();
}
