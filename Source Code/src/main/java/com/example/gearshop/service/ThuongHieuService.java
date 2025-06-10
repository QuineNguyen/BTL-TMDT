package com.example.gearshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.ThuongHieu;
import com.example.gearshop.repository.ThuongHieuRepository;

@Service
public class ThuongHieuService {

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    public List<ThuongHieu> findAll() {
        return thuongHieuRepository.findAll();
    }

}
