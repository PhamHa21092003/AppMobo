package com.example.bangiaytablet.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bangiaytablet.Class.ChiTiethoaDonXuat;
import com.example.bangiaytablet.Class.ChitietHoaDonNhap;
import com.example.bangiaytablet.R;

import java.util.List;

public class DS_SanPhamXuat_ChiTietHoaDonXuat_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ChiTiethoaDonXuat> ChiTietHoadonXuatList;

    public DS_SanPhamXuat_ChiTietHoaDonXuat_Adapter(Context context, int layout, List<ChiTiethoaDonXuat> chiTietHoadonXuatList) {
        this.context = context;
        this.layout = layout;
        ChiTietHoadonXuatList = chiTietHoadonXuatList;
    }

    @Override
    public int getCount() {
        return ChiTietHoadonXuatList.size();
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
        public TextView tensp,masp,sl,gia,mausac,thuonghieu,size;
        public ImageView anhSP;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder viewHolder;
        if(view==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            //Anh xa
            viewHolder.tensp= view.findViewById(R.id.tenspTRongdsHoaDonXuat);
            viewHolder.masp=view.findViewById(R.id.masptrongHDXuat);
            viewHolder.sl=view.findViewById(R.id.soluongsptronghdXuat);
            viewHolder.gia=view.findViewById(R.id.giaspTRongdsHoaDonXuat);
            viewHolder.mausac=view.findViewById(R.id.MauSacsptronghdXuat);
            viewHolder.thuonghieu=view.findViewById(R.id.ThuongHieusptronghdXuat);
            viewHolder.size=view.findViewById(R.id.SizesptronghdXuat);
            viewHolder.anhSP=view.findViewById(R.id.imageViewAnhSPChiTietHDXuat);

            view.setTag(viewHolder);

        }
        else{
            viewHolder= (ViewHolder)view.getTag();
        }
        ChiTiethoaDonXuat HD= ChiTietHoadonXuatList.get(i);
        String sl=Integer.toString(HD.getSoLuongXuat());
        String sizenhap=Integer.toString(HD.getSizeXuat());
        String gianhap= Double.toString(HD.getGiaXuat());
        viewHolder.tensp.setText(HD.getTenhangXuat());
        viewHolder.masp.setText("Mã SP: "+HD.getMaHangXuat());
        viewHolder.sl.setText("Sl: "+sl);
        viewHolder.gia.setText("Giá: "+gianhap);
        viewHolder.size.setText("Size: "+sizenhap);
        viewHolder.mausac.setText("Màu sắc: "+HD.getMauSac());
        viewHolder.thuonghieu.setText("Thương hiệu: "+HD.getThuonghieu());

        //chuyển byte[]->bitmap
        byte[] hinhAnh= HD.getAnhsp();
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        viewHolder.anhSP.setImageBitmap(bitmap);
        return view;
    }
}
