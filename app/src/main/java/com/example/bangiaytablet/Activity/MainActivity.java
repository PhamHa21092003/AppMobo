package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiaytablet.Action.Nhap_Hang_Action;
import com.example.bangiaytablet.Class.TaiKhoan;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    DatabaseQuanLy database;
    ArrayList<TaiKhoan> arrayListTaiKhoan, arrayListTaiKhoanOnline;
    String hoTenNguoiNhap, tenTaiKhoanDangNhap;
    RelativeLayout hangtrongkho, nhaphang, xuathang, hoadonnhap, hoadonxuat;
    Button logOut;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayListTaiKhoanOnline = new ArrayList<>();
        //Tao DB
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        //getTaiKhoanOnline();

        Cursor cursor = database.getData("select TenDN, Hoten from User where Trangthai=?", new String[]{"1"});
        if(cursor != null && cursor.moveToNext()){
            hoTenNguoiNhap = cursor.getString(1);
            tenTaiKhoanDangNhap = cursor.getString(0);
            cursor.close();
        }
        else {
            cursor.close();
            intent = new Intent(MainActivity.this, DangNhap_Activity.class);
            startActivity(intent);
        }


        hangtrongkho = findViewById(R.id.hangtrongkho);
        nhaphang = findViewById(R.id.nhaphang);
        xuathang = findViewById(R.id.xuathang);
        hoadonnhap = findViewById(R.id.hoadonnhap);
        hoadonxuat = findViewById(R.id.hoadonxuat);
        logOut = findViewById(R.id.logOut);


        TextView userDN = findViewById(R.id.userDN);
        userDN.setText(hoTenNguoiNhap);


        arrayListTaiKhoan = new ArrayList<>();


        //Tao bang
        database.QuerryData("CREATE TABLE IF NOT EXISTS HoaDonNhap (maHD INTEGER PRIMARY KEY AUTOINCREMENT,NgayTao VARCHAR(50),NguoiNhap VARCHAR(50),NhaCungCap VARCHAR(50), TenDN VARCHAR(50))");
        database.QuerryData("CREATE TABLE IF NOT EXISTS HoaDonXuat (maHDXuat INTEGER PRIMARY KEY AUTOINCREMENT,NgayTao VARCHAR(50),NguoiXuat VARCHAR(50),NguoiMua VARCHAR(50), TenDN VARCHAR(50))");
        database.QuerryData("CREATE TABLE IF NOT EXISTS Hang (MAHANG varchar(50) PRIMARY KEY, TENlOAIGIAY VARCHAR(200),TongSl INTEGER,Gia Double,HangSX VARCHAR(200),MauSac varchar(50),Size41 INTEGER,Size42 INTEGER,Size43 INTEGER,hinhanh BLOB, TenDN VARCHAR(50))");
        database.QuerryData("CREATE TABLE IF NOT EXISTS ChiTietHoaDonNhap (maHDNhap INTEGER ,maHangNhap VARCHAR(50),SlNhap INTEGER,GiaNhap Double,Size INTEGER, TenDN VARCHAR(50))");
        database.QuerryData("CREATE TABLE IF NOT EXISTS ChiTietHoaDonXuat (maHDXuat INTEGER ,maHangXuat VARCHAR(50),SlXuat INTEGER,GiaXuat Double,Size INTEGER, TenDN VARCHAR(50))");

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, DangNhap_Activity.class);
                ContentValues value = new ContentValues();
                value.put("Trangthai", 0);
                database.updateData("User", value, "TenDN=?", new String[] {tenTaiKhoanDangNhap});
                startActivity(intent);
            }
        });

        hangtrongkho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, HangTrongKho_Activity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Hàng trong kho", Toast.LENGTH_LONG).show();
            }
        });

        nhaphang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_themhoadon_nhap);
                dialog.show();


                EditText ngayTaoHoaDon = dialog.findViewById(R.id.editTextNgayLapHoaDonNhap);
                EditText ncc = dialog.findViewById(R.id.editTextNhaCungCap);


                Button btnTaoHoaDon = dialog.findViewById(R.id.buttonThemHoaDonNhap);
                Button btnHuyTaoHoaDon = dialog.findViewById(R.id.buttonHuyTaoHoaDonNhap);

                getTaiKhoan();


                btnHuyTaoHoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnTaoHoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ngayTaoNhap = ngayTaoHoaDon.getText().toString().trim();
                        String NhaCungCap = ncc.getText().toString().trim();

                        Date dateNhap = null, datehientai = null;
                        Boolean isDate = false;
                        if (TextUtils.isEmpty(ngayTaoNhap)) {
                            Toast.makeText(MainActivity.this, "Hãy nhập ngày tạo hóa đơn", Toast.LENGTH_SHORT).show();
                        }
                        if (ngayTaoNhap.split("\\/").length == 3) {
                            String ngaycat[] = ngayTaoNhap.split("\\/");
                            Integer ngay = Integer.parseInt(ngaycat[0]);
                            Integer thang = Integer.parseInt(ngaycat[1]);
                            if (ngay > 31 || thang > 12) {
                                Toast.makeText(MainActivity.this, "Ngày nhập theo dạng dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    dateNhap = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(ngayTaoNhap);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                ;
                                datehientai = new Date();

                                if (dateNhap.after(datehientai)) {
                                    Toast.makeText(MainActivity.this, "Ngày nhập không được quá ngày hiện tại", Toast.LENGTH_SHORT).show();
                                } else {
                                   // database.QuerryData("INSERT INTO HoaDonNhap VALUES(null,'" + ngayTaoNhap + "','" + hoTenNguoiNhap + "','" + NhaCungCap + "'," + "'" + tenTaiKhoanDangNhap + "')");
                                    ContentValues values = database.valuesTableHoaDonNhap(ngayTaoNhap, hoTenNguoiNhap, NhaCungCap);
                                    database.insertData("HoaDonNhap", values);
                                    Toast.makeText(MainActivity.this, "Tạo hóa đơn thành công", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(MainActivity.this, Nhap_Hang_Activity.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
            }
        });


        xuathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_themhoadon_xuat);
                dialog.show();

                EditText ngayTaoHoaDon = dialog.findViewById(R.id.editTextNgayLapHoaDonXuat);
                EditText nguoiMua = dialog.findViewById(R.id.editTextNguoiMua);
                Button btnTaoHoaDon = dialog.findViewById(R.id.buttonThemHoaDonXuat);
                Button btnHuyTaoHoaDon = dialog.findViewById(R.id.buttonHuyTaoHoaDonXuat);

                btnHuyTaoHoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnTaoHoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ngayTaoXuat = ngayTaoHoaDon.getText().toString().trim();
                        String nguoimua = nguoiMua.getText().toString().trim();
                        Date dateXuat = null, datehientai = null;
                        if (TextUtils.isEmpty(ngayTaoXuat)) {
                            Toast.makeText(MainActivity.this, "Hãy nhập ngày tạo hóa đơn", Toast.LENGTH_LONG).show();

                        }

                        if (ngayTaoXuat.split("\\/").length == 3) {
                            String ngaycat[] = ngayTaoXuat.split("\\/");
                            Integer ngay = Integer.parseInt(ngaycat[0]);
                            Integer thang = Integer.parseInt(ngaycat[1]);
                            if (ngay > 31 || thang > 12) {
                                Toast.makeText(MainActivity.this, "Ngày nhập theo dạng dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    dateXuat = new SimpleDateFormat("dd/MM/yyyy").parse(ngayTaoXuat);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                ;
                                datehientai = new Date();


                                if (dateXuat.after(datehientai)) {
                                    Toast.makeText(MainActivity.this, "Ngày nhập không được quá ngày hiện tại", Toast.LENGTH_SHORT).show();
                                } else {
                                    database.QuerryData("INSERT INTO HoaDonXuat VALUES(null,'" + ngayTaoXuat + "','" + hoTenNguoiNhap + "','" + nguoimua + "')");
                                    Toast.makeText(MainActivity.this, "Tạo hóa đơn thành công", Toast.LENGTH_LONG).show();
                                    intent = new Intent(MainActivity.this, XuatHang_Activity.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                }


                            }
                        }


                    }
                });
                Toast.makeText(MainActivity.this, "Xuất hàng", Toast.LENGTH_SHORT).show();
            }
        });

        hoadonnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, DSHD_ChiTietHoaDonNhap_Activity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Hóa đơn nhập", Toast.LENGTH_SHORT).show();
            }
        });

        hoadonxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, DSHD_ChiTietHoaDonXuat_Acivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Hóa đơn xuất", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getTaiKhoan() {
        Cursor dataTaiKhoan = database.GetData("SELECT * FROM User ");
        arrayListTaiKhoan.clear();
        while (dataTaiKhoan.moveToNext()) {
            int id = dataTaiKhoan.getInt(0);
            String TenDN = dataTaiKhoan.getString(1);
            String MatKhau = dataTaiKhoan.getString(2);
            String hoten = dataTaiKhoan.getString(3);
            arrayListTaiKhoan.add(new TaiKhoan(id, TenDN, MatKhau, hoten));
        }
    }


    private void getTaiKhoanOnline() {
        Cursor dataTaiKhoanOn = database.GetData("SELECT HoTen FROM User where Trangthai=1 ");
        arrayListTaiKhoanOnline.clear();
        while (dataTaiKhoanOn.moveToNext()) {
            String hoten = dataTaiKhoanOn.getString(0);
            arrayListTaiKhoanOnline.add(new TaiKhoan(hoten));
        }
    }
}