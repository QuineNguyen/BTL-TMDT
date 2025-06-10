package com.example.gearshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gearshop.model.VoucherKhachHang;

import jakarta.transaction.Transactional;

public interface VoucherKhachHangRepository extends JpaRepository<VoucherKhachHang, Integer> {

    List<VoucherKhachHang> findByVoucherId(Integer voucherId);

    @Modifying
    @Transactional
    @Query("DELETE FROM VoucherKhachHang vkh WHERE vkh.voucher.id = :voucherID")
    void deleteByVoucherID(@Param("voucherID") Integer voucherID);
}
