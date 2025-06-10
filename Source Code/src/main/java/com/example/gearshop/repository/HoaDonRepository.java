package com.example.gearshop.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.gearshop.model.HoaDon;

import java.util.Optional;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    Optional<HoaDon> findTopByOrderByIdDesc();

    List<HoaDon> findByThongTinNhanHang_KhachHangID(Integer khachHangID);

    List<HoaDon> findAll();

    List<HoaDon> findAll(Sort sort);

    @Query("SELECT hd FROM HoaDon hd " +
            "JOIN ThongTinNhanHang ttnh ON hd.thongTinNhanHang.id = ttnh.id " +
            "JOIN KhachHang kh ON ttnh.khachHangID = kh.id " +
            "JOIN NguoiDung nd ON kh.nguoiDung.id = nd.id " +
            "WHERE nd.tenNguoiDung LIKE %:tenNguoiDung% " +
            "ORDER BY hd.ngayTao DESC")
    List<HoaDon> findByTenNguoiDungContainingIgnoreCase(@Param("tenNguoiDung") String tenNguoiDung);

    // Thống kê doanh thu theo ngày
    @Query("SELECT FUNCTION('DATE', hd.ngayTao), SUM(hd.tongGia) " +
            "FROM HoaDon hd " +
            "GROUP BY FUNCTION('DATE', hd.ngayTao) " +
            "ORDER BY FUNCTION('DATE', hd.ngayTao)")
    List<Object[]> doanhThuTheoNgay();

    // Thống kê doanh thu theo tháng
    @Query("SELECT FUNCTION('MONTH', hd.ngayTao), SUM(hd.tongGia) " +
            "FROM HoaDon hd " +
            "GROUP BY FUNCTION('MONTH', hd.ngayTao) " +
            "ORDER BY FUNCTION('MONTH', hd.ngayTao)")
    List<Object[]> doanhThuTheoThang();
}
