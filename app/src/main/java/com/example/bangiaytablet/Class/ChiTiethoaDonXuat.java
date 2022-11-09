package com.example.bangiaytablet.Class;

public class ChiTiethoaDonXuat {
    int MaHoaDonXuat,soLuongXuat,SizeXuat;
    String MaHangXuat,tenhangXuat,MauSac,thuonghieu;
    Double giaXuat;
    byte[] anhsp;

    public ChiTiethoaDonXuat(int maHoaDonXuat, int soLuongXuat, int sizeXuat, String maHangXuat, String tenhangXuat, String mauSac, String thuonghieu, Double giaXuat, byte[] anhsp) {
        MaHoaDonXuat = maHoaDonXuat;
        this.soLuongXuat = soLuongXuat;
        SizeXuat = sizeXuat;
        MaHangXuat = maHangXuat;
        this.tenhangXuat = tenhangXuat;
        MauSac = mauSac;
        this.thuonghieu = thuonghieu;
        this.giaXuat = giaXuat;
        this.anhsp = anhsp;
    }


    public ChiTiethoaDonXuat(int soLuongXuat, int sizeXuat, String maHangXuat, String tenhangXuat, String mauSac, Double giaXuat, byte[] anhsp) {
        this.soLuongXuat = soLuongXuat;
        SizeXuat = sizeXuat;
        MaHangXuat = maHangXuat;
        this.tenhangXuat = tenhangXuat;
        MauSac = mauSac;
        this.giaXuat = giaXuat;
        this.anhsp = anhsp;
    }

    public int getMaHoaDonXuat() {
        return MaHoaDonXuat;
    }

    public void setMaHoaDonXuat(int maHoaDonXuat) {
        MaHoaDonXuat = maHoaDonXuat;
    }

    public int getSoLuongXuat() {
        return soLuongXuat;
    }

    public void setSoLuongXuat(int soLuongXuat) {
        this.soLuongXuat = soLuongXuat;
    }

    public int getSizeXuat() {
        return SizeXuat;
    }

    public void setSizeXuat(int sizeXuat) {
        SizeXuat = sizeXuat;
    }

    public String getMaHangXuat() {
        return MaHangXuat;
    }

    public void setMaHangXuat(String maHangXuat) {
        MaHangXuat = maHangXuat;
    }

    public String getTenhangXuat() {
        return tenhangXuat;
    }

    public void setTenhangXuat(String tenhangXuat) {
        this.tenhangXuat = tenhangXuat;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String mauSac) {
        MauSac = mauSac;
    }

    public String getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public Double getGiaXuat() {
        return giaXuat;
    }

    public void setGiaXuat(Double giaXuat) {
        this.giaXuat = giaXuat;
    }

    public byte[] getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(byte[] anhsp) {
        this.anhsp = anhsp;
    }
}
