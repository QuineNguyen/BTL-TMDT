use gearshop;

create table nguoiDung(
	ID int primary key auto_increment not null,
    maNguoiDung nvarchar(10),
    tenNguoiDung nvarchar(50),
    tenDangNhap nvarchar(20),
    matKhau nvarchar(20),
    email nvarchar(30),
    sdt nvarchar(15),
    diaChi nvarchar(200)
);
create table nhanVien(
	ID int primary key auto_increment not null,
    maNhanVien nvarchar(10),
    nguoiDungID int,
    ghiChu nvarchar(100),
    foreign key (nguoiDungID) references nguoiDung(ID)
);

create table khachHang(
	ID int primary key auto_increment not null,
    maKhachHang nvarchar(10),
    ghiChu nvarchar(100)
);

create table thongTinNhanHang(
	ID int primary key auto_increment not null,
    khachHangID int,
    tenNguoiNhan varchar(100),
    sdt varchar(20),
    diachi nvarchar(200),
    foreign key (khachHangID) references khachHang(ID)
);

create table gioHang(
	ID int primary key auto_increment not null,
    maGioHang nvarchar(10),
	thongTinNhanHangID int,
    foreign key (thongTinNhanHangID) references thongTinNhanHang(ID)
);

create table thuongHieu(
	ID int primary key auto_increment not null,
    maThuongHieu nvarchar(10),
    tenThuongHieu nvarchar(20)
);

create table loaiSanPham(
	ID int primary key auto_increment not null,
    maLoaiSP nvarchar(10),
    tenLoaiSanPham nvarchar(20)
);
create table sanPham(
	ID int primary key auto_increment not null,
    maSanPham nvarchar(10),
    tenSanPham nvarchar(100),
    hinhAnh nvarchar(50),
    nguoiThemID int,
    loaiSPID int,
    thuongHieuID int,
    ngayThem datetime,
    gia decimal(18,0),
    foreign key (nguoiThemID) references nhanVien(ID),
    foreign key (loaiSPID) references loaiSanPham(ID),
    foreign key (thuongHieuID) references thuongHieu(ID)
);

create table gioHangChiTiet(
	ID int primary key auto_increment not null,
    maGioHangChiTiet nvarchar(10),
    gioHangID int,
    sanPhamID int,
    soLuong int,
    donGia decimal(18,0),
    foreign key (gioHangID) references gioHang(ID),
    foreign key (sanPhamID) references sanPham(ID)
);

create table hoaDon(
	ID int primary key auto_increment not null,
    maHoaDon nvarchar(10),
    thongTinNhanHangID int,
    ngayTao datetime,
    tongGia decimal(18,0),
    trangThaiDonHang nvarchar(20),
    foreign key (thongTinNhanHangID) references khachHang(ID)
);

create table hoaDonChiTiet(
	ID int primary key auto_increment not null,
    maHoaDonChiTiet nvarchar(10),
    hoaDonID int,
    sanPhamID int,
    soLuongSP int,
    thanhTien decimal(18,0),
    foreign key (hoaDonID) references hoaDon(ID),
    foreign key (sanPhamID) references sanPham(ID)
);

create table yeuCauHoanTien(
	ID int primary key auto_increment not null,
    maYeuCauHoanTien nvarchar(10),
    hoaDonChiTietID int,
    ngayYeuCau datetime,
    trangThai nvarchar(15),
    foreign key (hoaDonChiTietID) references hoaDonChiTiet(ID)
);

create table voucher(
	ID int primary key auto_increment not null,
    maVoucher nvarchar(10),
    tenVoucher nvarchar(30),
    giamGiaTheoPhanTram int,
    giamGiaCuThe decimal(18,0),
    thoiHan datetime,
    donToiThieu decimal(18,0)
);

create table voucherKhachHang(
	ID int primary key auto_increment not null,
    maVoucherKhachHang nvarchar(10),
    khachHangID int,
    voucherID int,
    daDung boolean,
    foreign key (khachHangID) references khachHang(ID),
    foreign key (voucherID) references voucher(ID)
);

create table danhGia(
	ID int primary key auto_increment not null,
    khachHangID int,
    sanPhamID int,
    soSao int,
    noiDung nvarchar(200),
    foreign key (khachHangID) references khachHang(ID),
    foreign key (sanPhamID) references sanPham(ID)
);

create table sanPhamMainboard(
	ID int primary key auto_increment not null,
    maMainBoard nvarchar(10),
    sanPhamID int,
    modelMain nvarchar(20),
    chipset nvarchar(20),
    socketMain nvarchar(20),
    kichThuoc nvarchar(20),
    soKheRAM int,
    mota nvarchar(200),
    foreign key (sanPhamID) references sanPham(ID)
);

create table sanPhamCPU(
	ID int primary key auto_increment not null,
    maCPU nvarchar(10),
    sanPhamID int,
    loaiCPU nvarchar(20),
    soNhansoLuong nvarchar(20),
    mota nvarchar(200),
    foreign key (sanPhamID) references sanPham(ID)
);

create table sanPhamRAM(
	ID int primary key auto_increment not null,
    maRAM nvarchar(10),
    sanPhamID int,
    chuanRAM nvarchar(10),
    dungLuong nvarchar(10),
    mota nvarchar(200),
    foreign key (sanPhamID) references sanPham(ID)
);

create table sanPhamVGA(
	ID int primary key auto_increment not null,
    maVGA nvarchar(10),
    sanPhamID int,
    kieuBoNho nvarchar(10),
    dungLuongBoNho nvarchar(10),
    chipGPU nvarchar(20),
    mota nvarchar(200),
    foreign key (sanPhamID) references sanPham(ID)
);

create table sanPhamOCung(
	ID int primary key auto_increment not null,
    maOCung nvarchar(10),
    sanPhamID int,
    loaiOCung nvarchar(6),
    dungLuong int,
    mota nvarchar(200),
    foreign key (sanPhamID) references sanPham(ID)
);

create table sanPhamPSU(
	ID int primary key auto_increment not null,
    maPSU nvarchar(10),
    sanPhamID int,
    dienApVao int,
    congSuat int,
    mota nvarchar(200),
    foreign key (sanPhamID) references sanPham(ID)
);

create table sanPhamCooler(
	ID int primary key auto_increment not null,
    maCooler nvarchar(10),
    sanPhamID int,
    loaiTan nvarchar(20),
    coLED boolean,
    mota nvarchar(200),
    foreign key (sanPhamID) references sanPham(ID)
);

create table sanPhamCase(
	ID int primary key auto_increment not null,
    maCase nvarchar(10),
    sanPhamID int,
    hoTroMain nvarchar(10),
    mauCase nvarchar(10),
    mota nvarchar(200),
    foreign key (sanPhamID) references sanPham(ID)
);

create table sanPhamManHinh(
	ID int primary key auto_increment not null,
    maMH nvarchar(10),
    sanPhamID int,
    kichThuoc int,
    beMat nvarchar(20),
    tanSoQuet int,
    tamNen nvarchar(10),
    doPhanGiai nvarchar(50),
    mota nvarchar(200),
    foreign key (sanPhamID) references sanPham(ID)
);

create table khuyenMai(
	ID int primary key auto_increment not null,
    maKM nvarchar(10),
    tenKM nvarchar(50),
    giamGiaPhanTram int,
    giamGiaCuThe decimal(18,0),
    ngayBatDau date,
    ngayKetThuc date,
    mota nvarchar(500)
);

create table khuyenMaiSanPham(
    sanPhamID int,
    khuyenMaiID int,
    primary key (sanPhamID, khuyenMaiID),
    foreign key (sanPhamID) references sanPham(ID) on delete cascade,
    foreign key (khuyenMaiID) references khuyenMai(ID) on delete cascade
);

create table phieuBaoHanh(
	ID int primary key auto_increment not null,
    maBH nvarchar(10),
    khachHangID int,
    sanPhamID int,
    thoiHanBaoHanh int, -- Bao nhiêu tháng
    ngayHetHan date,
    foreign key (khachHangID) references khachhang(ID),
    foreign key (sanPhamID) references sanpham(ID)
);





ALTER TABLE hoaDonChiTiet ADD CONSTRAINT fk_hoaDon FOREIGN KEY (hoaDonID) REFERENCES HoaDon(ID);
alter table nhanVien add column nguoiDungID int;
alter table khachHang add column nguoiDungID int;
alter table nhanVien add constraint nhanvien_ibfk_1 foreign key (nguoiDungID) references nguoiDung(ID);
alter table khachHang add constraint khachhang_ibfk_1 foreign key (nguoiDungID) references nguoiDung(ID);
alter table hoadon add column trangThaiDonHang nvarchar(20);
alter table sanpham add column tonKho int;
ALTER TABLE sanPham
modify COLUMN tenSanPham NVARCHAR(150);
alter table sanphammainboard modify column mota nvarchar(500);
alter table sanphamcpu modify column mota nvarchar(500);
alter table sanphamram modify column mota nvarchar(500);
alter table sanphamvga modify column mota nvarchar(500);
alter table sanphamocung modify column mota nvarchar(500);
alter table sanphampsu modify column mota nvarchar(500);
alter table sanphamcooler modify column mota nvarchar(500);
alter table sanphamcase modify column mota nvarchar(500);
alter table sanphammanhinh modify column mota nvarchar(500);
alter table sanphamvga modify column chipGPU nvarchar(50);
alter table sanphamocung modify column dungluong nvarchar(10);
alter table sanpham add column daBan int;
alter table yeucauhoantien add column loiNhan nvarchar(500);