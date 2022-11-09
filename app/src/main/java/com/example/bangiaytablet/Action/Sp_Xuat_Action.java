package com.example.bangiaytablet.Action;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bangiaytablet.Class.ChiTiethoaDonXuat;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Class.HoaDonNhap;
import com.example.bangiaytablet.Class.HoaDonXuat;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Sp_Xuat_Action extends AppCompatActivity {
    Intent intent;
    DatabaseQuanLy database;
    EditText SlSpMua;
    RadioButton rdoSize41,rdoSize42,rdoSize43;
    TextView maSP,tenSP,mausac,ThuongHieu,slTRongKho,giaBanSP;
    ImageView anhSP;
    int slCu,slTong,slCon,slMua;
    String tensp,masp,slsp;
    String tenspnhan,maspnhan,thuonghieunhan,maunhan,size41nhan,size42nhan,size43nhan,tongSLnhan,giaban;
    Button btnThem,btnHuy;
    ArrayList<Hang> arrayList;
    ArrayList<HoaDonXuat> arrayListHoaDonXuat;
    ArrayList<ChiTiethoaDonXuat> arrayListChiTietHoaDonXuat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_xuat_action);

        arrayList=new ArrayList<>();
        arrayListChiTietHoaDonXuat=new ArrayList<>();
        arrayListHoaDonXuat=new ArrayList<>();
        maspnhan=getIntent().getStringExtra("maSpThem");
        tenspnhan=getIntent().getStringExtra("tenSpThem");
        thuonghieunhan=getIntent().getStringExtra("tenthuonghieu");
        maunhan=getIntent().getStringExtra("MauSac");
        size41nhan=getIntent().getStringExtra("Size41");
        size42nhan=getIntent().getStringExtra("Size42");
        size43nhan=getIntent().getStringExtra("Size43");
        tongSLnhan=getIntent().getStringExtra("tongSL");
        giaban=getIntent().getStringExtra("giaBan");

        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);
        anhxa();
        getdataHang();
        getdataHoaDonXuat();
        maSP.setText("Mã sản phâm: "+maspnhan);
        tenSP.setText("Tên sản phẩm: "+tenspnhan);
        mausac.setText("Màu sắc: "+ maunhan);
        ThuongHieu.setText("Thương hiệu: "+ thuonghieunhan);
        slTRongKho.setText("Số Lượng sản phẩm còn: "+tongSLnhan);
        giaBanSP.setText("Giá bán: "+giaban);
        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i).getMaHang().equalsIgnoreCase(maspnhan)){
                byte[] hinhAnh = arrayList.get(i).getHinhanh();
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                anhSP.setImageBitmap(bitmap);
                break;
            }
        }
        rdoSize41.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(rdoSize41.isChecked()){
                    slTRongKho.setText("Số Lượng sản phẩm còn: "+size41nhan);
                }
            }
        });

        rdoSize42.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(rdoSize42.isChecked()){
                    slTRongKho.setText("Số Lượng sản phẩm còn: "+size42nhan);
                }
            }
        });

        rdoSize43.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(rdoSize43.isChecked()){
                    slTRongKho.setText("Số Lượng sản phẩm còn: "+size43nhan);
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(Sp_Xuat_Action.this,Xuat_Hang_Action.class);
                startActivity(intent);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String slcon= slTRongKho.getText().toString().trim();
                String slcontach[]=slcon.split("\\ ");
                String slmua= SlSpMua.getText().toString().trim();
                slCon= Integer.parseInt(slcontach[5]);
                slMua= Integer.parseInt(slmua);
               if(slMua > slCon){
                   Toast.makeText(Sp_Xuat_Action.this,"Số lượng mua vượt quá số lượng Sp còn",Toast.LENGTH_SHORT).show();
               }
               else{
                   int slsauMua=slCon-slMua;
                   int TongSLConSauMua=0;
                   for(int i=0;i<arrayList.size();i++){
                       if(arrayList.get(i).getMaHang().equalsIgnoreCase(maspnhan)){
                           TongSLConSauMua=arrayList.get(i).getSoLuong()-slMua;
                           break;
                       }
                   }
                   if(rdoSize41.isChecked()==true){
                       int mahdxuat=arrayListHoaDonXuat.get(arrayListHoaDonXuat.size()-1).getMaHoaDon();
                       database.QuerryData("INSERT INTO ChiTietHoaDonXuat VALUES('"+mahdxuat+"','"+maspnhan+"','"+slMua+"','"+giaban+"',41) ");
                       database.QuerryData("UPDATE Hang set TongSl= '"+TongSLConSauMua+"', Size41='"+slsauMua+"' WHERE MAHANG='"+maspnhan+"' ");

                   }
                   if(rdoSize42.isChecked()==true){
                       int mahdxuat=arrayListHoaDonXuat.get(arrayListHoaDonXuat.size()-1).getMaHoaDon();
                       database.QuerryData("INSERT INTO ChiTietHoaDonXuat VALUES('"+mahdxuat+"','"+maspnhan+"','"+slMua+"','"+giaban+"',42) ");
                       database.QuerryData("UPDATE Hang set TongSl= '"+TongSLConSauMua+"', Size42='"+slsauMua+"' WHERE MAHANG='"+maspnhan+"' ");

                   }
                   if(rdoSize43.isChecked()==true){
                       int mahdxuat=arrayListHoaDonXuat.get(arrayListHoaDonXuat.size()-1).getMaHoaDon();
                       database.QuerryData("INSERT INTO ChiTietHoaDonXuat VALUES('"+mahdxuat+"','"+maspnhan+"','"+slMua+"','"+giaban+"',43) ");
                       database.QuerryData("UPDATE Hang set TongSl= '"+TongSLConSauMua+"', Size43='"+slsauMua+"' WHERE MAHANG='"+maspnhan+"' ");

                   }

                   intent=new Intent(Sp_Xuat_Action.this,Xuat_Hang_Action.class);
                   startActivity(intent);
               }


        }
        });
    }

    private void anhxa() {
        SlSpMua=findViewById(R.id.edtSLMua);
        maSP=findViewById(R.id.TextMaSanPhamXuat);
        tenSP=findViewById(R.id.TextTenSanPhamXuat);
        mausac=findViewById(R.id.TextMauSanPhamXuat);
        ThuongHieu=findViewById(R.id.TextHangSanPhamXuat);
        slTRongKho=findViewById(R.id.TextViewSlSPXuat);
        giaBanSP=findViewById(R.id.TextViewGiaSanPhamXuat);
        anhSP=findViewById(R.id.imgSpXuat);
        rdoSize41=findViewById(R.id.rdobtn41);
        rdoSize42=findViewById(R.id.rdobtn42);
        rdoSize43=findViewById(R.id.rdobtn43);
        btnThem=findViewById(R.id.btnThemSPMoiVaoHoaDonXuat);
        btnHuy=findViewById(R.id.btnHuyXuat);
    }
    private void getdataHang() {
        Cursor dataHang = database.GetData("SELECT * FROM Hang ");
        arrayList.clear();
        while (dataHang.moveToNext()) {
            int SL = dataHang.getInt(2);
            String TenHang = dataHang.getString(1);
            String MaHang = dataHang.getString(0);
            String MauSac= dataHang.getString(5);
            String hangSX=dataHang.getString(4);
            int SLSize41=dataHang.getInt(6);
            int SLSize42=dataHang.getInt(7);
            int SLSize43=dataHang.getInt(8);
            Double Gia=dataHang.getDouble(3);
            arrayList.add(new Hang(MaHang,TenHang,hangSX,MauSac,SLSize41,SLSize42,SLSize43,SL,Gia,dataHang.getBlob(9)));
        }
    }

    private void getdataHoaDonXuat(){
        Cursor dataHoaDonXuat = database.GetData("SELECT * FROM HoaDonXuat ");
        arrayListHoaDonXuat.clear();
        while (dataHoaDonXuat.moveToNext()) {
            int maHD = dataHoaDonXuat.getInt(0);
            String ngayNhap = dataHoaDonXuat.getString(1);
            arrayListHoaDonXuat.add(new HoaDonXuat(ngayNhap,maHD));
        }
    }
}