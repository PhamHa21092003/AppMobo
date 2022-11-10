package com.example.bangiaytablet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bangiaytablet.Action.Nhap_Hang_Action;
import com.example.bangiaytablet.Action.Sp_Xuat_Action;
import com.example.bangiaytablet.Action.Them_SP_Da_Co_Action;
import com.example.bangiaytablet.Action.Xuat_Hang_Action;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.R;

import java.util.List;

public class Xuat_Hang_Action_Adapter extends BaseAdapter {
    private Xuat_Hang_Action context;
    private int layout;
    private List<Hang> HangList;
    Intent intent;

    public Xuat_Hang_Action_Adapter(Xuat_Hang_Action context, int layout, List<Hang> hangList) {
        this.context = context;
        this.layout = layout;
        HangList = hangList;
    }

    @Override
    public int getCount() {
        return HangList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolderXuat {
        public TextView tvTenSPXuat, tvMaSPXuat, tvSL, mausac, giaXuat;
        public ImageView imgXuat, imgANhSP;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderXuat viewHolder;
        if (view == null) {
            viewHolder = new ViewHolderXuat();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            //Anh xa
            viewHolder.tvTenSPXuat = view.findViewById(R.id.tensptrongkhonsptrongkhoXuat);
            viewHolder.tvMaSPXuat = view.findViewById(R.id.masptrongkhoXuat);
            viewHolder.tvSL = view.findViewById(R.id.soluongsptrongkhoXuat);
            viewHolder.imgXuat = view.findViewById(R.id.btnXuatThemSP);
            viewHolder.mausac = view.findViewById(R.id.mausacspXuat);
            viewHolder.imgANhSP = view.findViewById(R.id.imageViewAnhSPXuat);
            viewHolder.giaXuat = view.findViewById(R.id.giaXuatKho);
            view.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolderXuat) view.getTag();
        }
        Hang Sp = HangList.get(i);
        String SL = Integer.toString(Sp.getSoLuong());
        viewHolder.tvTenSPXuat.setText(Sp.getTenHang());
        viewHolder.tvMaSPXuat.setText("Mã SP: " + Sp.getMaHang());
        viewHolder.tvSL.setText("SL: " + SL);
        viewHolder.mausac.setText("Màu: " + Sp.getMausac());
        viewHolder.giaXuat.setText("Giá Xuất:" + Sp.getGia());
        //chuyển byte[]->bitmap
        byte[] hinhAnh = Sp.getHinhanh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        viewHolder.imgANhSP.setImageBitmap(bitmap);


        viewHolder.imgXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, Sp_Xuat_Action.class);
                String tenSpThem = Sp.getTenHang();
                String maSpThem = Sp.getMaHang();
                int SLSp = Sp.getSoLuong();
                String SlSpThem = Integer.toString(SLSp);
                intent.putExtra("maSpThem", maSpThem);
                intent.putExtra("tenSpThem", tenSpThem);
                intent.putExtra("tenthuonghieu", Sp.getNhaSanXuat());
                intent.putExtra("MauSac", Sp.getMausac());
                intent.putExtra("giaBan", Double.toString(Sp.getGia()));
                intent.putExtra("Size41", Integer.toString(Sp.getSize41()));
                intent.putExtra("Size42", Integer.toString(Sp.getSize42()));
                intent.putExtra("Size43", Integer.toString(Sp.getSize43()));
                intent.putExtra("tongSL", Integer.toString(Sp.getSoLuong()));
                context.startActivity(intent);

            }
        });
        return view;
    }
}
