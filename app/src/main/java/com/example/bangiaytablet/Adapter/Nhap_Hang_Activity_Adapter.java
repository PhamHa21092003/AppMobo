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
import com.example.bangiaytablet.Activity.Nhap_Hang_Activity;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.R;

import java.util.List;

public class Nhap_Hang_Activity_Adapter extends BaseAdapter {
    private Nhap_Hang_Activity context;
    private int layout;
    private List<ChitietHoaDonNhap> ChiTietHoaDonNhapList;
    Intent intent;

    public Nhap_Hang_Activity_Adapter(Nhap_Hang_Activity context, int layout, List<ChitietHoaDonNhap> chiTietHoaDonNhapList) {
        this.context = context;
        this.layout = layout;
        ChiTietHoaDonNhapList = chiTietHoaDonNhapList;
    }

    @Override
    public int getCount() {
        return ChiTietHoaDonNhapList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    private class ViewHolderNhap {
        public TextView tvTenSPNhap, tvMaSPNhap, tvSLnhap, mausac, giaNhap, Size;
        public ImageView imgXoa, anhsp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderNhap viewHolder;
        if (view == null) {
            viewHolder = new ViewHolderNhap();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            //Anh xa
            viewHolder.tvTenSPNhap = view.findViewById(R.id.tenspDangNhap);
            viewHolder.tvMaSPNhap = view.findViewById(R.id.maspDangNhap);
            viewHolder.tvSLnhap = view.findViewById(R.id.soluongspDangNhap);
            viewHolder.imgXoa = view.findViewById(R.id.btnXoaSPtrongDSDANgNhap);
            viewHolder.mausac = view.findViewById(R.id.mausacspDangnhap);
            viewHolder.giaNhap = view.findViewById(R.id.GiaspDangnhap);
            viewHolder.Size = view.findViewById(R.id.sizespDangnhap);
            viewHolder.anhsp = view.findViewById(R.id.imageViewAnhSPNhap);
            view.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolderNhap) view.getTag();
        }
        ChitietHoaDonNhap Sp = ChiTietHoaDonNhapList.get(i);
        String SL = Integer.toString(Sp.getSoLuongNhap());
        viewHolder.tvTenSPNhap.setText(Sp.getTenhangNhap());
        viewHolder.tvMaSPNhap.setText("Mã SP: " + Sp.getMaHangNhap());
        viewHolder.tvSLnhap.setText("SL: " + SL);
        viewHolder.mausac.setText("Màu: " + Sp.getMauSac());
        viewHolder.Size.setText("Size: " + Sp.getSizeNhap());
        viewHolder.giaNhap.setText("Giá nhập: " + Sp.getGiaNhap());
        //chuyển byte[]->bitmap
        byte[] hinhAnh = Sp.getAnhsp();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        viewHolder.anhsp.setImageBitmap(bitmap);


        viewHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoa(Sp.getTenhangNhap(), Sp.getMaHangNhap(), Sp.getSizeNhap(), Sp.getSoLuongNhap());
            }
        });
        return view;
    }
}
