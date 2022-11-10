package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bangiaytablet.Adapter.DS_SanPhamNhap_ChiTietHDNhap_Adapter;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DSSP_ChiTietHoaDonNhap_Activity extends AppCompatActivity {
    DatabaseQuanLy database;
    ArrayList<Hang> arrayListHang;
    ArrayList<ChitietHoaDonNhap> arrayListChiTietNhapHang;
    DS_SanPhamNhap_ChiTietHDNhap_Adapter adapter;
    String mahdNhan;
    int mahd;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dssp_chi_tiet_hoa_don_nhap);

        lv = findViewById(R.id.lv_DSSP_ChiTietHDNhap);
        arrayListHang = new ArrayList<>();
        arrayListChiTietNhapHang = new ArrayList<>();
        adapter = new DS_SanPhamNhap_ChiTietHDNhap_Adapter(this, R.layout.dong_ds_hang_cua_hoa_don_nhap, arrayListChiTietNhapHang);
        lv.setAdapter(adapter);

        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        mahdNhan = getIntent().getStringExtra("maHDNhap");
        mahd = Integer.parseInt(mahdNhan);
        hienthiDL();

    }

    private void hienthiDL() {
        Cursor dataChiTietHoaDonNhap = database.GetData("SELECT * FROM ChiTietHoaDonNhap Where maHDNhap='" + mahd + "'");
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        String mahang, tenSP, MauSac, thuonghieu = "";
        int soLuongNhap, size;
        byte[] anhsp = null;
        Double gianhap;
        arrayListChiTietNhapHang.clear();
        while (dataChiTietHoaDonNhap.moveToNext()) {
            mahang = dataChiTietHoaDonNhap.getString(1);
            soLuongNhap = dataChiTietHoaDonNhap.getInt(2);
            gianhap = dataChiTietHoaDonNhap.getDouble(3);
            size = dataChiTietHoaDonNhap.getInt(4);
            tenSP = MauSac = "";
            while (dataHang.moveToNext()) {
                String mahangtrongkho = dataHang.getString(0);
                if (mahangtrongkho.equalsIgnoreCase(mahang)) {
                    tenSP = dataHang.getString(1);
                    MauSac = dataHang.getString(5);
                    anhsp = dataHang.getBlob(9);
                    thuonghieu = dataHang.getString(4);
                    break;
                }
            }
            dataHang.moveToFirst();
            arrayListChiTietNhapHang.add(new ChitietHoaDonNhap(mahd, soLuongNhap, size, mahang, tenSP, MauSac, thuonghieu, gianhap, anhsp));
        }

        adapter.notifyDataSetChanged();
    }
}