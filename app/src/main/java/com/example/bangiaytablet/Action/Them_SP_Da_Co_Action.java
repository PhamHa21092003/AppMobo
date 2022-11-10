package com.example.bangiaytablet.Action;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Class.HoaDonNhap;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Them_SP_Da_Co_Action extends AppCompatActivity {
    Intent intent;
    DatabaseQuanLy database;
    EditText SlSpThem, GiaSpThem, slSize41, slSize42, slSize43;
    TextView maSPThem, tenSPThem, mausac, ThuongHieu;
    ImageView anhSP;
    int slCu, slTong;
    String tensp, masp, slsp;
    String tenspnhan, maspnhan, thuonghieunhan, maunhan, size41nhan, size42nhan, size43nhan, tongSLnhan;
    Button btnThem, btnHuy;
    ArrayList<Hang> arrayList;
    ArrayList<HoaDonNhap> arrayListHoaDonNhap;
    ArrayList<ChitietHoaDonNhap> arrayListChiTietHoaDonNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sp_da_co_action);

        arrayList = new ArrayList<>();
        arrayListHoaDonNhap = new ArrayList<>();
        arrayListChiTietHoaDonNhap = new ArrayList<>();

        maspnhan = getIntent().getStringExtra("maSpThem");
        tenspnhan = getIntent().getStringExtra("tenSpThem");
        thuonghieunhan = getIntent().getStringExtra("tenthuonghieu");
        maunhan = getIntent().getStringExtra("MauSac");
        size41nhan = getIntent().getStringExtra("Size41");
        size42nhan = getIntent().getStringExtra("Size42");
        size43nhan = getIntent().getStringExtra("Size43");
        tongSLnhan = getIntent().getStringExtra("tongSL");

        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        //Tao bang
        database.QuerryData("CREATE TABLE IF NOT EXISTS ChiTietHoaDonNhap (maHDNhap INTEGER ,maHangNhap VARCHAR(50),SlNhap INTEGER,GiaNhap Double,Size INTEGER)");


        maSPThem = findViewById(R.id.TextMaSanPhamThem);
        tenSPThem = findViewById(R.id.TextMaSanPhamThem);
        GiaSpThem = findViewById(R.id.editTextGiaSanPhamThem);
        btnThem = findViewById(R.id.btnThemSPMoiVaoKho);
        btnHuy = findViewById(R.id.btnHuyThemSPMoi);
        slSize41 = findViewById(R.id.edtsls41Nhap);
        slSize42 = findViewById(R.id.edtsls42Nhap);
        slSize43 = findViewById(R.id.edtsls43Nhap);
        ThuongHieu = findViewById(R.id.TextHangSanPhamThem);
        mausac = findViewById(R.id.TextMauSanPhamThem);
        anhSP = findViewById(R.id.imgSpThem);


        getdata();
        getdataHoaDonNhap();

        maSPThem.setText("Mã sản phẩm: " + maspnhan);
        tenSPThem.setText("Tên sản phẩm: " + tenspnhan);
        ThuongHieu.setText("Thương hiệu: " + thuonghieunhan);
        mausac.setText("Màu sắc: " + maunhan);


        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getMaHang().equalsIgnoreCase(maspnhan)) {
                byte[] hinhAnh = arrayList.get(i).getHinhanh();
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                anhSP.setImageBitmap(bitmap);
            }
        }

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Them_SP_Da_Co_Action.this, Nhap_Hang_Action.class);
                startActivity(intent);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maHangThem, tenHangThem, giaSPNhap, thuonghieusp, mauSac;
                int slhangThemINT, size41nhap, size42nhap, size43nhap, tongsoluongmoi, size41moi, size42moi, size43moi;
                Double giaHagThemDouble, giaban;
                boolean slDangSoNguyen = false;


                try {
                    Integer.parseInt(slSize41.getText().toString().trim());
                    Integer.parseInt(slSize42.getText().toString().trim());
                    Integer.parseInt(slSize43.getText().toString().trim());
                    slDangSoNguyen = true;
                } catch (NumberFormatException e) {

                    slDangSoNguyen = false;
                }

                if (
                        TextUtils.isEmpty(slSize43.getText().toString().trim()) ||
                                TextUtils.isEmpty(slSize41.getText().toString().trim()) ||
                                TextUtils.isEmpty(slSize42.getText().toString().trim())) {
                    Toast.makeText(Them_SP_Da_Co_Action.this, "Hãy nhập đủ thông tin", Toast.LENGTH_LONG).show();
                } else {
                    if (slDangSoNguyen) {
                        int tonatai = 0;
                        size41nhap = Integer.parseInt(slSize41.getText().toString().trim());
                        size42nhap = Integer.parseInt(slSize42.getText().toString().trim());
                        size43nhap = Integer.parseInt(slSize43.getText().toString().trim());
                        slhangThemINT = size41nhap + size42nhap + size43nhap;
                        tongsoluongmoi = Integer.parseInt(tongSLnhan) + slhangThemINT;
                        size41moi = size41nhap + Integer.parseInt(size41nhan);
                        size42moi = size42nhap + Integer.parseInt(size42nhan);
                        size43moi = size43nhap + Integer.parseInt(size43nhan);


                        giaHagThemDouble = Double.parseDouble(GiaSpThem.getText().toString().trim());

                        Double lai = giaHagThemDouble / 10;

                        giaban = giaHagThemDouble + lai;


                        for (int i = 0; i < arrayList.size(); i++) {
                            if (maspnhan.equalsIgnoreCase(arrayList.get(i).getMaHang().toString().trim())) {
                                tonatai = 1;
                            }
                        }


                        if (tonatai == 0) {
                            Toast.makeText(Them_SP_Da_Co_Action.this, "Chưa tồn tại mã này", Toast.LENGTH_SHORT).show();
                        } else {
                            int maHD = arrayListHoaDonNhap.get(arrayListHoaDonNhap.size() - 1).getMaHoaDon();


                            database.QuerryData("Update Hang Set TongSl='" + tongsoluongmoi + "',Gia='" + giaban + "',Size41='" + size41moi + "',Size42='" + size42moi + "',Size43='" + size43moi + "' WHERE MAHANG='" + maspnhan + "'");
                            if (size41nhap > 0) {
                                database.QuerryData("INSERT INTO ChiTietHoaDonNhap VALUES('" + maHD + "','" + maspnhan + "','" + size41nhap + "','" + giaHagThemDouble + "',41)");
                            }
                            if (size42nhap > 0) {
                                database.QuerryData("INSERT INTO ChiTietHoaDonNhap VALUES('" + maHD + "','" + maspnhan + "','" + size42nhap + "','" + giaHagThemDouble + "',42)");
                            }
                            if (size43nhap > 0) {
                                database.QuerryData("INSERT INTO ChiTietHoaDonNhap VALUES('" + maHD + "','" + maspnhan + "','" + size43nhap + "','" + giaHagThemDouble + "',43)");
                            }

                            Toast.makeText(Them_SP_Da_Co_Action.this, "Them Sp moi thành công", Toast.LENGTH_LONG).show();
                            intent = new Intent(Them_SP_Da_Co_Action.this, Nhap_Hang_Action.class);
                            startActivity(intent);
                        }


                    } else {
                        Toast.makeText(Them_SP_Da_Co_Action.this, "Số lượng phải là dạng số nguyên", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    private void getdata() {
        Cursor dataHang = database.GetData("SELECT * FROM Hang ");
        arrayList.clear();
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
            arrayList.add(new Hang(MaHang, TenHang, hangSX, MauSac, SLSize41, SLSize42, SLSize43, SL, Gia, dataHang.getBlob(9)));
        }
        dataHang.close();
    }

    private void getdataHoaDonNhap() {
        Cursor dataHoaDonNhap = database.GetData("SELECT * FROM HoaDonNhap ");
        arrayListHoaDonNhap.clear();
        while (dataHoaDonNhap.moveToNext()) {
            int maHD = dataHoaDonNhap.getInt(0);
            String ngayNhap = dataHoaDonNhap.getString(1);
            arrayListHoaDonNhap.add(new HoaDonNhap(ngayNhap, maHD));
        }
        dataHoaDonNhap.close();
    }
}