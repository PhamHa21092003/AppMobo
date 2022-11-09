package com.example.bangiaytablet.Class;

public class ChitietHoaDonNhap {
    int MaHoaDonNhap,soLuongNhap,SizeNhap;
    String MaHangNhap,tenhangNhap,MauSac,thuonghieu;
    Double giaNhap;
    byte[] anhsp;

    public ChitietHoaDonNhap(int maHoaDonNhap, int soLuongNhap, int sizeNhap, String maHangNhap, Double giaNhap) {
        MaHoaDonNhap = maHoaDonNhap;
        this.soLuongNhap = soLuongNhap;
        SizeNhap = sizeNhap;
        MaHangNhap = maHangNhap;
        this.giaNhap = giaNhap;
    }

    public ChitietHoaDonNhap(int soLuongNhap, int sizeNhap, String maHangNhap, String tenhangNhap, String mauSac, Double giaNhap) {
        this.soLuongNhap = soLuongNhap;
        SizeNhap = sizeNhap;
        MaHangNhap = maHangNhap;
        this.tenhangNhap = tenhangNhap;
        MauSac = mauSac;
        this.giaNhap = giaNhap;
    }

    public ChitietHoaDonNhap(int maHoaDonNhap, int soLuongNhap, int sizeNhap, String maHangNhap, String tenhangNhap, String mauSac, Double giaNhap, byte[] anhsp) {
        MaHoaDonNhap = maHoaDonNhap;
        this.soLuongNhap = soLuongNhap;
        SizeNhap = sizeNhap;
        MaHangNhap = maHangNhap;
        this.tenhangNhap = tenhangNhap;
        MauSac = mauSac;
        this.giaNhap = giaNhap;
        this.anhsp = anhsp;
    }

    public ChitietHoaDonNhap(int soLuongNhap, int sizeNhap, String maHangNhap, String tenhangNhap, String mauSac, Double giaNhap, byte[] anhsp) {
        this.soLuongNhap = soLuongNhap;
        SizeNhap = sizeNhap;
        MaHangNhap = maHangNhap;
        this.tenhangNhap = tenhangNhap;
        MauSac = mauSac;
        this.giaNhap = giaNhap;
        this.anhsp = anhsp;
    }

    public ChitietHoaDonNhap(int maHoaDonNhap, int soLuongNhap, int sizeNhap, String maHangNhap, String tenhangNhap, String mauSac, String thuonghieu, Double giaNhap, byte[] anhsp) {
        MaHoaDonNhap = maHoaDonNhap;
        this.soLuongNhap = soLuongNhap;
        SizeNhap = sizeNhap;
        MaHangNhap = maHangNhap;
        this.tenhangNhap = tenhangNhap;
        MauSac = mauSac;
        this.thuonghieu = thuonghieu;
        this.giaNhap = giaNhap;
        this.anhsp = anhsp;
    }

    public String getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public byte[] getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(byte[] anhsp) {
        this.anhsp = anhsp;
    }

    public String getTenhangNhap() {
        return tenhangNhap;
    }

    public void setTenhangNhap(String tenhangNhap) {
        this.tenhangNhap = tenhangNhap;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String mauSac) {
        MauSac = mauSac;
    }

    public int getMaHoaDonNhap() {
        return MaHoaDonNhap;
    }

    public void setMaHoaDonNhap(int maHoaDonNhap) {
        MaHoaDonNhap = maHoaDonNhap;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public int getSizeNhap() {
        return SizeNhap;
    }

    public void setSizeNhap(int sizeNhap) {
        SizeNhap = sizeNhap;
    }

    public String getMaHangNhap() {
        return MaHangNhap;
    }

    public void setMaHangNhap(String maHangNhap) {
        MaHangNhap = maHangNhap;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }
}
