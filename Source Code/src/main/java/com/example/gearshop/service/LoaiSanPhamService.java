package com.example.gearshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.LoaiSanPham;
import com.example.gearshop.repository.LoaiSanPhamRepository;

@Service
public class LoaiSanPhamService {

    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;

    public List<LoaiSanPham> findAll() {
        return loaiSanPhamRepository.findAll();
    }

    public LoaiSanPham getByTenLoaiSanPham(String tenLoaiSanPham) {
        return loaiSanPhamRepository.findByTenLoaiSanPham(tenLoaiSanPham);
    }

    public LoaiSanPham getByIdLoaiSanPham(Integer maLoaiSanPham) {
        return loaiSanPhamRepository.findById(maLoaiSanPham);
    }
    // existing code
}