package com.example.gearshop.service;

import com.example.gearshop.model.GioHang;
import com.example.gearshop.model.GioHangChiTiet;
import com.example.gearshop.repository.GioHangChiTietRepository;
import com.example.gearshop.repository.GioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    public GioHang getCartByCustomerId(Integer customerId) {
        return gioHangRepository.findById(customerId).orElse(null);
    }

    public List<GioHangChiTiet> getCartDetailsByCartId(Integer cartId) {
        return gioHangChiTietRepository.findByGioHang_Id(cartId);
    }

    public GioHangChiTiet addCartItem(GioHangChiTiet cartItem) {
        return gioHangChiTietRepository.save(cartItem);
    }

    public void removeCartItem(Integer cartItemId) {
        gioHangChiTietRepository.deleteById(cartItemId);
    }

    public void clearCart(Integer cartId) {
        List<GioHangChiTiet> cartItems = gioHangChiTietRepository.findByGioHang_Id(cartId);
        gioHangChiTietRepository.deleteAll(cartItems);
    }
}