package com.example.gearshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gearshop.model.SanPhamOCung;

@Repository
public interface SanPhamOCungRepository extends JpaRepository<SanPhamOCung, Integer> {

        @Query("SELECT o FROM SanPhamOCung o WHERE " +
                        "(:thuongHieu IS NULL OR o.sanPham.thuongHieu.tenThuongHieu = :thuongHieu) " +
                        "AND (:loaiOCung IS NULL OR o.loaiOCung = :loaiOCung) " +
                        "AND (:dungLuong IS NULL OR o.dungLuong = :dungLuong) " +
                        "AND (:giaMin IS NULL OR o.sanPham.gia >= :giaMin) " +
                        "AND (:giaMax IS NULL OR o.sanPham.gia <= :giaMax) " +
                        "ORDER BY " +
                        "CASE WHEN :sort = 'giaTangDan' THEN o.sanPham.gia END ASC, " +
                        "CASE WHEN :sort = 'giaGiamDan' THEN o.sanPham.gia END DESC")
        List<SanPhamOCung> filterOCung(
                        @Param("thuongHieu") String thuongHieu,
                        @Param("loaiOCung") String loaiOCung,
                        @Param("dungLuong") String dungLuong,
                        @Param("giaMin") Long giaMin,
                        @Param("giaMax") Long giaMax,
                        @Param("sort") String sort);

        // Các hàm khác như đã trình bày ở trên
        @Query("SELECT DISTINCT o.loaiOCung FROM SanPhamOCung o")
        List<String> findAllLoaiOCung();

        @Query("SELECT DISTINCT o.dungLuong FROM SanPhamOCung o")
        List<String> findAllDungLuong();

        @Query("SELECT DISTINCT sp.thuongHieu.tenThuongHieu FROM SanPhamOCung o JOIN o.sanPham sp")
        List<String> findAllThuongHieu();

        @Query("SELECT o FROM SanPhamOCung o WHERE o.sanPham.id = :sanPhamID")
        SanPhamOCung findBySanPhamID(@Param("sanPhamID") Integer sanPhamID);

        SanPhamOCung findBySanPham(com.example.gearshop.model.SanPham sp);

        void deleteBySanPham(com.example.gearshop.model.SanPham sp);

        @Query("SELECT MAX(o.maOCung) FROM SanPhamOCung o")
        String findMaxMaOCung();
}
