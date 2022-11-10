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

import com.example.bangiaytablet.Activity.Nhap_Hang_Activity;
import com.example.bangiaytablet.Activity.XuatHang_Activity;
import com.example.bangiaytablet.Class.ChiTiethoaDonXuat;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.R;

import java.util.List;

public class Xuat_Hang_Activity_Adapter extends BaseAdapter {
    private XuatHang_Activity context;
    private int layout;
    private List<ChiTiethoaDonXuat> ChiTietHoaDonXuatList;
    Intent intent;

    @Override
    public int getCount() {
        return ChiTietHoaDonXuatList.size();
    }

    public Xuat_Hang_Activity_Adapter(XuatHang_Activity context, int layout, List<ChiTiethoaDonXuat> chiTietHoaDonXuatList) {
        this.context = context;
        this.layout = layout;
        ChiTietHoaDonXuatList = chiTietHoaDonXuatList;
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
        public TextView tvTenSPXuat, tvMaSPXuat, tvSLXuat, mausac, giaXuat, Size;
        public ImageView imgXoa, anhsp;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolderXuat viewHolder;
        if (view == null) {
            viewHolder = new ViewHolderXuat();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            //Anh xa
            viewHolder.tvTenSPXuat = view.findViewById(R.id.tenspDangXuat);
            viewHolder.tvMaSPXuat = view.findViewById(R.id.maspDangXuat);
            viewHolder.tvSLXuat = view.findViewById(R.id.soluongspDangXuat);
//            viewHolder.imgXoa=view.findViewById(R.id.btnXoaSPtrongDSDANgXuat);
            viewHolder.mausac = view.findViewById(R.id.mausacspDangXuat);
            viewHolder.giaXuat = view.findViewById(R.id.GiaspDangXuat);
            viewHolder.Size = view.findViewById(R.id.sizespDangXuat);
            viewHolder.anhsp = view.findViewById(R.id.imageViewAnhSPXuat);
            view.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolderXuat) view.getTag();
        }
        ChiTiethoaDonXuat Sp = ChiTietHoaDonXuatList.get(position);
        String SL = Integer.toString(Sp.getSoLuongXuat());
        viewHolder.tvTenSPXuat.setText(Sp.getTenhangXuat());
        viewHolder.tvMaSPXuat.setText("Mã SP: " + Sp.getMaHangXuat());
        viewHolder.tvSLXuat.setText("SL: " + SL);
        viewHolder.mausac.setText("Màu: " + Sp.getMauSac());
        viewHolder.Size.setText("Size: " + Sp.getSizeXuat());
        viewHolder.giaXuat.setText("Giá nhập: " + Sp.getGiaXuat());
        //chuyển byte[]->bitmap
        byte[] hinhAnh = Sp.getAnhsp();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        viewHolder.anhsp.setImageBitmap(bitmap);


//        viewHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.DialogXoa(Sp.getTenhangNhap(),Sp.getMaHangNhap(),Sp.getSizeNhap(),Sp.getSoLuongNhap());
//            }
//        });
        return view;
    }
}
