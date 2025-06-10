package com.example.gearshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.SanPhamMainBoard;
import com.example.gearshop.repository.SanPhamMainBoardRepository;

@Service
public class SanPhamMainBoardService {

    @Autowired
    private SanPhamMainBoardRepository sanPhamMainBoardRepository;

    public String taoMaMainBboardMoi() {
        String maSanPhamCuoi = sanPhamMainBoardRepository.findMaxMaMainBoard();
        if (maSanPhamCuoi == null) {
            return "MB0001"; // Mã đầu tiên nếu không có sản phẩm nào
        }
        int so = Integer.parseInt(maSanPhamCuoi.substring(2)); // Lấy phần số sau "MB"
        so++;
        return String.format("MB%05d", so); // Tạo mã mới với định dạng MBxxxx
    }

    public SanPhamMainBoard luuSanPhamMainBoard(SanPhamMainBoard sanPhamMainBoard) {
        return sanPhamMainBoardRepository.save(sanPhamMainBoard);
    }
}
