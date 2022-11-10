package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bangiaytablet.Adapter.DS_SanPhamNhap_ChiTietHDNhap_Adapter;
import com.example.bangiaytablet.Adapter.LSNhapHangCuaSP_Adapter;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class LSCacLanNhapCuaSP_Activity extends AppCompatActivity {
    DatabaseQuanLy database;
    ArrayList<Hang> arrayListHang;
    ArrayList<ChitietHoaDonNhap> arrayListChiTietNhapHang;
    LSNhapHangCuaSP_Adapter adapter;
    int maHD;
    String maHangNhan;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lscac_lan_nhap_cua_sp);

        lv = findViewById(R.id.lvLSNhapHangCuaSP);
        arrayListHang = new ArrayList<>();
        arrayListChiTietNhapHang = new ArrayList<>();
        adapter = new LSNhapHangCuaSP_Adapter(this, R.layout.dong_ds_hang_cua_hoa_don_nhap, arrayListChiTietNhapHang);
        lv.setAdapter(adapter);

        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        maHangNhan = getIntent().getStringExtra("maHDHang");

        hienthiDL();
    }

    private void hienthiDL() {
        Cursor dataChiTietHoaDonNhap = database.GetData("SELECT * FROM ChiTietHoaDonNhap Where maHangNhap='" + maHangNhan + "' ORDER BY maHDNhap DESC");
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        String mahang, tenSP, MauSac, thuonghieu = "";
        int soLuongNhap, size, maHoaDon;
        byte[] anhsp = null;
        Double gianhap;
        arrayListChiTietNhapHang.clear();
        while (dataChiTietHoaDonNhap.moveToNext()) {
            maHoaDon = dataChiTietHoaDonNhap.getInt(0);
            soLuongNhap = dataChiTietHoaDonNhap.getInt(2);
            gianhap = dataChiTietHoaDonNhap.getDouble(3);
            size = dataChiTietHoaDonNhap.getInt(4);
            tenSP = MauSac = "";
            while (dataHang.moveToNext()) {
                String mahangtrongkho = dataHang.getString(0);
                if (mahangtrongkho.equalsIgnoreCase(maHangNhan)) {
                    tenSP = dataHang.getString(1);
                    MauSac = dataHang.getString(5);
                    anhsp = dataHang.getBlob(9);
                    thuonghieu = dataHang.getString(4);
                    break;
                }
            }
            dataHang.moveToFirst();
            arrayListChiTietNhapHang.add(new ChitietHoaDonNhap(maHoaDon, soLuongNhap, size, maHangNhan, tenSP, MauSac, thuonghieu, gianhap, anhsp));
        }

        adapter.notifyDataSetChanged();
    }
}