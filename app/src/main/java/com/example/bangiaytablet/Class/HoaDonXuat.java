package com.example.bangiaytablet.Class;

public class HoaDonXuat {
    String ngayTaoHoaDon, nguoiXuat, nguoiMua;
    int MaHoaDon;
    Double tongtien;

    public HoaDonXuat(String ngayTaoHoaDon, String nguoiXuat, String nguoiMua, int maHoaDon, Double tongtien) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.nguoiXuat = nguoiXuat;
        this.nguoiMua = nguoiMua;
        MaHoaDon = maHoaDon;
        this.tongtien = tongtien;
    }

    public HoaDonXuat(String ngayTaoHoaDon, String nguoiXuat, String nguoiMua, int maHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.nguoiXuat = nguoiXuat;
        this.nguoiMua = nguoiMua;
        MaHoaDon = maHoaDon;
    }

    public HoaDonXuat(String ngayTaoHoaDon, int maHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        MaHoaDon = maHoaDon;
    }

    public String getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(String ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    public String getNguoiXuat() {
        return nguoiXuat;
    }

    public void setNguoiXuat(String nguoiXuat) {
        this.nguoiXuat = nguoiXuat;
    }

    public String getNguoiMua() {
        return nguoiMua;
    }

    public void setNguoiMua(String nguoiMua) {
        this.nguoiMua = nguoiMua;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }
}
