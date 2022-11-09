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

import com.example.bangiaytablet.Action.SuaSP_Action;
import com.example.bangiaytablet.Class.Hang;

import com.example.bangiaytablet.R;
//import com.example.bangiaytablet.SuaSP_Activity;
import com.example.bangiaytablet.Activity.HangTrongKho_Activity;
import com.example.bangiaytablet.Class.Hang;

import java.util.List;

public class Hang_Adapter extends BaseAdapter
{
    //để gọi được hàm trong HangTrongKho_Activity thì Context phải là HangTrongKho_Activity
    private HangTrongKho_Activity context;
    private int layout;
    private List<Hang> HangList;
    Intent intent;

    public Hang_Adapter(HangTrongKho_Activity context, int layout, List<Hang> hangList) {
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

    private class ViewHolder{
        public TextView tvTenSP,tvMagiay,tvSL,tvgia,tvMauSac;
        public ImageView imgSua,imgXoa,hinhanhSP;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            //Anh xa
            viewHolder.tvTenSP= view.findViewById(R.id.tensptrongkhonsptrongkho);
            viewHolder.tvMagiay=view.findViewById(R.id.masptrongkho);
            viewHolder.tvSL=view.findViewById((R.id.soluongsptrongkho));
            viewHolder.imgSua=view.findViewById(R.id.btnedit);
            viewHolder.imgXoa=view.findViewById(R.id.btndelete);
            viewHolder.tvgia=view.findViewById(R.id.giasptrongkho);
            viewHolder.tvMauSac=view.findViewById(R.id.mausacsptrongkho);
            viewHolder.hinhanhSP=view.findViewById(R.id.imageViewAnhSP);

            view.setTag(viewHolder);

        }
        else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Hang Sp= HangList.get(i);
        String SL=Integer.toString(Sp.getSoLuong());
        String giaSp= Double.toString(Sp.getGia());
        viewHolder.tvTenSP.setText(Sp.getTenHang());
        viewHolder.tvMagiay.setText("Mã SP: "+Sp.getMaHang());
        viewHolder.tvSL.setText("SL: "+SL);
        viewHolder.tvgia.setText("Giá: "+giaSp);
        viewHolder.tvMauSac.setText("Màu sắc: "+Sp.getMausac());

        //chuyển byte[]->bitmap
        byte[] hinhAnh= Sp.getHinhanh();
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        viewHolder.hinhanhSP.setImageBitmap(bitmap);


        viewHolder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(context, SuaSP_Action.class);

                String tenSpSua= Sp.getTenHang();
                String maSpSua= Sp.getMaHang();
                int SLSp= Sp.getSoLuong();
                String size41= Integer.toString(Sp.getSize41());
                String size42= Integer.toString(Sp.getSize42());
                String size43= Integer.toString(Sp.getSize43());

                String SlSpSua=Integer.toString(SLSp);
                intent.putExtra("maSpSua",maSpSua);
                intent.putExtra("tenSpSua",tenSpSua);
                intent.putExtra("size41",size41);
                intent.putExtra("size42",size42);
                intent.putExtra("size43",size43);

                context.startActivity(intent);

            }
        });

        viewHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoa(Sp.getTenHang(),Sp.getMaHang());
            }
        });
        return view;
    }
}
