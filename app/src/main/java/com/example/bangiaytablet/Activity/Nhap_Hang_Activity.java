package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiaytablet.Action.Nhap_Hang_Action;
import com.example.bangiaytablet.Adapter.Hang_Adapter;
import com.example.bangiaytablet.Adapter.Nhap_Hang_Activity_Adapter;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Class.HoaDonNhap;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.util.ArrayList;

public class Nhap_Hang_Activity extends AppCompatActivity {

    DatabaseQuanLy database;
    ArrayList<HoaDonNhap> arrayList;
    Nhap_Hang_Activity_Adapter adapter;
    ArrayList<ChitietHoaDonNhap> arrayListChitietHoaDonNhapl;
    ArrayList<Hang> arrayListHang;
    TextView maHDNhap, nguoiNhap, NhaCC, ngaytaoHD, tongtiencuahpadon;
    ImageView imgvThemSp;
    Double tongtienhientai = 0.0;
    Intent intent;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_hang);

        lv = findViewById(R.id.lvNhungMatHangCotrongHoaDon);
        arrayList = new ArrayList<>();
        arrayListChitietHoaDonNhapl = new ArrayList<>();
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);
        adapter = new Nhap_Hang_Activity_Adapter(this, R.layout.dong_cac_san_pham_dang_nhap_trong_hoa_don, arrayListChitietHoaDonNhapl);
        lv.setAdapter(adapter);
        arrayListHang = new ArrayList<>();

        anhxa();
        getdataHoaDonNhap();
        getdataChiTietHoaDonNhap();
        int doDaiArray = arrayList.size();
        maHDNhap.setText("Mã hóa đơn: " + arrayList.get(doDaiArray - 1).getMaHoaDon());
        nguoiNhap.setText("Người nhập: " + arrayList.get(doDaiArray - 1).getNguoiNhap());
        NhaCC.setText("Nhà cung cấp: " + arrayList.get(doDaiArray - 1).getNcc());
        ngaytaoHD.setText("Ngày nhập: " + arrayList.get(doDaiArray - 1).getNgayTaoHoaDon());

        for (int i = 0; i < arrayListChitietHoaDonNhapl.size(); i++) {
            tongtienhientai += arrayListChitietHoaDonNhapl.get(i).getGiaNhap() * arrayListChitietHoaDonNhapl.get(i).getSoLuongNhap();
        }
        tongtiencuahpadon.setText("Tổng tiền: " + tongtienhientai);
        imgvThemSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Nhap_Hang_Activity.this, Nhap_Hang_Action.class);
                startActivity(intent);
            }
        });
    }

    private void getdataHoaDonNhap() {
        Cursor dataHoaDonNhap = database.GetData("SELECT * FROM HoaDonNhap");
        arrayList.clear();
        while (dataHoaDonNhap.moveToNext()) {
            int maHDNhap = dataHoaDonNhap.getInt(0);
            String ngayTaoHD = dataHoaDonNhap.getString(1);
            String nguoiNhap = dataHoaDonNhap.getString(2);
            String nhacungcap = dataHoaDonNhap.getString(3);
            arrayList.add(new HoaDonNhap(ngayTaoHD, nguoiNhap, nhacungcap, maHDNhap));
        }
    }

    private void anhxa() {
        maHDNhap = findViewById(R.id.mahoadonnhap);
        nguoiNhap = findViewById(R.id.tvNguoiLapHoaDonNhap);
        NhaCC = findViewById(R.id.tvNhaCCHoaDOnNhap);
        ngaytaoHD = findViewById(R.id.NgaytaoHoaDonnhap);
        tongtiencuahpadon = findViewById(R.id.tvTongTientrongHoaDonNhap);
        imgvThemSp = findViewById(R.id.imageViewThemSPVaoHDNhap);
    }

    private void getdataChiTietHoaDonNhap() {
        Cursor dataChiTietHoaDonNhap = database.GetData("SELECT * FROM ChiTietHoaDonNhap Where maHDNhap='" + arrayList.get(arrayList.size() - 1).getMaHoaDon() + "'");
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        String mahang, tenSP, MauSac;
        int soLuongNhap, size;
        byte[] anhsp = null;
        Double gianhap;
        arrayListChitietHoaDonNhapl.clear();
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
                }
            }
            dataHang.moveToFirst();
            arrayListChitietHoaDonNhapl.add(new ChitietHoaDonNhap(soLuongNhap, size, mahang, tenSP, MauSac, gianhap, anhsp));
        }
        adapter.notifyDataSetChanged();
    }


    public void DialogXoa(String tenSp, String maHang, int Size, int soluongnhap) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_xoa);
        dialog.show();

        TextView textXacNhanSPXoa = dialog.findViewById(R.id.XacNhanXoaTXT);

        Button btnXoa = dialog.findViewById(R.id.buttonXacNhanXoa);
        Button btnHuyXoa = dialog.findViewById(R.id.buttonHuy);


        Cursor dataHang = database.GetData("SELECT * FROM Hang WHERE MAHANG='" + maHang + "'");
        arrayListHang.clear();
        while (dataHang.moveToNext()) {
            int SL = dataHang.getInt(2);
            String TenHang = dataHang.getString(1);
            String MaHang = dataHang.getString(0);
            String MauSac = dataHang.getString(5);
            String hangSX = dataHang.getString(4);
            int SLSize41 = dataHang.getInt(6);
            int SLSize42 = dataHang.getInt(7);
            int SLSize43 = dataHang.getInt(8);
            Double Gia = dataHang.getDouble(3);

            arrayListHang.add(new Hang(MaHang, TenHang, hangSX, MauSac, SLSize41, SLSize42, SLSize43, SL, Gia, dataHang.getBlob(9)));
        }


        textXacNhanSPXoa.setText("Bạn có cắc chắn muốn xóa " + tenSp + "?");


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.QuerryData("Delete from ChiTietHoaDonNhap WHERE maHDNhap='" + arrayList.get(arrayList.size() - 1).getMaHoaDon() + "' AND maHangNhap='" + maHang + "' AND Size='" + Size + "'");
                for (int i = 0; i < arrayListHang.size(); i++) {
                    if (maHang.equalsIgnoreCase(arrayListHang.get(i).getMaHang())) {
                        int soluongsauxoa = arrayListHang.get(i).getSoLuong() - soluongnhap;
                        database.QuerryData("Update Hang Set TongSl='" + soluongsauxoa + "' Where MAHANG='" + maHang + "' ");
                        switch (Size) {
                            case 41:
                                int slsize41sauxoa = arrayListHang.get(i).getSize41() - soluongnhap;
                                database.QuerryData("Update Hang Set Size41='" + slsize41sauxoa + "' Where MAHANG='" + maHang + "' ");
                                break;
                            case 42:
                                int slsize42sauxoa = arrayListHang.get(i).getSize42() - soluongnhap;
                                database.QuerryData("Update Hang Set Size42='" + slsize42sauxoa + "' Where MAHANG='" + maHang + "' ");
                                break;
                            case 43:
                                int slsize43sauxoa = arrayListHang.get(i).getSize43() - soluongnhap;
                                database.QuerryData("Update Hang Set Size43='" + slsize43sauxoa + "' Where MAHANG='" + maHang + "' ");
                                break;
                        }
                    }
                }
                Toast.makeText(Nhap_Hang_Activity.this, "Xóa thành công", Toast.LENGTH_LONG).show();
                getdataHoaDonNhap();
                getdataChiTietHoaDonNhap();
                for (int i = 0; i < arrayListChitietHoaDonNhapl.size(); i++) {
                    tongtienhientai += arrayListChitietHoaDonNhapl.get(i).getGiaNhap() * arrayListChitietHoaDonNhapl.get(i).getSoLuongNhap();
                }
                tongtiencuahpadon.setText("Tổng tiền: " + tongtienhientai);
                dialog.dismiss();

            }
        });


        btnHuyXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }

}