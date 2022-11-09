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
import android.widget.Toast;

import com.example.bangiaytablet.Action.Nhap_Hang_Action;
import com.example.bangiaytablet.Action.Them_SP_Da_Co_Action;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.R;

import java.util.List;

public class Nhap_Hang_Action_Adapter extends BaseAdapter {

    private Nhap_Hang_Action context;
    private int layout;
    private List<Hang> HangList;
    Intent intent;

    public Nhap_Hang_Action_Adapter(Nhap_Hang_Action context, int layout, List<Hang> hangList) {
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

    private class ViewHolderNhap{
        public TextView tvTenSPNhap,tvMaSPNhap,tvSL,mausac;
        public ImageView imgNhap,imgANhSP;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolderNhap viewHolder;
        if(view==null){
            viewHolder= new ViewHolderNhap();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            //Anh xa
            viewHolder.tvTenSPNhap= view.findViewById(R.id.tensptrongkhonsptrongkhoNhap);
            viewHolder.tvMaSPNhap=view.findViewById(R.id.masptrongkhoNhap);
            viewHolder.tvSL=view.findViewById(R.id.soluongsptrongkhoNhap);
            viewHolder.imgNhap=view.findViewById(R.id.btnNhapThemSP);
            viewHolder.mausac=view.findViewById(R.id.mausacspnhap);
            viewHolder.imgANhSP=view.findViewById(R.id.imageViewAnhSPNhap);
            view.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolderNhap) view.getTag();
        }
        Hang Sp= HangList.get(position);
        String SL=Integer.toString(Sp.getSoLuong());
        viewHolder.tvTenSPNhap.setText(Sp.getTenHang());
        viewHolder.tvMaSPNhap.setText("Mã SP: "+Sp.getMaHang());
        viewHolder.tvSL.setText("SL: "+SL);
        viewHolder.mausac.setText("Màu: "+Sp.getMausac());
        //chuyển byte[]->bitmap
        byte[] hinhAnh= Sp.getHinhanh();
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        viewHolder.imgANhSP.setImageBitmap(bitmap);
        viewHolder.imgNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(context, Them_SP_Da_Co_Action.class);
                String tenSpThem= Sp.getTenHang();
                String maSpThem= Sp.getMaHang();
                int SLSp= Sp.getSoLuong();
                String SlSpThem=Integer.toString(SLSp);
                intent.putExtra("maSpThem",maSpThem);
                intent.putExtra("tenSpThem",tenSpThem);
                intent.putExtra("tenthuonghieu",Sp.getNhaSanXuat());
                intent.putExtra("MauSac",Sp.getMausac());
                intent.putExtra("Size41",Integer.toString(Sp.getSize41()));
                intent.putExtra("Size42",Integer.toString(Sp.getSize42()));
                intent.putExtra("Size43",Integer.toString(Sp.getSize43()));
                intent.putExtra("tongSL",Integer.toString(Sp.getSoLuong()));
                context.startActivity(intent);
            }
        });
        return view;
    }
}