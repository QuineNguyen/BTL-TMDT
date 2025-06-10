package com.example.gearshop.interceptor;

import com.example.gearshop.model.NguoiDung;
import com.example.gearshop.repository.NhanVienRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private NhanVienRepository nhanVienRepo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/");
            return false;
        }

        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null) {
            response.sendRedirect("/");
            return false;
        }

        boolean isNhanVien = nhanVienRepo.findByNguoiDung_Id(nguoiDung.getId()) != null;
        if (!isNhanVien) {
            response.sendRedirect("/");
            return false;
        }

        return true; // Cho phép tiếp tục xử lý yêu cầu
    }
}