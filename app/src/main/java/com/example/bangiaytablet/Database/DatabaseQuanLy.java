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

    //Truy van khong tra kq
    public void QuerryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public Cursor getData(String sql, String[] strings){  // rawQuery return A Cursor object, which is positioned before the first entry. Note that Cursors are not synchronized, see the documentation for more details.
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, strings);
    }

    public long insertData(String nameTable, ContentValues values){ // insert return the row ID of the newly inserted row, or -1 if an error occurred
        SQLiteDatabase database = getWritableDatabase();
        return database.insert(nameTable, null, values);
    }

    public int updateData(String sql, ContentValues values, String condition, String[] pars){ // update return the number of rows affected
        SQLiteDatabase database = getWritableDatabase();
        return  database.update(sql, values, condition, pars);
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


    public void updateImageProduct(String maSp, byte[] hinhanh) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Hang SET hinhanh= ? WHERE MAHANG=? ";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, hinhanh);
        statement.bindString(2, maSp);

        statement.executeUpdateDelete();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
