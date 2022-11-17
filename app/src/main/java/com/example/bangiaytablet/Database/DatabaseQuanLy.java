package com.example.bangiaytablet.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseQuanLy extends SQLiteOpenHelper {
    public DatabaseQuanLy(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Truy van khong tra kq
    public void QuerryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public Cursor getData(String sql, String[] strings) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, strings);
        // rawQuery return A Cursor object, which is positioned before the first entry. Note that Cursors are not synchronized, see the documentation for more details.
        // chỉ đc đặt dấu ? trong mệnh đề where
        // mệnh đề where dùng and hoặc or k dùng dấu ,
    }

    public long insertData(String nameTable, ContentValues values) {
        SQLiteDatabase database = getWritableDatabase();
        return database.insert(nameTable, null, values);
        // insert return the row ID of the newly inserted row, or -1 if an error occurred
    }

    public int updateData(String nameTable, ContentValues values, String condition, String[] pars) {
        SQLiteDatabase database = getWritableDatabase();
        return database.update(nameTable, values, condition, pars);
        // update return the number of rows affected
    }

    public int deleteData(String nameTable, String whereClause, String[] pars) {
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(nameTable, whereClause, pars);
        //the number of rows affected if a whereClause is passed in, 0 otherwise. To remove all rows and get a count pass "1" as the whereClause.
    }

    public String getNameAccount() {
        Cursor cursor = this.getData("select TenDN from User where Trangthai=?", new String[]{"1"});
        cursor.moveToNext();
        String name = cursor.getString(0);
        cursor.close();
        return name;
    }

    public ContentValues valuesTableHang(String mahang, String tengiay, int soluong, double gia, String hang, String mausac, int size41, int size42, int size43, byte[] hinhanh) {
        String nameAccount = getNameAccount();
        ContentValues values = new ContentValues();
        values.put("MAHANG", mahang);
        values.put("TENLOAIGIAY", tengiay);
        values.put("TongSl", soluong);
        values.put("Gia", gia);
        values.put("HangSX", hang);
        values.put("MauSac", mausac);
        values.put("Size41", size41);
        values.put("Size42", size42);
        values.put("Size43", size43);
        values.put("hinhanh", hinhanh);
        values.put("TenDN", nameAccount);
        return values;
    }

    public ContentValues valuesTableHoaDonNhap(String ngaytao, String nguoinhan, String nhacungcap) {
        String tendanhnhap = getNameAccount();
        ContentValues values = new ContentValues();
        values.put("NgayTao", ngaytao);
        values.put("NguoiNhap", nguoinhan);
        values.put("NhaCungCap", nhacungcap);
        values.put("TenDN", tendanhnhap);
        return values;
    }

    public ContentValues valuesTableHoaDonXuat(String ngaytao, String nguoixuat, String nguoimua) {
        String tendanhnhap = getNameAccount();
        ContentValues values = new ContentValues();
        values.put("NgayTao", ngaytao);
        values.put("NguoiXuat", nguoixuat);
        values.put("NhaMua", nguoimua);
        values.put("TenDN", tendanhnhap);
        return values;
    }

    public ContentValues valuesTableChiTietHoaDonNhap(int mahoadon, String mahang, int soluong, double gia, int size) {
        String tendanhnhap = getNameAccount();
        ContentValues values = new ContentValues();
        values.put("maHDNhap", mahoadon);
        values.put("maHangNhap", mahang);
        values.put("SlNhap", soluong);
        values.put("GiaNhap", gia);
        values.put("Size", size);
        values.put("TenDN", tendanhnhap);
        return values;
    }

    public ContentValues valuesTableChiTietHoaDonXuat(int mahoadon, String mahang, int soluong, double gia, int size) {
        String tendanhnhap = getNameAccount();
        ContentValues values = new ContentValues();
        values.put("maHDXuat", mahoadon);
        values.put("maHangXuat", mahang);
        values.put("SlXuat", soluong);
        values.put("GiaXuat", gia);
        values.put("Size", size);
        values.put("TenDN", tendanhnhap);
        return values;
    }

    public void updateImageProduct(String maSp, byte[] hinhanh) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Hang SET hinhanh= ? WHERE MAHANG=? ";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, hinhanh);
        statement.bindString(2, maSp);

        statement.executeUpdateDelete();
    }


    //    public void InsertNewProduct(String mahang,String tenhang,int soluong,double giaban,
//                            String hangSX, String MauSac,int slsize41,int slsize42,int slsize43,byte[] hinhanh){
//        SQLiteDatabase database= getWritableDatabase();
//        String sql="INSERT INTO Hang VALUES(?,?,?,?,?,?,?,?,?,?)";
//        SQLiteStatement statement=database.compileStatement(sql);
//        statement.clearBindings();
//
//        statement.bindString(0,mahang);
//        statement.bindString(1,tenhang);
//        statement.bindString(2, String.valueOf(soluong));
//        statement.bindString(3,mahang);
//        statement.bindString(4,mahang);
//        statement.bindString(5,mahang);
//        statement.bindString(6,mahang);
//        statement.bindString(7,mahang);
//        statement.bindString(8,mahang);
//        statement.bindBlob(9,hinhanh);
//
//
//
//    }
}
