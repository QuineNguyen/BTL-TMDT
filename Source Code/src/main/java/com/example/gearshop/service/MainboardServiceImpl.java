package com.example.gearshop.service;

import com.example.gearshop.model.SanPhamMainBoard;
import com.example.gearshop.repository.SanPhamMainBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainboardServiceImpl implements MainboardService {

    @Autowired
    private SanPhamMainBoardRepository mainboardRepository;

    @Override
    public List<SanPhamMainBoard> findFiltered(String[] thuongHieu, String[] modelMain,
                                               String[] chipset, String[] socketMain,
                                               String[] kichThuoc, String[] soKheRAM,
                                               Integer minPrice, Integer maxPrice, String sort) {
        // Gọi xuống repository với Specification (lọc động)
        Specification<SanPhamMainBoard> spec = Specification.where(null);

        if (thuongHieu != null && thuongHieu.length > 0) {
            spec = spec.and((root, query, cb) -> root.get("sanPham").get("thuongHieu").get("tenThuongHieu")
                    .in((Object[]) thuongHieu));
        }

        if (modelMain != null && modelMain.length > 0) {
            spec = spec.and((root, query, cb) -> root.get("modelMain").in((Object[]) modelMain));
        }

        if (chipset != null && chipset.length > 0) {
            spec = spec.and((root, query, cb) -> root.get("chipset").in((Object[]) chipset));
        }

        if (socketMain != null && socketMain.length > 0) {
            spec = spec.and((root, query, cb) -> root.get("socketMain").in((Object[]) socketMain));
        }

        if (kichThuoc != null && kichThuoc.length > 0) {
            spec = spec.and((root, query, cb) -> root.get("kichThuoc").in((Object[]) kichThuoc));
        }

        if (soKheRAM != null && soKheRAM.length > 0) {
            List<Integer> soKheRAMList = Arrays.stream(soKheRAM)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            spec = spec.and((root, query, cb) -> root.get("soKheRAM").in(soKheRAMList));
        }

        if (minPrice != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("sanPham").get("gia"), minPrice));
        }

        if (maxPrice != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("sanPham").get("gia"), maxPrice));
        }

        // Sắp xếp
        Sort sortObj = Sort.unsorted();
        if ("priceAsc".equals(sort)) {
            sortObj = Sort.by(Sort.Direction.ASC, "sanPham.gia");
        } else if ("priceDesc".equals(sort)) {
            sortObj = Sort.by(Sort.Direction.DESC, "sanPham.gia");
        }

        return mainboardRepository.findAll(spec, sortObj);
    }

    @Override
    public SanPhamMainBoard findById(Integer id) {
        // Tìm sản phẩm Mainboard theo ID
        return mainboardRepository.findById(id).orElse(null);
    }
}
