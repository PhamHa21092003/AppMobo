package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bangiaytablet.Action.Xuat_Hang_Action;
import com.example.bangiaytablet.Adapter.Nhap_Hang_Activity_Adapter;
import com.example.bangiaytablet.Adapter.Xuat_Hang_Activity_Adapter;
import com.example.bangiaytablet.Class.ChiTiethoaDonXuat;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Class.HoaDonNhap;
import com.example.bangiaytablet.Class.HoaDonXuat;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class XuatHang_Activity extends AppCompatActivity {
    DatabaseQuanLy database;
    ArrayList<HoaDonXuat> arrayList;
    Xuat_Hang_Activity_Adapter adapter;
    ArrayList<ChiTiethoaDonXuat> arrayListChitietHoaDonXuat;
    ArrayList<Hang> arrayListHang;
    TextView maHDXuat, nguoiXuat, nguoiMua, ngaytaoHD, tongtiencuahoadon;
    ImageView imgvThemSp;
    Double tongtienhientai = 0.0;
    Intent intent;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuat_hang);

        lv = findViewById(R.id.lvNhungMatHangCotrongHoaDonxuat);
        arrayList = new ArrayList<>();
        arrayListChitietHoaDonXuat = new ArrayList<>();
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);
        adapter = new Xuat_Hang_Activity_Adapter(this, R.layout.dong_cac_san_pham_dang_xuat_trong_hoa_don, arrayListChitietHoaDonXuat);
        lv.setAdapter(adapter);
        arrayListHang = new ArrayList<>();

        anhxa();
        getdataHoaDonXuat();
        getdataChiTietHoaDonXuat();
        int doDaiArray = arrayList.size();
        maHDXuat.setText("Mã hóa đơn: " + arrayList.get(doDaiArray - 1).getMaHoaDon());
        nguoiXuat.setText("Người xuất: " + arrayList.get(doDaiArray - 1).getNguoiXuat());
        nguoiMua.setText("Người mua: " + arrayList.get(doDaiArray - 1).getNguoiMua());
        ngaytaoHD.setText("Ngày nhập: " + arrayList.get(doDaiArray - 1).getNgayTaoHoaDon());

        for (int i = 0; i < arrayListChitietHoaDonXuat.size(); i++) {
            tongtienhientai += arrayListChitietHoaDonXuat.get(i).getGiaXuat() * arrayListChitietHoaDonXuat.get(i).getSoLuongXuat();
        }
        tongtiencuahoadon.setText("Tổng tiền: " + tongtienhientai);

        imgvThemSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(XuatHang_Activity.this, Xuat_Hang_Action.class);
                startActivity(intent);
            }
        });
    }

    private void getdataChiTietHoaDonXuat() {
        Cursor dataChiTietHoaDonNhap = database.GetData("SELECT * FROM ChiTietHoaDonXuat Where maHDXuat='" + arrayList.get(arrayList.size() - 1).getMaHoaDon() + "'");
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        String mahang, tenSP, MauSac;
        int soLuongXuat, size;
        byte[] anhsp = null;
        Double giaXuat;
        arrayListChitietHoaDonXuat.clear();
        while (dataChiTietHoaDonNhap.moveToNext()) {
            mahang = dataChiTietHoaDonNhap.getString(1);
            soLuongXuat = dataChiTietHoaDonNhap.getInt(2);
            giaXuat = dataChiTietHoaDonNhap.getDouble(3);
            size = dataChiTietHoaDonNhap.getInt(4);
            tenSP = MauSac = "";
            while (dataHang.moveToNext()) {
                String mahangtrongkho = dataHang.getString(0);
                if (mahangtrongkho.equalsIgnoreCase(mahang)) {
                    tenSP = dataHang.getString(1);
                    MauSac = dataHang.getString(5);
                    anhsp = dataHang.getBlob(9);
                }
            }
            dataHang.moveToFirst();
            arrayListChitietHoaDonXuat.add(new ChiTiethoaDonXuat(soLuongXuat, size, mahang, tenSP, MauSac, giaXuat, anhsp));
        }
        adapter.notifyDataSetChanged();
    }

    private void getdataHoaDonXuat() {
        Cursor dataHoaDonXuat = database.GetData("SELECT * FROM HoaDonXuat");
        arrayList.clear();
        while (dataHoaDonXuat.moveToNext()) {
            int maHDXuat = dataHoaDonXuat.getInt(0);
            String ngayTaoHD = dataHoaDonXuat.getString(1);
            String nguoiXuat = dataHoaDonXuat.getString(2);
            String nguoiMua = dataHoaDonXuat.getString(3);
            arrayList.add(new HoaDonXuat(ngayTaoHD, nguoiXuat, nguoiMua, maHDXuat));
        }

    }

    private void anhxa() {
        maHDXuat = findViewById(R.id.mahoadonxuat);
        nguoiXuat = findViewById(R.id.tvNguoiLapHoaDonxuat);
        nguoiMua = findViewById(R.id.tvNguoiMua);
        ngaytaoHD = findViewById(R.id.NgaytaoHoaDonxuat);
        imgvThemSp = findViewById(R.id.imageViewThemSPVaoHDxuat);
        tongtiencuahoadon = findViewById(R.id.tvTongTientrongHoaDonxuat);
    }
}