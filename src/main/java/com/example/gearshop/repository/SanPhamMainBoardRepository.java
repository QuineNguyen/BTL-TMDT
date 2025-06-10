package com.example.gearshop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.gearshop.model.SanPham;
import com.example.gearshop.model.SanPhamMainBoard;

import java.util.List;

@Repository
public interface SanPhamMainBoardRepository
                extends JpaRepository<SanPhamMainBoard, Integer>, JpaSpecificationExecutor<SanPhamMainBoard> {
        // Custom query methods can be defined here if needed
        // For example, you can add methods to find MainBoards by specific criteria
        @Query("SELECT m FROM SanPhamMainBoard m " +
                        "JOIN FETCH m.sanPham sp " +
                        "JOIN FETCH sp.thuongHieu th " +
                        "WHERE (:thuongHieu IS NULL OR th.tenThuongHieu = :thuongHieu) " +
                        "AND (:modelMain IS NULL OR m.modelMain = :modelMain) " +
                        "AND (:chipset IS NULL OR m.chipset = :chipset) " +
                        "AND (:socketMain IS NULL OR m.socketMain = :socketMain) " +
                        "AND (:kichThuoc IS NULL OR m.kichThuoc = :kichThuoc) " +
                        "AND (:soKheRAM IS NULL OR m.soKheRAM = :soKheRAM)")
        List<SanPhamMainBoard> locTheoTieuChi(
                        @Param("thuongHieu") String thuongHieu,
                        @Param("modelMain") String modelMain,
                        @Param("chipset") String chipset,
                        @Param("socketMain") String socketMain,
                        @Param("kichThuoc") String kichThuoc,
                        @Param("soKheRAM") Integer soKheRAM);

        @Query("SELECT DISTINCT th.tenThuongHieu FROM ThuongHieu th " +
                        "JOIN SanPham sp ON th.id = sp.thuongHieu.id " +
                        "JOIN SanPhamMainBoard m ON m.sanPham.id = sp.id")
        List<String> findDistinctThuongHieu();

        @Query("SELECT DISTINCT m.modelMain FROM SanPhamMainBoard m")
        List<String> findDistinctModelMain();

        @Query("SELECT DISTINCT m.chipset FROM SanPhamMainBoard m")
        List<String> findDistinctChipset();

        @Query("SELECT DISTINCT m.socketMain FROM SanPhamMainBoard m")
        List<String> findDistinctSocketMain();

        @Query("SELECT DISTINCT m.kichThuoc FROM SanPhamMainBoard m")
        List<String> findDistinctKichThuoc();

        @Query("SELECT DISTINCT m.soKheRAM FROM SanPhamMainBoard m")
        List<Integer> findDistinctSoKheRAM();

        List<SanPhamMainBoard> findAll();

        @Query("SELECT m FROM SanPhamMainBoard m WHERE m.sanPham.id = :sanPhamId")
        SanPhamMainBoard findBySanPhamID(Integer sanPhamId);

        SanPhamMainBoard findBySanPham(SanPham sanPham);

        void deleteBySanPham(SanPham sanPham);

        @Query("SELECT MAX(m.maMainBoard) FROM SanPhamMainBoard m")
        String findMaxMaMainBoard();
}
