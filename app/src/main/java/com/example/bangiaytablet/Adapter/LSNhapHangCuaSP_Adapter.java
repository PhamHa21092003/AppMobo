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

import com.example.bangiaytablet.Activity.HangTrongKho_Activity;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.R;

import java.util.List;

public class LSNhapHangCuaSP_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ChitietHoaDonNhap> ChiTietNhapList;

    public LSNhapHangCuaSP_Adapter(Context context, int layout, List<ChitietHoaDonNhap> chiTietNhapList) {
        this.context = context;
        this.layout = layout;
        ChiTietNhapList = chiTietNhapList;
    }

    @Override
    public int getCount() {
        return ChiTietNhapList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        public TextView tensp, masp, sl, gia, mausac, thuonghieu, size;
        public ImageView anhSP;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            //Anh xa
            viewHolder.tensp = view.findViewById(R.id.tenspTRongdsHoaDon);
            viewHolder.masp = view.findViewById(R.id.masptrongHDNHAP);
            viewHolder.sl = view.findViewById(R.id.soluongsptronghdnhap);
            viewHolder.gia = view.findViewById(R.id.giaspTRongdsHoaDon);
            viewHolder.mausac = view.findViewById(R.id.MauSacsptronghdnhap);
            viewHolder.thuonghieu = view.findViewById(R.id.ThuongHieusptronghdnhap);
            viewHolder.size = view.findViewById(R.id.Sizesptronghdnhap);
            viewHolder.anhSP = view.findViewById(R.id.imageViewAnhSPChiTietHDNhap);

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ChitietHoaDonNhap HD = ChiTietNhapList.get(i);
        String sl = Integer.toString(HD.getSoLuongNhap());
        String sizenhap = Integer.toString(HD.getSizeNhap());
        String gianhap = Double.toString(HD.getGiaNhap());
        viewHolder.tensp.setText(HD.getTenhangNhap());
        viewHolder.masp.setText("M?? SP: " + HD.getMaHangNhap());
        viewHolder.sl.setText("Sl: " + sl);
        viewHolder.gia.setText("Gi??: " + gianhap);
        viewHolder.size.setText("Size: " + sizenhap);
        viewHolder.mausac.setText("M??u s???c: " + HD.getMauSac());
        viewHolder.thuonghieu.setText("Th????ng hi???u: " + HD.getThuonghieu());

        //chuy???n byte[]->bitmap
        byte[] hinhAnh = HD.getAnhsp();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        viewHolder.anhSP.setImageBitmap(bitmap);
        return view;
    }
}
