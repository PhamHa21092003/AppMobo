package com.example.bangiaytablet.Action;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bangiaytablet.Activity.HangTrongKho_Activity;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Database.DatabaseQuanLy;
import com.example.bangiaytablet.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class SuaSP_Action extends AppCompatActivity {

    Intent intent;
    String tenspsua, maspsua, size41, size42, size43;
    EditText edtTensp, edtSLsize41, edtSLsize42, edtSLsize43;
    ImageView anhSP,moCamera,moFolder;
    ArrayList<Hang> arrayList;
    Button btnThaydoi, btnHuy;
    DatabaseQuanLy database;
    final int REQUEST_CODE_CAMERA=123;
    final int REQUEST_CODE_FOLDER=456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_sp);

        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);
        arrayList = new ArrayList<>();

        tenspsua = getIntent().getStringExtra("tenSpSua");
        maspsua = getIntent().getStringExtra("maSpSua");
        size41 = getIntent().getStringExtra("size41");
        size42 = getIntent().getStringExtra("size42");
        size43 = getIntent().getStringExtra("size43");


        edtTensp = findViewById(R.id.editTextTenSanPhamSua);
        edtSLsize41 = findViewById(R.id.edtsls41);
        edtSLsize42 = findViewById(R.id.edtsls42);
        edtSLsize43 = findViewById(R.id.edtsls43);
        anhSP=findViewById(R.id.imgSpSua);
        moFolder=findViewById(R.id.btnThemanhSuaFolder);
        moCamera=findViewById(R.id.btnThemanhSuaCamera);


        btnThaydoi = findViewById(R.id.btnCapNhapSua);
        btnHuy = findViewById(R.id.btnHuySua);
        getAnh();

        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i).getMaHang().equalsIgnoreCase(maspsua)){
                byte[] hinhAnh = arrayList.get(i).getHinhanh();
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                anhSP.setImageBitmap(bitmap);
                break;
            }
        }


        moCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        SuaSP_Action.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                );
            }
        });

        moFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        SuaSP_Action.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER
                );
            }
        });





        edtTensp.setText(tenspsua);
        edtSLsize41.setText(size41);
        edtSLsize42.setText(size42);
        edtSLsize43.setText(size43);


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SuaSP_Action.this, HangTrongKho_Activity.class);
                startActivity(intent);
            }
        });

        btnThaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenmoi = edtTensp.getText().toString().trim();
                String SLSIze41moi = edtSLsize41.getText().toString().trim();
                String SLSIze42moi = edtSLsize42.getText().toString().trim();
                String SLSIze43moi = edtSLsize43.getText().toString().trim();

//                boolean slDangSo=false;

//                Kiem tra số lượng có phải số không
//                try {
//                    Integer.parseInt(SLmoi);
//                    slDangSo=true;
//                }
//                catch (NumberFormatException e){
////
//                    slDangSo=false;
//                }

                if (TextUtils.isEmpty(tenmoi) || TextUtils.isEmpty(SLSIze41moi) || TextUtils.isEmpty(SLSIze42moi) || TextUtils.isEmpty(SLSIze43moi)) {
                    Toast.makeText(SuaSP_Action.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();
                } else {
                    int SLSize41moiINT = Integer.parseInt(SLSIze41moi);
                    int SLSize42moiINT = Integer.parseInt(SLSIze42moi);
                    int SLSize43moiINT = Integer.parseInt(SLSIze43moi);
                    int TongSoSP = SLSize41moiINT + SLSize42moiINT + SLSize43moiINT;

                    //chuyển từ dataImageView ->byte[];
                    BitmapDrawable bitmapDrawable= (BitmapDrawable) anhSP.getDrawable();
                    Bitmap bitmap=bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray= new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                    byte[] hinhAnh= byteArray.toByteArray();

                    database.QuerryData("UPDATE Hang SET TENlOAIGIAY='" + tenmoi + "',TongSl='" + TongSoSP + "',Size41='" + SLSize41moiINT + "',Size42='" + SLSize42moiINT + "',Size43='" + SLSize43moiINT + "' WHERE MAHANG='" + maspsua + "'");
                    database.updateImageProduct(maspsua, hinhAnh);
                    Toast.makeText(SuaSP_Action.this, "Cập nhật thành công!!", Toast.LENGTH_LONG).show();
                    intent = new Intent(SuaSP_Action.this, HangTrongKho_Activity.class);
                    startActivity(intent);

                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Intent intent1=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent1,REQUEST_CODE_CAMERA);
                }
                else {
                    Toast.makeText(SuaSP_Action.this,"Bạn không cấp quyền cho mở Camera",Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_FOLDER:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Intent intent1=new Intent(Intent.ACTION_PICK);
                    intent1.setType("image/*");
                    startActivityForResult(intent1,REQUEST_CODE_FOLDER);
                }
                else {
                    Toast.makeText(SuaSP_Action.this,"Bạn không cấp quyền cho mở Folder",Toast.LENGTH_SHORT).show();
                }
                break;

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_CAMERA && resultCode==RESULT_OK && data!=null){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            anhSP.setImageBitmap(bitmap);
        }
        if(requestCode==REQUEST_CODE_FOLDER && resultCode==RESULT_OK && data!=null){
            Uri uri= data.getData();
            try {
                InputStream inputStream= getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                anhSP.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getAnh() {
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
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
            byte[] hinhanh = dataHang.getBlob(9);
            arrayList.add(new Hang(MaHang, TenHang, hangSX, MauSac, SLSize41, SLSize42, SLSize43, SL, Gia, dataHang.getBlob(9)));
        }
    }
}