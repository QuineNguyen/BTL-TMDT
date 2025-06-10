package com.example.gearshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gearshop.model.ThongBao;
import com.example.gearshop.model.TrangThaiThongBao;
import com.example.gearshop.repository.ThongBaoRepository;

@Service
public class ThongBaoService {

    @Autowired
    private ThongBaoRepository thongBaoRepository;

    public List<ThongBao> layThongBaoTheoKhachHang(Integer khachHangId) {
        return thongBaoRepository.findByKhachHangIdOrderByNgayThongBaoDesc(khachHangId);
    }

    public long demSoThongBaoChuaDoc(Integer khachHangId) {
        return thongBaoRepository.countByKhachHangIdAndTrangThaiThongBao(khachHangId, TrangThaiThongBao.CHUA_DOC);
    }

    public void danhDauTatCaDaDoc(Integer khachHangId) {
        List<ThongBao> thongBaos = thongBaoRepository.findByKhachHangIdOrderByNgayThongBaoDesc(khachHangId);
        for (ThongBao tb : thongBaos) {
            if (tb.getTrangThaiThongBao() == TrangThaiThongBao.CHUA_DOC) {
                tb.setTrangThaiThongBao(TrangThaiThongBao.DA_DOC);
            }
        }
        thongBaoRepository.saveAll(thongBaos);
    }

}
