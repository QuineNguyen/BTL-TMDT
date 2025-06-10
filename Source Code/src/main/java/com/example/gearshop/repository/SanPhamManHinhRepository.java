package com.example.gearshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gearshop.model.SanPhamManHinh;

@Repository
public interface SanPhamManHinhRepository extends JpaRepository<SanPhamManHinh, Integer> {

        @Query("SELECT m FROM SanPhamManHinh m WHERE "
                        + "(:thuongHieu IS NULL OR m.sanPham.thuongHieu.tenThuongHieu = :thuongHieu) "
                        + "AND (:kichThuoc IS NULL OR m.kichThuoc = :kichThuoc) "
                        + "AND (:beMat IS NULL OR m.beMat = :beMat) "
                        + "AND (:tanSoQuet IS NULL OR m.tanSoQuet = :tanSoQuet) "
                        + "AND (:tamNen IS NULL OR m.tamNen = :tamNen) "
                        + "AND (:doPhanGiai IS NULL OR m.doPhanGiai = :doPhanGiai) "
                        + "AND (:giaMin IS NULL OR m.sanPham.gia >= :giaMin) "
                        + "AND (:giaMax IS NULL OR m.sanPham.gia <= :giaMax) "
                        + "ORDER BY "
                        + "CASE WHEN :sort = 'giaTangDan' THEN m.sanPham.gia END ASC, "
                        + "CASE WHEN :sort = 'giaGiamDan' THEN m.sanPham.gia END DESC")
        List<SanPhamManHinh> filterManHinh(
                        @Param("thuongHieu") String thuongHieu,
                        @Param("kichThuoc") Integer kichThuoc,
                        @Param("beMat") String beMat,
                        @Param("tanSoQuet") Integer tanSoQuet,
                        @Param("tamNen") String tamNen,
                        @Param("doPhanGiai") String doPhanGiai,
                        @Param("giaMin") Long giaMin,
                        @Param("giaMax") Long giaMax,
                        @Param("sort") String sort);

        @Query("SELECT DISTINCT m.kichThuoc FROM SanPhamManHinh m")
        List<Integer> findAllKichThuoc();

        @Query("SELECT DISTINCT m.beMat FROM SanPhamManHinh m")
        List<String> findAllBeMat();

        @Query("SELECT DISTINCT m.tanSoQuet FROM SanPhamManHinh m")
        List<Integer> findAllTanSoQuet();

        @Query("SELECT DISTINCT m.tamNen FROM SanPhamManHinh m")
        List<String> findAllTamNen();

        @Query("SELECT DISTINCT m.doPhanGiai FROM SanPhamManHinh m")
        List<String> findAllDoPhanGiai();

        @Query("SELECT DISTINCT m.sanPham.thuongHieu.tenThuongHieu FROM SanPhamManHinh m")
        List<String> findAllThuongHieu();

        @Query("SELECT m FROM SanPhamManHinh m WHERE m.sanPham.id = :sanPhamID")
        SanPhamManHinh findBySanPhamID(@Param("sanPhamID") Integer sanPhamID);

        SanPhamManHinh findBySanPham(com.example.gearshop.model.SanPham sp);

        void deleteBySanPham(com.example.gearshop.model.SanPham sp);

        @Query("SELECT MAX(m.maMH) FROM SanPhamManHinh m")
        String findMaxMaManHinh();
}
