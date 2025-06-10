-- Import dl nguoidung

insert into nguoidung(maNguoiDung, tenNguoiDung, tenDangNhap, matKhau, email, sdt, diaChi)
values
(N'ND0001', N'Nguyễn Văn A', N'anv1999', N'matkhau1', N'anv@gmail.com', N'0965195768', N'Ngõ 1 đường ABCD, phường XYZ, quận Hà Đông, Hà Nội'),
(N'ND0002', N'Trần Văn B', N'btv2003', N'matkhau2', N'btv@gmail.com', N'0902192008', N'Ngõ 2, phường TB, quận LB, Hà Nội'),
(N'ND0003', N'Hoàng Minh C', N'hmc1999', N'matkhau3', N'chm@gmail.com', N'08956253208', N'Nhà số 15, thôn AA, xã BB, huyện CC, Hà Nội'),
(N'ND0004', N'Bùi Thị D', N'btd1969', N'matkhau4', N'dbt@gmail.com', N'0863123756', N'Gốc cây phượng, thôn AX, xã AY, huyện AZ, Hà Nội'),
(N'ND0005', N'Nguyễn Thị E', N'nte1998', N'matkhau5', N'ent@gmail.com', N'0368372273', N'Ngõ 92, đường BG, phượng NV, quận NTL, Hà Nội'),
(N'ND0006', N'Đào Việt F', N'fdv213', N'matkhau6', N'fdv@gmail.com', N'0902132776', N'Nhà số 9, đường LBQ, phường TH, quận TH, Hà Nội'),
(N'ND0007', N'Đinh Văn G', N'gdv19009', N'matkhau7', N'gdv@gmail.com', N'0812903454', N'Nhà số 2, thôn CV, xã DT, huyện DA, Hà Nội'),
(N'ND0008', N'Cam Hải Đăng', N'dangch2003', N'admin', N'dangch@gmail.com', N'0902132776', N'Nhà số XX, thôn YY, xã ZZ, huyện WW, Hà Nội'),
(N'ND0009', N'Nguyễn Bá Hoàng Huynh', N'huynhnbh', N'admin', N'huynhnbh@gmail.com', N'0812903454', N'Nhà số 2, thôn CV, xã DT, huyện DA, Hà Nội'),
(N'ND0010', N'Vũ Tuyết H', N'lmao', N'matkhau8', N'hvt@gmail.com', N'0892725068', N'Nhà văn hoá thôn XB, xã CD, huyện GL, Hà Nội');

-- Import dl nhanvien
insert into nhanvien(maNhanVien, nguoiDungID, ghiChu) values
(N'NV001', 8, 'Quan ly'),
(N'NV002', 9, 'Quan ly');

-- import dl khachhang
insert into khachhang(maKhachHang, nguoiDungID) values
(N'KH0001', 1),
(N'KH0002', 2),
(N'KH0003', 3),
(N'KH0004', 4),
(N'KH0005', 5),
(N'KH0006', 6),
(N'KH0007', 7),
(N'KH0008', 10);

-- import dl hoadon
insert into thongTinNhanHang(khachHangID, tenNguoiNhan, sdt, diachi) values
(1, N'Nguyễn Văn A', '0123456789', N'Mỗ Lao, Hà Đông, Hà Nội');

insert into hoadon(maHoaDon, thongTinNhanHangID, ngayTao, tongGia, trangThaiDonHang) values
(N'HD0001', 1, '2025-04-11', 2000000, N'Đã thanh toán');

insert into loaiSanPham(maLoaiSP, tenLoaiSanPham) values
(N'LSP01', N'Mainboard'),
(N'LSP02', N'CPU'),
(N'LSP03', N'RAM'),
(N'LSP04', N'VGA'),
(N'LSP05', N'Ổ cứng'),
(N'LSP06', N'PSU'),
(N'LSP07', N'Cooler'),
(N'LSP08', N'Case'),
(N'LSP09', N'Màn hình');

-- import thuong hieu

insert into thuonghieu(maThuongHieu, tenThuongHieu) values
(N'TH001', N'MSI'),
(N'TH002', N'ASROCK'),
(N'TH003', N'ASUS'),
(N'TH004', N'CORSAIR'),
(N'TH005', N'GIGABYTE'),
(N'TH006', N'BIOSTAR'),
(N'TH007', N'AMD'),
(N'TH008', N'INTEL'),
(N'TH009', N'COLORFUL'),
(N'TH010', N'KINGSTON'),
(N'TH011', N'ADATA'),
(N'TH012', N'VSP'),
(N'TH013', N'Cooler Master'),
(N'TH014', N'Seagate');

-- import sanpham

insert into sanpham(maSanPham, tenSanPham, hinhAnh, nguoiThemID, loaiSPID, thuongHieuID, ngayThem, gia, tonkho)
values
(N'SP00001', N'Mainboard MSI Z790 GAMING WIFI', N'mainboard1.jpg', 1, 1, 1, '2025-04-12', 6360000, 4),
(N'SP00002', N'Mainboard ASROCK B550M STEEL LEGEND', N'mainboard2.jpg', 1, 1, 2, '2025-04-12', 3600000, 4),
(N'SP00003', N'Mainboard ASROCK H510M-ITX', N'mainboard3.jpg', 1, 1, 2, '2025-04-12', 2200000, 4),
(N'SP00004', N'Mainboard ASUS PRIME H510M-K', N'mainboard4.jpg', 1, 1, 3, '2025-04-12', 1800000, 4),
(N'SP00005', N'Mainboard ASUS ROG MAXIMUS XII HERO', N'mainboard5.jpg', 1, 1, 3, '2025-04-12', 9900000, 4),
(N'SP00006', N'Mainboard ASUS TUF GAMING X570 PLUS', N'mainboard6.jpg', 1, 1, 3, '2025-04-12', 6500000, 4),
(N'SP00007', N'Mainboard MSI X470 Gaming Pro Carbon', N'mainboard7.jpg', 1, 1, 1, '2025-04-12', 5000000, 4),
(N'SP00008', N'Mainboard MSI X470 GAMING PRO', N'mainboard8.jpg', 1, 1, 1, '2025-04-12', 4300000, 5),

(N'SP00009', N'CPU Intel Core i5 10400F TRAY (2.90 up to 4.30GHz)', N'cpu1.jpg', 1, 2, 8, '2025-04-12', 2220000, 6),
(N'SP00010', N'CPU Intel Core i5 11400 6 nhân 12 luồng Turbo 4.4GHz', N'cpu2.jpg', 1, 2, 8, '2025-04-12', 3100000, 6),
(N'SP00011', N'CPU AMD Ryzen 5 5600X 3.7 up to 4.6GHz', N'cpu3.jpg', 1, 2, 7, '2025-04-12', 4230000, 6),
(N'SP00012', N'CPU Intel Core i3 10105F Tray 4 nhân 8 luồng', N'cpu4.jpg', 1, 2, 8, '2025-04-12', 1540000, 5),
(N'SP00013', N'CPU Intel Core i5 12400 Tray không Fan', N'cpu5.jpg', 1, 2, 8, '2025-04-12', 3500000, 6),
(N'SP00014', N'CPU Intel Core i7 13700 TRAY up to 5.2GHz', N'cpu6.jpg', 1, 2, 8, '2025-04-12', 9540000, 5),
(N'SP00015', N'CPU Intel Core i5 11400F TRAY 2.6 up to 4.4GHz', N'cpu7.jpg', 1, 2, 8, '2025-04-12', 1920000, 7),
(N'SP00016', N'CPU Intel Core i9 12900K 16 nhân 24 luồng up to 5.20GHz', N'cpu8.jpg', 1, 2, 8, '2025-04-12', 8790000, 4),
(N'SP00017', N'CPU AMD Ryzen 5 5500 6 nhân 12 luồng', N'cpu9.jpg', 1, 2, 7, '2025-04-12', 2700000, 5),
(N'SP00018', N'CPU AMD Ryzen 7 9800X3D 4.7GHz boost 5.20GHz', N'cpu10.jpg', 1, 2, 7, '2025-04-12', 14800000, 6),
(N'SP00019', N'CPU Intel Core i5 13600KF up to 5.1GHz', N'cpu11.jpg', 1, 2, 8, '2025-04-12', 6700000, 3),
(N'SP00020', N'CPU Intel Core i7 13700KF 16 nhân 24 luồng', N'cpu12.jpg', 1, 2, 8, '2025-04-12', 8320000, 2),

(N'SP00021', N'RAM Desktop CORSAIR Vengeance LPX', N'ram1.jpg', 1, 3, 4, '2025-04-12', 610000, 12),
(N'SP00022', N'RAM CORSAIR Dominator RGB 16GB DDR4', N'ram2.jpg', 1, 3, 4, '2025-04-12', 2510000, 11),
(N'SP00023', N'RAM CORSAIR Dominator Platinum White RGB 32GB', N'ram3.jpg', 1, 3, 4, '2025-04-12', 3210000, 9),
(N'SP00024', N'RAM CORSAIR Vengeance LPX 8GB 3200MHz', N'ram4.jpg', 1, 3, 4, '2025-04-12', 490000, 2),
(N'SP00025', N'RAM PC CORSAIR Vengeance LPX', N'ram5.jpg', 1, 3, 4, '2025-04-12', 610000, 8),
(N'SP00026', N'RAM ADATA LANCER DDR5 64GB (32GB x2)', N'ram6.jpg', 1, 3, 11, '2025-04-12', 6080000, 10),
(N'SP00027', N'RAM ADATA XPG D50 DDR4 8GB', N'ram7.jpg', 1, 3, 11, '2025-04-12', 640000, 6),
(N'SP00028', N'RAM Desktop ADATA XPG Gammix D10 8Gb', N'ram8.jpg', 1, 3, 11, '2025-04-12', 420000, 7),
(N'SP00029', N'RAM ADATA XPG SPECTRIX D50 16GB', N'ram9.jpg', 1, 3, 11, '2025-04-12', 1010000, 14),
(N'SP00030', N'RAM ADATA Xtreme 3200 C16 DDR4', N'ram10.jpg', 1, 3, 11, '2025-04-12', 2150000, 1),

(N'SP00031', N'VGA MSI GeForce RTX 5070Ti 16G GAMING TRIO OC', N'vga1.jpg', 1, 4, 1, '2025-04-12', 31320000, 3),
(N'SP00032', N'VGA Colorful iGame GeForce RTX 5060Ti Ultra', N'vga2.jpg', 1, 4, 9, '2025-04-12', 15490000, 4),
(N'SP00033', N'VGA Asrock RX 9060 XT Steel Legend 16GB OC', N'vga3.jpg', 1, 4, 2, '2025-04-12', 28900000, 10),
(N'SP00034', N'VGA ASUS PH GT1030-O2G 2GB DDR5', N'vga4.jpg', 1, 4, 3, '2025-04-12', 2240000, 3),
(N'SP00035', N'VGA ASUS Prime GeForce RTX 5070 12GB GDDR7', N'vga5.jpg', 1, 4, 3, '2025-04-12', 20999000, 7),
(N'SP00036', N'VGA ASUS TUF Gaming GeForce RTX 5070 OC Edition 12GB', N'vga6.jpg', 1, 4, 3, '2025-04-12', 26890000, 8),
(N'SP00037', N'VGA ASUS PH GTX 1650 Super O4G', N'vga7.jpg', 1, 4, 3, '2025-04-12', 3499000, 9),
(N'SP00038', N'VGA ASUS DUAL RX 6500 XT-O4G', N'vga8.jpg', 1, 4, 3, '2025-04-12', 4012000, 12),
(N'SP00039', N'VGA GIGABYTE GeForce RTX 3060 WINDFORCE OC 12GB', N'vga9.jpg', 1, 4, 5, '2025-04-12', 8190000, 4),
(N'SP00040', N'VGA GIGABYTE GeForce RTX 3060Ti EAGLE 8GB', N'vga10.jpg', 1, 4, 5, '2025-04-12', 11230000, 6),
(N'SP00041', N'VGA GIGABYTE GeForce RTX 4060Ti EAGLE 8Gb', N'vga11.jpg', 1, 4, 5, '2025-04-12', 12560000, 7),
(N'SP00042', N'VGA GIGABYTE Radeon RX 7700 XT GAMING OC 12G', N'vga12.jpg', 1, 4, 5, '2025-04-12', 14780000, 8),

(N'SP00043', N'Ổ cứng SSD Kingston A400 120GB 2.5 inch SATA3', N'ocung1.jpg', 1, 5, 10, '2025-04-12', 612000, 8),
(N'SP00044', N'Ổ cứng HDD Seagate Barracuda 8TB 3.5inch', N'ocung2.jpg', 1, 5, 14, '2025-04-12', 6230000, 2),
(N'SP00045', N'Ổ cứng HDD Seagate Ironworf 12TB', N'ocung3.jpg', 1, 5, 14, '2025-04-12', 12899000, 12),
(N'SP00046', N'Ổ cứng HDD Seagate Ironworf Pro 6TB 3.5inch', N'ocung4.jpg', 1, 5, 14, '2025-04-12', 6710000, 10),
(N'SP00047', N'Ổ cứng SSD GIGABYTE 512GB M.2 2280 PCIe NVMe Gen3x4', N'ocung5.jpg', 1, 5, 5, '2025-04-12', 1120000, 9),
(N'SP00048', N'Ổ cứng SSD Kingston A2000M8 500GB', N'ocung6.jpg', 1, 5, 10, '2025-04-12', 1720000, 7),
(N'SP00049', N'Ổ cứng SSD Kingston KC600 256GB 2.5 inch', N'ocung7.jpg', 1, 5, 10, '2025-04-12', 810000, 8),
(N'SP00050', N'Ổ cứng SSD Kingston SKC600 512GB 2.5 inch SATA3', N'ocung8.jpg', 1, 5, 10, '2025-04-12', 1540000, 14);

insert into sanpham(maSanPham, tenSanPham, hinhAnh, nguoiThemID, loaiSPID, thuongHieuID, ngayThem, gia, tonkho)
values
(N'SP00051', N'Nguồn ASUS TUF GAMING 1000W GOLD ATX3.1', N'psu1.jpg', 1, 6, 3, '2025-04-12', 4600000, 5),
(N'SP00052', N'Nguồn ASUS TUF GAMING 650W Bronze', N'psu2.jpg', 1, 6, 3, '2025-04-12', 1449000, 8),
(N'SP00053', N'Nguồn ASUS TUF GAMING 750W BRONZE', N'psu3.jpg', 1, 6, 3, '2025-04-12', 1845000, 6),
(N'SP00054', N'Nguồn ASUS ROG STRIX 1000P 1000W', N'psu4.jpg', 1, 6, 3, '2025-04-12', 7099000, 7),
(N'SP00055', N'Nguồn MSI A1000G 1000W', N'psu5.jpg', 1, 6, 1, '2025-04-12', 4399000, 9),
(N'SP00056', N'Nguồn MSI MAG A750GL PCIE 5.0 750W', N'psu6.jpg', 1, 6, 1, '2025-04-12', 2760000, 4),
(N'SP00057', N'Nguồn CORSAIR RM850e ATX3.1', N'psu7.jpg', 1, 6, 4, '2025-04-12', 3290000, 4),
(N'SP00058', N'Nguồn CORSAIR RM750e ATX3.1', N'psu8.jpg', 1, 6, 4, '2025-04-12', 2870000, 3),
(N'SP00059', N'Nguồn CORSAIR HX1200I 2023', N'psu9.jpg', 1, 6, 4, '2025-04-12', 7890000, 2),

(N'SP00060', N'Tản nhiệt nước Cooler Master MASTERLIQUID ML280', N'cooler1.jpg', 1, 7, 13, '2025-04-12', 2100000, 5),
(N'SP00061', N'Tản nhiệt nước Cooler Master ML240L ARGB V2', N'cooler2.jpg', 1, 7, 13, '2025-04-12', 1490000, 7),
(N'SP00062', N'Tản nhiệt khí Cooler Master HYPER 212 ARGB TURBO', N'cooler3.jpg', 1, 7, 13, '2025-04-12', 952000, 4),
(N'SP00063', N'Tản nhiệt khí Cooler Master MasterAir MA612', N'cooler4.jpg', 1, 7, 13, '2025-04-12', 1780000, 5),
(N'SP00064', N'Tản nhiệt nước CORSAIR Hydro Series H100i RGB', N'cooler5.jpg', 1, 7, 4, '2025-04-12', 4203000, 6),
(N'SP00065', N'Tản nhiệt nước CORSAIR H60i CW', N'cooler6.jpg', 1, 7, 4, '2025-04-12', 3340000, 9),

(N'SP00066', N'Case VSP E-ROG ES1 Trắng', N'case1.jpg', 1, 8, 12, '2025-04-12', 912000, 5),
(N'SP00067', N'Case VSP Aquanaut Pro Gaming X2 Galaxy White', N'case2.jpg', 1, 8, 12, '2025-04-12', 560000, 7),
(N'SP00068', N'Case VSP V2880', N'case3.jpg', 1, 8, 12, '2025-04-12', 230000, 8),
(N'SP00069', N'Case ASUS ROG Z11 ITX Đen', N'case4.jpg', 1, 8, 3, '2025-04-12', 2127000, 9),
(N'SP00070', N'Case ASUS TUF Gaming GT501 White Edition', N'case5.jpg', 1, 8, 3, '2025-04-12', 4120000, 4),
(N'SP00071', N'Case ASUS ROG STRIX HELIOS GX601 GUNDAM', N'case6.jpg', 1, 8, 3, '2025-04-12', 9999000, 6),
(N'SP00072', N'Case ASUS ROG Hyperion GR701', N'case7.jpg', 1, 8, 3, '2025-04-12', 10900000, 7),
(N'SP00073', N'Case Gigabyte GB-AC300G Tempered Glass', N'case8.jpg', 1, 8, 5, '2025-04-12', 2620000, 8),
(N'SP00074', N'Case Gigabyte AORUS AC300W', N'case9.jpg', 1, 8, 5, '2025-04-12', 2199000, 9),
(N'SP00075', N'Case Gigabyte C301 GLASS White', N'case10.jpg', 1, 8, 5, '2025-04-12', 2340000, 5),

(N'SP00076', N'Màn hình ASUS ProArt PA278QV', N'man1.jpg', 1, 9, 3, '2025-04-12', 7900000, 5),
(N'SP00077', N'Màn hình ASUS BG279QM 27inch', N'man2.jpg', 1, 9, 3, '2025-04-12', 6950000, 6),
(N'SP00078', N'Màn hình ASUS XG27WQ', N'man3.jpg', 1, 9, 3, '2025-04-12', 12789000, 8),
(N'SP00079', N'Màn hình ASUS VA24EHE', N'man4.jpg', 1, 9, 3, '2025-04-12', 2901000, 2),
(N'SP00080', N'Màn hình ASUS TUF VG32VQ', N'man5.jpg', 1, 9, 3, '2025-04-12', 12789000, 3),
(N'SP00081', N'Màn hình ASUS TUF GAMING VG249Q', N'man6.jpg', 1, 9, 3, '2025-04-12', 6200000, 7),
(N'SP00082', N'Màn hình ASUS XG258Q', N'man7.jpg', 1, 9, 3, '2025-04-12', 11209000, 5);

-- import sp vao tung bang
-- mainboard
insert into sanphammainboard(maMainBoard, sanphamID, modelMain, chipset, socketMain, kichthuoc, soKheRAM, mota)
values
(N'MB00001', 1, N'Z790 GAMING WIFI', N'Intel Z790', N'LGA1700', N'ATX', 4, N'Hỗ trợ CPU Intel thế hệ 12, 13| Cung cấp 4 khe RAM DDR5, tối đa lên đến 192GB| Hỗ trợ 5 cổng PCI-E 3.0 và 4.0| Hỗ trợ 3 cổng M.2 và 4 cổng SATA 6G'),
(N'MB00002', 2, N'B550M Steel Legend', N'B550', N'AM4', N'M-ATX', 4, N'Hỗ trợ CPU AMD| Cung cấp 4 khe RAM DDR4 DIMM, tối đa 128GB| Cung cấp 6 cổng SATA3, 1 cổng Hyper M.2, 1 cổng M.2| Hỗ trợ Wireless 2.5Gigabit LAN'),
(N'MB00003', 3, N'Z590 TAICHI', N'Z590', N'LGA1200', N'Mini ITX', 4, N'Hỗ trợ CPU Intel thế hệ 10 và 11| Cung cấp 4 khe RAM DDR4, tối đa 128GB| Hỗ trợ 8 cổng SATA3, 1 cổng Hyper M.2 Socket, 2 cổng Ultra M.2 Socket'),
(N'MB00004', 4, N'PRIME H510M-K', N'H510', N'LGA1200', N'M-ATX', 2, N'Hỗ trợ CPU Intel Core thế hệ 10, 11| Cung cấp 2 khe RAM DDR4, tối đa 64GB| Hỗ trợ 4 cổng SATA3| Hỗ trợ mạng Ethernet Intel I219V'),
(N'MB00005', 5, N'ROG MAXIMUS XII HERO WIFI', N'Z490', N'LGA1200', N'ATX', 4, N'Hỗ trợ CPU Intel| Cung cấp 4 khe RAM DDR4 DIMM, tối đa 128GB| Hỗ trợ 6 cổng SATA3, 3 cổng NVM.e| Cung cấp 3 cổng kết nối mạng, tích hợp mạng không dây Wifi 6 AX201'),
(N'MB00006', 6, N'TUF GAMING X570 PLUS', N'AMD X570', N'AM4', N'ATX', 4, N'Hỗ trợ CPU Socket AMD thế hệ 2 và 3| Cung cấp 4 khe RAM, tối đa 128GB| Hỗ trợ MultiGPU| Hỗ trợ 8 cổng SATA3, 2 cổng M.2 Socket 3| Hỗ trợ Workstation Intel C612 Chipset'),
(N'MB00007', 7, N'X470 GAMING PLUS', N'AMD A320', N'AM4', N'ATX', 4, N'Hỗ trợ CPU AMD tối đa đến Ryzen 7| Hỗ trơ 4 khe cắm RAM DDR4 Dual lên đến 64GB| Cung cấp 7 cổng PCI-e| Hỗ trợ 8 cổng SATA3, 2 cổng M.2 Slot'),
(N'MB00008', 8, N'X470 GAMING PRO', N'AMD X470', N'AM4', N'ATX', 4, N'Hỗ trợ CPU AMD Ryzen, A Series và Athlon| Hỗ trợ 4 khe cắm RAM DDR4 tối đa 64GB| Hỗ trợ card Onboard| Cung cấp 6 khe cắm PCI-ex16, 6 cổng SATA3, 2 cổng M.2');

-- CPU
insert into sanphamcpu(maCPU, sanPhamID, loaiCPU, soNhansoLuong, mota)
values
(N'CPU00001', 9, N'Intel', N'6 nhân 12 luồng', N'CPU Core i5 thế hệ thứ 10| Sử dụng socket LGA1200| Tốc độ cơ bản: 2.9GHz, tốc độ tối đa: 4.3GHz| Cache 14MB| Hỗ trợ 64-bit, Siêu phân luồng, công nghệ ảo hoá, bộ nhớ DDR4 2666MHz| TDP 65W'),
(N'CPU00002', 10, N'Intel', N'6 nhân 12 luồng', N'CPU Core i5 thế hệ thứ 11| Sử dụng socket FGCLGA1200| Tốc độ cơ bản: 2.6GHz, tốc độ tối đa: 4.4GHz| Cache 12MB| Hỗ trợ vi xử lí đồ hoạ Intel UHD Graphic 730| Hỗ trợ bộ nhớ tối đa 128GB DDR4 3200MHz| TDP 65W| Nhiệt độ tối đa 100 độ C'),
(N'CPU00003', 11, N'AMD', N'6 nhân 12 luồng', N'CPU Ryzen thế hệ thứ 5| Kiến trúc Zen 3 mới nhất của AMD| Sử dụng socket AM4| Tốc độ cơ bản: 3.7GHz, tốc độ tối đa: 4.8GHz| Cache 3MB + 32MB| Hỗ trợ bộ nhớ DDR4 3200MHz| Hỗ trợ mở khoá hệ số nhân| TDP 65W'),
(N'CPU00004', 12, N'Intel', N'4 nhân 8 luồng', N'CPU Core i3 thế hệ thứ 10| Sử dụng Socket LGA1200| Tốc độ cơ bản: 3.7GHz, tốc độ tối đa: 4.4GHz| Cache 6MB| Hỗ trợ bộ nhớ DDR4 2666MHz| Hỗ trợ 64-bit, công nghệ ảo hoá, 2 kênh bộ nhớ| TDP 65W'),
(N'CPU00005', 13, N'Intel', N'6 nhân 12 luồng', N'CPU Core i5 thế hệ thứ 12| Sử dụng Socket FCLGA1700| Tốc độ cơ bản: 2.5GHz, tối đa 4.4GHz| Cache 18MB + 7.5MB| Hỗ trợ bộ nhớ DDR4 3200MHz và DDR5 4800MHz| Hỗ trợ 64-bit, 2 kênh bộ nhớ, công nghệ ảo hoá| Tích hợp nhân đồ hoạ UHD Graphics 730| TDP 65W'),
(N'CPU00006', 14, N'Intel', N'16 nhân 24 luồng', N'CPU Core i7 thế hệ 12| Sử dụng Socket LGA1700| Tốc độ cơ bản: 3.6GHz, tối đa 5.4GHz| Cache 30MB| Hỗ trợ bộ nhớ DDR4 3200MHz và DDR5 4800MHz| Hỗ trợ 64-bit, siêu phân luồng, 2 kênh bộ nhớ, công nghệ ảo hoá, nhân đồ hoạ tích hợp| PCI Express 5.0| TDP 125W'),
(N'CPU00007', 15, N'Intel', N'6 nhân 12 luồng', N'CPU Core i5 thế hệ 11| Sử dụng socket LGA1200| Tốc độ cơ bản: 2.6GHz, tối đa 4.4GHz| Cache 12MB| Hỗ trợ 64-bit, 2 kênh bộ nhớ, ảo hoá| PCI Express 4.0| Hỗ trợ bộ nhớ DDR4 3200MHz| TDP 65W'),
(N'CPU00008', 16, N'Intel', N'16 nhân 24 luồng', N'CPU Core i9 thế hệ 12| Sử dụng socket FCLGA1700| Tốc độ cơ bản: 2.4 - 3.2GHz, tối đa 3.9 - 5.2GHz| Cache 30MB + 14MB| Tích hợp nhân đồ hoạ UHD Graphics 770| Hỗ trợ bộ nhớ DDR4 3200MHz và DDR5 4800MHz, tối đa 128GB| TDP 125 - 241W| Nhiệt độ tối đa 100 độ C'),
(N'CPU00009', 17, N'AMD', N'6 nhân 12 luồng', N'AMD Ryzen 5| Sử dụng socket AM4| Tốc độ cơ bản: 3.6GHz, tối đa 4.4GHz| Cache 16MB + 3MB| Hỗ trợ 2 kênh bộ nhớ, DDR4 3200MHz| Phiên bản PCIe Express 3.0| TDP 65W| Nhiệt độ tối đa 95 độ C'),
(N'CPU00010', 18, N'AMD', N'8 nhân 16 luồng', N'AMD Ryzen 7 9000 series| Sử dụng socket AM5| Tốc độ cơ bản: 4.7GHz, tối đa 5.2GHz| Cache 8MB + 96MB| Hỗ trợ 4 kênh bộ nhớ, DDR5 3600MHz - 5200MHz| Phiên bản PCIe Express 5.0| Hỗ trợ 2 nhân đồ hoạ AMD Radeon Graphics| TDP 120W| Nhiệt độ tối đa 95 độ C'),
(N'CPU00011', 19, N'Intel', N'14 nhân 20 luồng', N'CPU Core i5 thế hệ 13| Sử dụng socket LGA1700| Tốc độ cơ bản: 3.6GHz, tối đa 5.1GHz| Cache 20MB| Hỗ trợ bộ nhớ DDR4 3200MHz, DDR5 4800MHz| Hỗ trợ 54-bit, siêu phân luồng, ảo hoá| TDP 125W'),
(N'CPU00012', 20, N'Intel', N'16 nhân 24 luồng', N'CPU Core i7 thế hệ 12| Sử dụng socket LGA1700| Tốc độ cơ bản: 3.6GHz, tối đa 5.4GHz| Cache 25MB| Hỗ trợ 2 kênh bộ nhớ DDR4 3200MHz, DDR5 4800MHz| PCIe Express 5.0| Hỗ trợ 64-bit, siêu phân luồng, ảo hoá| Tích hợp nhân đồ hoạ| TDP 125W');

-- RAM
insert into sanphamram(maRAM, sanphamID, chuanRAM, dungLuong, mota)
values
(N'RAM00001', 21, N'DDR4', N'8GB', N'Loại Desktop| Tốc độ 3000MHz| Màu đen, có tản nhiệt'),
(N'RAM00002', 22, N'DDR4', N'16GB', N'Loại Desktop| Tốc độ 3200MHz| Tương thích 1.35V, hỗ trợ ép xung'),
(N'RAM00003', 23, N'DDR4', N'32GB', N'Loại Desktop| Tốc độ 3200MHz| Màu trắng, có tản nhiệt, tương thích 1.35V'),
(N'RAM00004', 24, N'DDR4', N'8GB', N'Loại Desktop| Tốc độ 3200MHz| Điện áp 1.2V, hỗ trợ ép xung'),
(N'RAM00005', 25, N'DDR4', N'8GB', N'Loại Desktop| Tốc độ 3200MHz| Điện áp 1.2V, hàng secondhand còn bảo hành, hỗ trợ ép xung'),
(N'RAM00006', 26, N'DDR5', N'64GB', N'Loại Desktop| Tốc độ 6000MHz| Điện áp 1.2 - 1.45V, nhiệt độ hoạt động từ 0 đến 85 độ C'),
(N'RAM00007', 27, N'DDR4', N'8GB', N'Loại Desktop| Tốc độ 3200MHz| Điện áp 1.35V, có tản nhiệt'),
(N'RAM00008', 28, N'DDR4', N'8GB', N'Loại Desktop| Tốc độ 3200MHz| Điện áp 1.35V, hỗ trợ tản nhiệt'),
(N'RAM00009', 29, N'DDR4', N'16GB', N'Loại Desktop| Tốc độ 3200MHz| Điện áp 1.35V, có led RGB, có tản nhiệt'),
(N'RAM00010', 30, N'DDR4', N'8GB', N'Loại Desktop| Tốc độ 3200MHz| Điện áp 1.35V, có tản nhiệt');

-- VGA
insert into sanphamvga(maVGA, sanphamID, kieubonho, dungluongbonho, chipGPU, mota)
values
(N'VGA00001', 31, N'GDDR7', N'16GB', N'NVIDIA RTX 5070Ti', N'Lõi CUDA: 8960| Tốc độ bộ nhớ 28Gbps| OpenGL 4.6| Hỗ trợ độ phân giải 4K| Hỗ trợ cổng HDMI2.1b, DisplayPort2.1a, HDCP| Nguồn yêu cầu 1 chân 16 pin| TDP 250W'),
(N'VGA00002', 32, N'GDDR7', N'16GB', N'NVIDIA RTX 5060Ti', N'Lõi CUDA: 4608| Tốc độ bộ nhớ 28Gbps| OpenGL 4.6| Hỗ trợ độ phân giải 4K| Hỗ trợ cổng HDMI2.1b, DisplayPort2.1b, HDCP| Nguồn yêu cầu 1 chân 16 pin| TDP 250W'),
(N'VGA00003', 33, N'GDDR6', N'16GB', N'Radeon RX 9060 XT', N'Lõi CUDA: 2048| Tốc độ bộ nhớ 28Gbps| OpenGL 4.6| Hỗ trợ độ phân giải 4K| Hỗ trợ cổng HDMI2.1a, DisplayPort2.1a| Nguồn yêu cầu 1 chân 8 pin| TDP 200W'),
(N'VGA00004', 34, N'GDDR5', N'2GB', N'NVIDIA GT 1030', N'Xung GPU từ 1250MHz đến 1531MHz| Xung bộ nhớ 6008MHz| DirectX 12| Bus 64 bits| Hỗ trợ cổng HDMI, DVI-D| Yêu cầu nguồn 350W'),
(N'VGA00005', 35, N'GDDR7', N'12GB', N'NVIDIA RTX 5070', N'Lõi CUDA: 6144| Tốc độ bộ nhớ 28Gbps| OpenGL 4.6| Hỗ trợ độ phân giải 4K| Hỗ trợ cổng HDMI2.1b, DisplayPort2.1a, HDCP| Yêu cầu nguồn 1 chân 16 pin, công suất 750W'),
(N'VGA00006', 36, N'GDDR7', N'12GB', N'NVIDIA RTX 5070', N'Lõi CUDA: 6144| Tốc độ bộ nhớ 28Gbps| OpenGL 4.6| Hỗ trợ độ phân giải 4K| Hỗ trợ cổng HDMI2.1b, DisplayPort2.1a, HDCP 2.3| Yêu cầu nguồn 1 chân 16 pin, công suất 750W'),
(N'VGA00007', 37, N'GDDR6', N'4GB', N'NVIDIA GTX 1650 Super', N'Lõi CUDA: 896| Giao tiếp 128-bit| Xung nhịp tối đa 1725MHz| DirectX 12, OpenGL 4.5| Hỗ trợ cổng DVI-D, HDMI2.0b, DisplayPort1.4, HDCP 2.2| TDP 65W| Nguồn yêu cầu 300W trở lên'),
(N'VGA00008', 38, N'GDDR6', N'4GB', N'Radeon RX 6500 XT', N'Giao diện bộ nhớ 64-bit| Hỗ trợ độ phân giải 4K| Hỗ trợ cổng HDMI2.1, DisplayPort1.4a, HDCP2.3| Nguồn 1 chân 6 pin| Yêu cầu nguồn 500W'),
(N'VGA00009', 39, N'GDDR6', N'12GB', N'NVIDIA RTX 3060', N'Lõi CUDA: 3584| Xung bộ nhớ 15000MHz| Hỗ trợ độ phân giải 4K| DirectX12 Ultimate, OpenGL 4.6| Hỗ trợ cổng HDMI2.1, DisplayPort1.4a| Nguồn 1 chân 8 pin| Yêu cầu nguồn 550W'),
(N'VGA00010', 40, N'GDDR6', N'8GB', N'NVIDIA RTX 3060Ti', N'Lõi CUDA: 4864| Tốc độ bộ nhớ 14 Gbps| Hỗ trợ độ phân giải 4K| 2 chế độ xung 1695MHz và 1665MHz| Hỗ trợ cổng HDMI2.1, DisplayPort1.4a, HDCP2.3| Nguồn 1 chân 8 pin| Yêu cầu nguồn 750W'),
(N'VGA00011', 41, N'GDDR6', N'8GB', N'NVIDIA RTX 4060Ti', N'Lõi CUDA: 4352| Xung nhịp 2550MHz| Tốc độ bộ nhớ 18Gbps| OpenGL 4.6| Hỗ trợ độ phân giải 4K| Hỗ trợ cổng HDMI2.1a, DisplayPort1.4a| Nguồn 1 chân 8 pin| Nguồn yêu cầu 500W'),
(N'VGA00012', 42, N'GDDR6', N'12GB', N'Radeon RX 7700 XT', N'Lõi CUDA 3456| Xung bộ nhớ 18000MHz| Hỗ trợ độ phân giải 4K| Hỗ trợ cổng HDMI2.1, DisplayPort2.1| Nguồn 2 dây 8 pin| Yêu cầu nguồn 700W');

-- Ổ cứng
insert into sanphamocung(maOCung, sanPhamID, loaiOCung, dungLuong, mota)
values
(N'STR00001', 43, N'SSD', N'120GB', N'Chuẩn giao tiếp SATA3 6Gbps| Kích thước 2.5inch| Tốc độ đọc, ghi: 500MBps, 320MBps| MTBF: 1000000h'),
(N'STR00002', 44, N'HDD', N'8TB', N'Chuẩn giao tiếp SATA3 6Gbps| Cache 256MB| Kích thước 3.5inch'),
(N'STR00003', 45, N'HDD', N'12TB', N'Chuẩn giao tiếp SATA3 6Gbps| Cache 256MB| Kích thước 3.5inch'),
(N'STR00004', 46, N'HDD', N'6TB', N'Chuẩn giao tiếp SATA3 6Gbps| Cache 256MB| Kích thước 3.5inch'),
(N'STR00005', 47, N'SSD', N'512GB', N'Chuẩn giao tiếp M.2| Tốc độ đọc ghi: 1700MBps, 1550MBps| MTBF: 1500000h'),
(N'STR00006', 48, N'SSD', N'500GB', N'Chuẩn giao tiếp M.2| Tốc độ đọc ghi: 2200MBps, 1500MBps| Chip nhớ: 3D NAND| MTBF: 1500000h'),
(N'STR00007', 49, N'SSD', N'256GB', N'Chuẩn giao tiếp SATA3 6Gbps| Tốc độ đọc ghi: 550MBps, 500MBps| Kích thước 2.5inch| TBW: 600TB| MTBF: 1000000'),
(N'STR00008', 50, N'SSD', N'512GB', N'Chuẩn giao tiếp SATA3 6Gbps| Tốc độ đọc ghi: 550MBps, 520MBps| Kích thước 2.5inch| MTBF: 1000000h');

-- Nguồn
insert into sanphampsu(maPSU, sanphamid, dienapvao, congsuat, mota)
values
(N'PSU00001', 51, 220, 1000, N'Kích thước 150x150x86 mm| Các cổng kết nối hỗ trợ: MB 24/20 pin, CPU 4+4 pin, PCI-E 16 pin, PCI-E 8 pin, SATA, PERIPERAL'),
(N'PSU00002', 52, 220, 650, N'Kích thước 150x150x86 mm| Các cổng kết nối hỗ trợ: MB 24/20 pin, CPU 4+4 pin, PCI-e 6+2 pin, SATA, Periplheral'),
(N'PSU00003', 53, 220, 750, N'Kích thước 150x150x86 mm| Các cổng kết nối hỗ trợ: MB 24/20 pin, CPU 4+4 pin, PCI-e 6+2 pin, SATA, Periplheral'),
(N'PSU00004', 54, 220, 1000, N'Kích thước 160x160x86 mm| Các cổng kết nối hỗ trợ: ATX 24 pin, CPU 4+4 pin, PCI-e 6+2 pin, SATA, Periplheral'),
(N'PSU00005', 55, 220, 1000, N'Kích thước 150x150x86 mm| Các cổng kết nối hỗ trợ: ATX 24 pin, CPU 4+4 pin, PCI-e 16 pin, PCI-e 6+2 pin, SATA, MOLEX, FDD'),
(N'PSU00006', 56, 220, 1000, N'Kích thước 585x257x537 mm| Các cổng kết nối hỗ trợ: ATX 24 pin, CPU 4+4 pin, PCI-e 16 pin, PCI-e 6+2 pin, SATA, MOLEX, FDD'),
(N'PSU00007', 57, 220, 850, N'Kích thước 150x150x86 mm| Các cổng kết nối hỗ trợ: ATX 24 pin, CPU 4+4 pin, PCI-e 6+2 pin, SATA, PATA'),
(N'PSU00008', 58, 220, 750, N'Kích thước 150x150x86 mm| Các cổng kết nối hỗ trợ: ATX 24 pin, CPU 4+4 pin, PCI-e 6+2 pin, SATA, PATA'),
(N'PSU00009', 59, 220, 1200, N'Kích thước 150x150x86 mm| Các cổng kết nối hỗ trợ: ATX 24 pin, CPU 4+4 pin, PCI-e 16 pin, PCI-e 6+2 pin, SATA, MOLEX, FDD');

-- Tản
insert into sanphamcooler(maCooler, sanphamid, loaitan, coled, mota)
values
(N'TAN00001', 60, N'Tản nhiệt nước', 1, N'Phù hợp với các CPU socket LGA2066, 2011-v3, 2011, 1200, 1151, 1150, 1155, 1156, AM4, AM3| Kích thước 140x140x25 mm| Tuổi thọ 70000h| TDP 2.36W'),
(N'TAN00002', 61, N'Tản nhiệt nước', 1, N'Phù hợp với các CPU: LGA2066/2011-v3/2011/1151/1150/1155/1156/1366/775, AM4/3/3+/2/2+| Kích thước 80x76x47 mm'),
(N'TAN00003', 62, N'Tản nhiệt khí', 1, N'Phù hợp với các CPU: LGA2066/2011-v3/2011/1200/1151/1150/1155/1156/1366, AM4/3/3+/2/2+, FM2/2+/1| Tốc độ quạt 650-1800 RPM'),
(N'TAN00004', 63, N'Tản nhiệt khí', 0, N'Phù hợp với các CPU: LGA 2066/2011-v3/2011/1200/1366/1156/1155/1151/1150, AM4/3/3+/2/2+| Tốc độ quạt 650-1800RPM| Đầu cấp nguồn chân 4 pin'),
(N'TAN00005', 64, N'Tản nhiệt nước', 1, N'Phù hợp với các CPU: LGA 1200/1150/1151/1155/1156/1366/2011/2066, AM4/3/2| Kích thước 277x120x27 mm'),
(N'TAN00006', 65, N'Tản nhiệt nước', 0, N'Phù hợp với các CPU: LGA 20xx/115x, AM4/3/3+/2/2+| Kích thước quạt 120x120x25mm');

-- case
insert into sanphamcase(macase, sanphamid, hotromain, maucase, mota)
values
(N'CASE0001', 66, N'M-ATX', N'Trắng', N'Trọng lượng 5.4kg| Khay HDD/SSD 3.5 x2, SSD 2.5 x2, 4 khe mở rộng| Tản CPU cao tối đa 165mm, VGA dài tối đa 275mm| Cung cấp sẵn quạt tản nhiệt'),
(N'CASE0002', 67, N'ATX', N'Trắng', N'Khay HDD/SSD 3.5, SSD 2.5| Chiều cao tản CPU tối đa: 155mm, VGA dài tối đa 340mm'),
(N'CASE0003', 68, N'M-ATX', N'Đen', N'Kích thước vỏ case 310x170x350 mm| Chiều cao tản CPU tối đa 145mm, VGA dài tối đa 220mm'),
(N'CASE0004', 69, N'M-ITX', N'Đen', N'Khay HDD/SSD 3.5, SSD 2.5 x4| Sử dụng kính cường lực, cung cấp sẵn hệ thống làm mát| Chiều cao tản CPU tối đa 130mm, VGA dài tối đa 320mm'),
(N'CASE0005', 70, N'ATX', N'Trắng', N'Khe cắm mở rộng 7+2| Kích thước 251x545x552 mm| Khối lượng 10,5kg| Chiều cao tản CPU tối đa 180mm, VGA dài tối đa 420mm'),
(N'CASE0006', 71, N'E-ATX', N'Đen', N'Khay HDD/SSD 3.5 x2, SSD 2.5 x4| Khe mở rộng 8+2| Kích thước: 525x261x538 mm| Chiều cao tản CPU tối đa 190mm, VGA dài tối đa 450mm'),
(N'CASE0007', 72, N'E-ATX', N'Đen', N'Trọng lượng 20.8kg| Kích thước 268x639x659 mm| Khay HDD/SSD 3.5 x2, SSD 2.5 x5'),
(N'CASE0008', 73, N'E-ATX', N'Đen', N'Kích thước 211x469x458 mm| Khay HDD/SSD 3.5 x3, SSD 2.5 x2| Hỗ trợ 9 khe cắm mở rộng| Chiều cao tản CPU tối đa 180mm, VGA dài tối đa 400mm'),
(N'CASE0009', 74, N'ATX', N'Đen', N'Kích thước 211x469x458mm| Khay HDD/SSD 3.5 x2, SSD 2.5 x2| Hỗ trợ 9 khe cắm mở rộng| Chiều cao tản CPU tối đa 180mm, VGA dài tối đa 400mm'),
(N'CASE0010', 75, N'M-ATX', N'Trắng', N'Kích thước 486x220x473 mm| Khay HDD/SSD 3.5 x2, SSD 2.5 x2| Chiều cao tản CPU tối đa 170mm, VGA dài tối đa 400mm');

-- màn hình
insert into sanphammanhinh(maMH, sanphamid, kichthuoc, bemat, tansoquet, tamnen, dophangiai, mota)
values
(N'MH00001', 76, 27, N'Màn hình phẳng', 75, N'IPS', N'2K 2560x1440', N'Màu sắc hỗ trợ: sRGB| Tỉ lệ màn hình 16:9| Góc nhìn 178/178'),
(N'MH00002', 77, 27, N'Màn hình phẳng', 144, N'IPS', N'FHD 1920x1080', N'Hỗ trợ hiển thị chống loá 3H| Góc nhìn 178/178'),
(N'MH00003', 78, 27, N'Màn hình cong', 165, N'VA', N'2K 2560x1440', N'Màu sắc hiển thị: 16 triệu màu| Độ sáng 400cd/m2| Góc nhìn 178/178'),
(N'MH00004', 79, 24, N'Màn hình phẳng', 75, N'IPS', N'FHD 1920x1080', N'Màu sắc hiển thị 16 triệu màu| Thời gian phản hồi 5ms| Góc nhìn 178/178'),
(N'MH00005', 80, 32, N'Màn hình cong', 144, N'WLED', N'2K 2560x1440', N'Màu sắc màn hình sRGB| Độ tương phản 3000:1| Góc nhìn 178/178'),
(N'MH00006', 81, 24, N'Màn hình phẳng', 144, N'IPS', N'FHD 1920x1080', N'Màu sắc màn hình sRGB| Độ tương phản 1000:1| Góc nhìn 178/178'),
(N'MH00007', 82, 25, N'Màn hình phẳng', 240, N'TN', N'FHD 1920x1080', N'Màu sắc màn hình sRGB| Độ tương phản 100000000:1| Góc nhìn 170/160');