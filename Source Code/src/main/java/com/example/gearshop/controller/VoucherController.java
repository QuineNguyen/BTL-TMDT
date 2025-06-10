package com.example.gearshop.controller;

import com.example.gearshop.model.Voucher;
import com.example.gearshop.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/{maVoucher}")
    public Voucher getVoucherByMaVoucher(@PathVariable String maVoucher) {
        return voucherService.getVoucherByMaVoucher(maVoucher);
    }
}
