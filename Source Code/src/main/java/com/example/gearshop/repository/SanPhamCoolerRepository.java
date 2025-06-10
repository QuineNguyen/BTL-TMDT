package com.example.gearshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gearshop.model.SanPham;
import com.example.gearshop.model.SanPhamCooler;

@Repository
public interface SanPhamCoolerRepository extends JpaRepository<SanPhamCooler, Integer> {
        @Query("SELECT c FROM SanPhamCooler c WHERE " +
                        "(:thuongHieu IS NULL OR c.sanPham.thuongHieu.tenThuongHieu = :thuongHieu) " +
                        "AND (:loaiTan IS NULL OR c.loaiTan = :loaiTan) " +
                        "AND (:coLED IS NULL OR c.coLED = :coLED) " +
                        "AND (:giaMin IS NULL OR c.sanPham.gia >= :giaMin) " +
                        "AND (:giaMax IS NULL OR c.sanPham.gia <= :giaMax) " +
                        "ORDER BY " +
                        "CASE WHEN :sort = 'giaTangDan' THEN c.sanPham.gia END ASC, " +
                        "CASE WHEN :sort = 'giaGiamDan' THEN c.sanPham.gia END DESC")
        List<SanPhamCooler> filterCooler(
                        @Param("thuongHieu") String thuongHieu,
                        @Param("loaiTan") String loaiTan,
                        @Param("coLED") Boolean coLED,
                        @Param("giaMin") Long giaMin,
                        @Param("giaMax") Long giaMax,
                        @Param("sort") String sort);

        @Query("SELECT DISTINCT c.loaiTan FROM SanPhamCooler c")
        List<String> findAllLoaiTan();

        @Query("SELECT DISTINCT c.coLED FROM SanPhamCooler c")
        List<Boolean> findAllCoLED();

        @Query("SELECT DISTINCT sp.thuongHieu.tenThuongHieu FROM SanPhamCooler c JOIN c.sanPham sp")
        List<String> findAllThuongHieu();

        @Query("SELECT c FROM SanPhamCooler c WHERE c.sanPham.id = :sanPhamID")
        SanPhamCooler findBySanPhamID(@Param("sanPhamID") Integer sanPhamID);

        SanPhamCooler findBySanPham(SanPham sp);

        void deleteBySanPham(SanPham sp);

        @Query("SELECT MAX(c.maCooler) FROM SanPhamCooler c")
        String findMaxMaCooler();
}
