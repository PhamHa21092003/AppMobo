package com.example.bangiaytablet.Action;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bangiaytablet.Activity.HangTrongKho_Activity;
import com.example.bangiaytablet.Adapter.Hang_Adapter;
import com.example.bangiaytablet.Adapter.Nhap_Hang_Action_Adapter;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.util.ArrayList;

public class Nhap_Hang_Action extends AppCompatActivity {

    DatabaseQuanLy database;
    Nhap_Hang_Action_Adapter adapter;
    ArrayList<Hang> arrayList;
    EditText timkiemsp;
    ListView lv;
    Intent intent;
    String nameAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_hang_action);

        arrayList = new ArrayList<>();
        lv = findViewById(R.id.lvloaigiayNhap);
        timkiemsp = findViewById(R.id.edtTimKiemNhapHang);
        adapter = new Nhap_Hang_Action_Adapter(this, R.layout.dong_nhap_hang, arrayList);
        lv.setAdapter(adapter);
        //Tao DB
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);
        nameAccount = database.getNameAccount();
        hienthiDL();

        ImageView themSpmoi = findViewById(R.id.imageViewThemSPMoi);
        themSpmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Nhap_Hang_Action.this, Them_SP_Moi_Action.class);
                startActivity(intent);
            }
        });

        timkiemsp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String NDTIM = timkiemsp.getText().toString().trim();
                if (TextUtils.isEmpty(NDTIM)) {
                    Cursor dataHang = database.GetData("SELECT * FROM Hang");
                    arrayList.clear();
                    while (dataHang.moveToNext()) {
                        if (nameAccount.equals(dataHang.getString(10))) {
                            int SL = dataHang.getInt(2);
                            String TenHang = dataHang.getString(1);
                            String MaHang = dataHang.getString(0);
                            String MauSac = dataHang.getString(5);
                            Double Gia = dataHang.getDouble(3);
                            arrayList.add(new Hang(MaHang, TenHang, MauSac, SL, Gia, dataHang.getBlob(9)));
                        }
                    }
                    adapter.notifyDataSetChanged();
                    adapter = new Nhap_Hang_Action_Adapter(Nhap_Hang_Action.this, R.layout.dong_nhap_hang, arrayList);
                    lv.setAdapter(adapter);
                    return;
                }


                Cursor dataHang = database.GetData("SELECT * FROM Hang WHERE MAHANG Like '%" + NDTIM + "%' or TENlOAIGIAY Like '%" + NDTIM + "%' ");
                arrayList.clear();
                while (dataHang.moveToNext()) {
                    if (nameAccount.equals(dataHang.getString(10))) {
                        int SL = dataHang.getInt(2);
                        String TenHang = dataHang.getString(1);
                        String MaHang = dataHang.getString(0);
                        String MauSac = dataHang.getString(5);
                        Double Gia = dataHang.getDouble(3);
                        arrayList.add(new Hang(MaHang, TenHang, MauSac, SL, Gia, dataHang.getBlob(9)));
                    }
                }
                adapter.notifyDataSetChanged();
                adapter = new Nhap_Hang_Action_Adapter(Nhap_Hang_Action.this, R.layout.dong_nhap_hang, arrayList);
                lv.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void hienthiDL() {
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        arrayList.clear();
        while (dataHang.moveToNext()) {
            if (nameAccount.equals(dataHang.getString(10))) {
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
        }
        adapter.notifyDataSetChanged();
    }
}