package com.example.gearshop.service;

import com.example.gearshop.repository.VoucherKhachHangRepository;
import com.example.gearshop.repository.VoucherRepository;

import jakarta.transaction.Transactional;

import com.example.gearshop.model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private VoucherKhachHangRepository voucherKhachHangRepository;

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    public Voucher getVoucherByMaVoucher(String maVoucher) {
        return voucherRepository.findByMaVoucher(maVoucher)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy voucher với mã: " + maVoucher));
    }

    @Transactional
    public void xoaVoucherVaLienQuan(Integer id) {
        // 1. Xoá VoucherKhachHang trước
        voucherKhachHangRepository.deleteByVoucherID(id);

        // 2. Xoá Voucher
        voucherRepository.deleteById(id);
    }
}
