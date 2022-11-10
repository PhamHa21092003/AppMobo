package com.example.bangiaytablet.Class;

public class TaiKhoan {
    int id, trangthai;
    String username, password, name;

    public TaiKhoan(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public TaiKhoan(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public TaiKhoan(String name) {
        this.name = name;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
