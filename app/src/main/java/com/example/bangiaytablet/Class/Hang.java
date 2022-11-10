package com.example.bangiaytablet.Class;

public class Hang {
    String maHang, tenHang, nhaSanXuat, mausac;
    int size41, size42, size43;
    int SoLuong;
    Double gia;
    byte[] hinhanh;


    public Hang(String maHang, String tenHang, String nhaSanXuat, String mausac, int soLuong, Double gia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.nhaSanXuat = nhaSanXuat;
        this.mausac = mausac;
        SoLuong = soLuong;
        this.gia = gia;
    }

    public Hang(String maHang, String tenHang, String mausac, int soLuong, Double gia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.mausac = mausac;
        SoLuong = soLuong;
        this.gia = gia;
    }

    public Hang(String maHang, String tenHang, int soLuong, Double gia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        SoLuong = soLuong;
        this.gia = gia;
    }

    public Hang(String maHang, String tenHang, String nhaSanXuat, String mausac, int size41, int size42, int size43, int soLuong, Double gia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.nhaSanXuat = nhaSanXuat;
        this.mausac = mausac;
        this.size41 = size41;
        this.size42 = size42;
        this.size43 = size43;
        SoLuong = soLuong;
        this.gia = gia;
    }

    public Hang(String maHang, String tenHang, String nhaSanXuat, String mausac, int size41, int size42, int size43, int soLuong, Double gia, byte[] hinhanh) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.nhaSanXuat = nhaSanXuat;
        this.mausac = mausac;
        this.size41 = size41;
        this.size42 = size42;
        this.size43 = size43;
        SoLuong = soLuong;
        this.gia = gia;
        this.hinhanh = hinhanh;
    }

    public Hang(String maHang, String tenHang, String mausac, int soLuong, Double gia, byte[] hinhanh) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.mausac = mausac;
        SoLuong = soLuong;
        this.gia = gia;
        this.hinhanh = hinhanh;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSize41() {
        return size41;
    }

    public void setSize41(int size41) {
        this.size41 = size41;
    }

    public int getSize42() {
        return size42;
    }

    public void setSize42(int size42) {
        this.size42 = size42;
    }

    public int getSize43() {
        return size43;
    }

    public void setSize43(int size43) {
        this.size43 = size43;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
