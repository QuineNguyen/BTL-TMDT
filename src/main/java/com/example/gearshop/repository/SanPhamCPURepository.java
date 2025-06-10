package com.example.gearshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gearshop.model.SanPham;
import com.example.gearshop.model.SanPhamCPU;

public interface SanPhamCPURepository extends JpaRepository<SanPhamCPU, Integer> {

        @Query("SELECT c FROM SanPhamCPU c WHERE "
                        + "(:loaiCPU IS NULL OR c.loaiCPU = :loaiCPU) AND "
                        + "(:soNhanSoLuong IS NULL OR c.soNhansoLuong = :soNhanSoLuong) AND "
                        + "(:giaMin IS NULL OR c.sanPham.gia >= :giaMin) AND "
                        + "(:giaMax IS NULL OR c.sanPham.gia <= :giaMax) "
                        + "ORDER BY "
                        + "CASE WHEN :sort = 'giaAsc' THEN c.sanPham.gia END ASC, "
                        + "CASE WHEN :sort = 'giaDesc' THEN c.sanPham.gia END DESC")
        List<SanPhamCPU> filterCPUs(@Param("loaiCPU") String loaiCPU,
                        @Param("soNhanSoLuong") String soNhanSoLuong,
                        @Param("giaMin") Long giaMin,
                        @Param("giaMax") Long giaMax,
                        @Param("sort") String sort);

        @Query("SELECT DISTINCT c.loaiCPU FROM SanPhamCPU c")
        List<String> findAllLoaiCPU();

        @Query("SELECT DISTINCT c.soNhansoLuong FROM SanPhamCPU c")
        List<String> findAllSoNhanSoLuong();

        @Query("SELECT DISTINCT sp.thuongHieu.tenThuongHieu FROM SanPhamCPU cpu JOIN cpu.sanPham sp")
        List<String> findAllThuongHieu();

        @Query("SELECT c FROM SanPhamCPU c WHERE c.sanPham.id = :sanPhamID")
        SanPhamCPU findBySanPhamID(@Param("sanPhamID") Integer sanPhamID);

        SanPhamCPU findBySanPham(SanPham sp);

        void deleteBySanPham(SanPham sp);

        @Query("SELECT MAX(c.maCPU) FROM SanPhamCPU c")
        String findMaxMaCPU();
}
