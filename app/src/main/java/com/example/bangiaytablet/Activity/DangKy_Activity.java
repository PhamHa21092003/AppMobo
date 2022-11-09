package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bangiaytablet.Class.TaiKhoan;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.util.ArrayList;

public class DangKy_Activity extends AppCompatActivity {

    private DatabaseQuanLy database;
    private EditText tenDK, mk, Nhaplaimk, hotendk;
    private Button dangky, quaylai;
    private Intent intent;
    private ArrayList<TaiKhoan> arrayTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        arrayTaiKhoan = new ArrayList<>();
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);
        anhXa();
        //getdata();

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DangKy_Activity.this, DangNhap_Activity.class);
                startActivity(intent);
            }
        });

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentkDk, mkTK, nhaplaimk, hoten;
                tentkDk = tenDK.getText().toString();
                mkTK = mk.getText().toString();
                hoten = hotendk.getText().toString();
                nhaplaimk = Nhaplaimk.getText().toString();

                if (tentkDk.equals("") || mkTK.equals("") || nhaplaimk.equals("")) {
                    Toast.makeText(DangKy_Activity.this, "Vui lòng điền tất cả thông tin", Toast.LENGTH_LONG).show();
                } else {
                    Cursor existAccount = database.getData("Select TenDN from User where TenDN = ? ", new String[]{tentkDk});
                    if (existAccount != null && existAccount.moveToNext()) {
                        Toast.makeText(DangKy_Activity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                        existAccount.close();
                    } else {
                        if(mkTK.equals(nhaplaimk)){
                            ContentValues values = new ContentValues();
                            values.put("TenDN", tentkDk);
                            values.put("matkhau", mkTK);
                            values.put("Hoten", hoten);
                            values.put("trangthai", 0);
                            database.insertData("User", values);
                            values.clear();
                            intent = new Intent(DangKy_Activity.this, DangNhap_Activity.class);
                            Toast.makeText(DangKy_Activity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            intent.putExtra("userreg", tentkDk);
                            intent.putExtra("passreg", mkTK);
                            startActivity(intent);
                        }
                        else Toast.makeText(DangKy_Activity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
//                    if (nhaplaimk.equals(mkTK)) {
//                        if (arrayTaiKhoan.size() == 0) {
//                            database.QuerryData("INSERT INTO User VALUES(null,'" + tentkDk + "','" + mkTK + "','" + hoten + "',0)");
//                            intent = new Intent(DangKy_Activity.this, DangNhap_Activity.class);
//                            intent.putExtra("userreg", tentkDk);
//                            intent.putExtra("passreg", mkTK);
//                            startActivity(intent);
//                            Toast.makeText(DangKy_Activity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
//                        } else {
//                            for (int i = 0; i < arrayTaiKhoan.size(); i++) {
//                                if (tentkDk.equals(arrayTaiKhoan.get(i).getUsername())) {
//                                    Toast.makeText(DangKy_Activity.this, "Đã tồn tại tài khoản này", Toast.LENGTH_LONG).show();
//                                } else {
//                                    database.QuerryData("INSERT INTO User VALUES(null,'" + tentkDk + "','" + mkTK + "')");
//                                    intent = new Intent(DangKy_Activity.this, DangNhap_Activity.class);
//                                    intent.putExtra("userreg", tentkDk);
//                                    intent.putExtra("passreg", mkTK);
//                                    startActivity(intent);
//                                    Toast.makeText(DangKy_Activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
//                    } else {
//                        Toast.makeText(DangKy_Activity.this, "Mật khẩu không trùng khớp,vui lòng nhập lại", Toast.LENGTH_LONG).show();
//                    }
            }
        });
    }

    private void getdata() {
        Cursor dataTaiKhoan = database.GetData("SELECT * FROM User ");
        arrayTaiKhoan.clear();
        while (dataTaiKhoan.moveToNext()) {
            String TenDN = dataTaiKhoan.getString(1);
            String MatKhau = dataTaiKhoan.getString(2);
            int id = dataTaiKhoan.getInt(0);
            arrayTaiKhoan.add(new TaiKhoan(id, TenDN, MatKhau));
        }
    }

    public void anhXa() {
        tenDK = findViewById(R.id.editTextTenDK);
        mk = findViewById(R.id.editTextMatKhauDK);
        Nhaplaimk = findViewById(R.id.editTextNhapLaiMatKhauDk);
        dangky = findViewById(R.id.btnDangKyTK);
        quaylai = findViewById(R.id.btnQuayLai);
        hotendk = findViewById(R.id.editTextHoTenDK);

    }
}