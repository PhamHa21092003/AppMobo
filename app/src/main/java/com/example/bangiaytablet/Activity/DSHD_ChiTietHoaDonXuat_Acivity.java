package com.example.bangiaytablet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bangiaytablet.Adapter.DS_hoa_don_nhap_Adapter;
import com.example.bangiaytablet.Adapter.DS_hoa_don_xuat_Adapter;
import com.example.bangiaytablet.Class.HoaDonNhap;
import com.example.bangiaytablet.Class.HoaDonXuat;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DSHD_ChiTietHoaDonXuat_Acivity extends AppCompatActivity {
    DatabaseQuanLy database;
    DS_hoa_don_xuat_Adapter adapter;
    ArrayList<HoaDonXuat> arrayList;
    Intent intent;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dshd_chi_tiet_hoa_don_xuat_acivity);

        lv = findViewById(R.id.lv_DSHD_ChitietHDXuat);
        arrayList = new ArrayList<>();
        adapter = new DS_hoa_don_xuat_Adapter(this,R.layout.dong_ds_hd_xuat,arrayList);
        lv.setAdapter(adapter);


        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        hienthiDL();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String maHD=Integer.toString(arrayList.get(i).getMaHoaDon());
                intent =new Intent(DSHD_ChiTietHoaDonXuat_Acivity.this,DSSP_ChiTietHoaDonXuat_Activity.class);
                intent.putExtra("maHDXuat",maHD);
                startActivity(intent);
                Toast.makeText(DSHD_ChiTietHoaDonXuat_Acivity.this,maHD,Toast.LENGTH_SHORT).show();
            }
        });
//
//       Toast.makeText(DSHD_ChiTietHoaDonXuat_Acivity.this,maHD,Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void hienthiDL() {
        Cursor dataHDXuat = database.GetData("SELECT * FROM HoaDonXuat order by maHDXuat DESC");
        Cursor dataChitietHoadonXuat = database.GetData("SELECT maHDXuat, SUM(GiaXuat) as TONGTIENXuat,SUM(SlXuat) as TONGSOSP,maHangXuat FROM ChiTietHoaDonXuat group by maHDXuat,maHangXuat order by maHDXuat DESC");
        arrayList.clear();
        while (dataHDXuat.moveToNext()) {
            Double TONGTIENXuat=0.0;
            Double TONGTIENMATHANG=0.0;
            String nhaCUngCap= dataHDXuat.getString(3);
            String nguoiNhap=dataHDXuat.getString(2);
            int maHD = dataHDXuat.getInt(0);
            String ngaytaoHD = dataHDXuat.getString(1);
            while(dataChitietHoadonXuat.moveToNext()){
                int maHDCHitiet=dataChitietHoadonXuat.getInt(0);
                if(maHDCHitiet==maHD){
                    TONGTIENMATHANG=dataChitietHoadonXuat.getDouble(1)*dataChitietHoadonXuat.getInt(2);;
                    TONGTIENXuat+=TONGTIENMATHANG;

                }
            }
            dataChitietHoadonXuat.moveToFirst();
            arrayList.add(new HoaDonXuat(ngaytaoHD,nguoiNhap,nhaCUngCap,maHD,TONGTIENXuat));

        }
        adapter.notifyDataSetChanged();

    }
}