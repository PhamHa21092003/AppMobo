package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bangiaytablet.Adapter.DS_SanPhamNhap_ChiTietHDNhap_Adapter;
import com.example.bangiaytablet.Adapter.DS_SanPhamXuat_ChiTietHoaDonXuat_Adapter;
import com.example.bangiaytablet.Class.ChiTiethoaDonXuat;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.util.ArrayList;

public class DSSP_ChiTietHoaDonXuat_Activity extends AppCompatActivity {

    DatabaseQuanLy database;
    ArrayList<Hang> arrayListHang;
    ArrayList<ChiTiethoaDonXuat> arrayListChiTietXuatHang;
    DS_SanPhamXuat_ChiTietHoaDonXuat_Adapter adapter;
    String mahdNhan;
    int mahd;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dssp_chi_tiet_hoa_don_xuat);

        lv = findViewById(R.id.lv_DSSP_ChiTietHDXuat);
        arrayListHang = new ArrayList<>();
        arrayListChiTietXuatHang= new ArrayList<>();
        adapter = new DS_SanPhamXuat_ChiTietHoaDonXuat_Adapter(this,R.layout.dong_ds_hang_cua_hoa_don_xuat,arrayListChiTietXuatHang);
        lv.setAdapter(adapter);

        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        mahdNhan=getIntent().getStringExtra("maHDXuat");
        mahd= Integer.parseInt(mahdNhan);
        hienthiDL();
    }

    private void hienthiDL() {
        Cursor dataChiTietHoaDonXuat = database.GetData("SELECT * FROM ChiTietHoaDonXuat Where maHDXuat='"+mahd+"'");
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        String mahang,tenSP,MauSac,thuonghieu="";
        int soLuongNhap,size;
        byte[] anhsp=null;
        Double gianhap;
        arrayListChiTietXuatHang.clear();
        while (dataChiTietHoaDonXuat.moveToNext()) {
            mahang=dataChiTietHoaDonXuat.getString(1);
            soLuongNhap=dataChiTietHoaDonXuat.getInt(2);
            gianhap=dataChiTietHoaDonXuat.getDouble(3);
            size=dataChiTietHoaDonXuat.getInt(4);
            tenSP=MauSac="";
            while (dataHang.moveToNext()){
                String mahangtrongkho=dataHang.getString(0);
                if(mahangtrongkho.equalsIgnoreCase(mahang)){
                    tenSP=dataHang.getString(1);
                    MauSac=dataHang.getString(5);
                    anhsp=dataHang.getBlob(9);
                    thuonghieu=dataHang.getString(4);
                    break;
                }
            }
            dataHang.moveToFirst();
            arrayListChiTietXuatHang.add(new ChiTiethoaDonXuat(mahd,soLuongNhap,size,mahang,tenSP,MauSac,thuonghieu,gianhap,anhsp));
        }
        adapter.notifyDataSetChanged();
    }
}