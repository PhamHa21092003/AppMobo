package com.example.bangiaytablet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bangiaytablet.Class.Hang;
import com.example.bangiaytablet.Class.HoaDonNhap;
import com.example.bangiaytablet.R;

import java.util.List;

public class DS_hoa_don_nhap_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<HoaDonNhap> HoadonList;

    public DS_hoa_don_nhap_Adapter(Context context, int layout, List<HoaDonNhap> hoadonList) {
        this.context = context;
        this.layout = layout;
        HoadonList = hoadonList;
    }

    @Override
    public int getCount() {
        return HoadonList.size();
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
        public TextView NgayNhap, maHD, tongtien, nhacc, nguoinhap;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            //Anh xa
            viewHolder.NgayNhap = view.findViewById(R.id.ngayNhapHang);
            viewHolder.maHD = view.findViewById(R.id.maHDNhap);
            viewHolder.tongtien = view.findViewById(R.id.tongsotiennhap);
            viewHolder.nhacc = view.findViewById(R.id.nhaCCNhapHang);
            viewHolder.nguoinhap = view.findViewById(R.id.nguoiNhapHang);

            view.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HoaDonNhap HD = HoadonList.get(i);
        String maHD = Integer.toString(HD.getMaHoaDon());
        Double TongTienCuaHoaDon = HD.getTongtien();
        String tongTienString = Double.toString(TongTienCuaHoaDon);
        viewHolder.NgayNhap.setText("Ng??y t???o: " + HD.getNgayTaoHoaDon());
        viewHolder.maHD.setText("M?? HD: " + maHD);
        viewHolder.tongtien.setText("T???ng ti???n: " + tongTienString);
        viewHolder.nhacc.setText("Nh?? cung c???p: " + HD.getNcc());
        viewHolder.nguoinhap.setText("Ng?????i nh???p: " + HD.getNguoiNhap());
        return view;
    }
}
