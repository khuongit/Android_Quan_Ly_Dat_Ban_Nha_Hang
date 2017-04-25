package com.phamtritoan.cafeapp.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 4/24/2017.
 */

public class AppCafe extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "ORDERCAFE";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_QLYMON = "QLyMon";
    private static final String TABLE_NHANVIEN = "NhanVien";
    private static final String TABLE_QLYBAN = "QLyBan";
    private static final String TABLE_QLYORDER = "QLyOrder";
    private static final String TABLE_LOAIMON = "LoaiMon";

    // Employee Table Columns
    private static final String NHANVIEN_ID = "id";
    private static final String NHANVIEN_LASTNAME = "lasttName";
    private static final String NHANVIEN_FIRSTNAME = "firstName";
    private static final String NHANVIEN_QLYORDER_ID_FK = "qlyorderID";

    //Cot Bang Quan Ly Mon
    private static final String QLYMON_ID = "id";
    private static final String QLYMON_TENMON = "TenMon";

    //Cot Quan Ly Ban
    private static final String QLYBAN_ID = "id";
    private static final String QLYBAN_LOAIMON_ID_FK = "qlybanID";

    //Cot Loai Mon
    private static final String LOAIMON_ID = "id";
    private static final String LOAIMON_TENLOAIMON = "TenLoaiMon";
    private static final String LOAIMON_QLYMON_ID_FK = "qlymonID";

    //Cot QLyOrder
    private static final String QLYORDER_ID = "id";

    public AppCafe(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QLYMON_TABLE = "CREATE TABLE " + TABLE_QLYMON +
                "(" +
                QLYMON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                QLYMON_TENMON + " TEXT" +
                ")";

        String CREATE_NHANVIEN_TABLE = "CREATE TABLE " + TABLE_NHANVIEN +
                "(" +
                NHANVIEN_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                NHANVIEN_FIRSTNAME + " TEXT," +
                NHANVIEN_LASTNAME + " TEXT," +
                NHANVIEN_QLYORDER_ID_FK + " INTEGER CONSTRAINT FK_DEPARTMENT REFERENCES " + TABLE_QLYORDER + " ON UPDATE CASCADE" + // Define a foreign key
                ")";
        String CREATE_QLYBAN_TABLE = "CREATE TABLE " + TABLE_QLYBAN +
                "(" +
                QLYBAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                QLYBAN_LOAIMON_ID_FK + " INTEGER CONSTRAINT FK_DEPARTMENT REFERENCES " + TABLE_LOAIMON + " ON UPDATE CASCADE" +
                ")";
        String CREATE_LOAIMON_TABLE = "CREATE TABLE " + TABLE_LOAIMON +
                "(" +
                LOAIMON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LOAIMON_TENLOAIMON + "TEXT" +
                LOAIMON_QLYMON_ID_FK + " INTEGER CONSTRAINT FK_DEPARTMENT REFERENCES " + TABLE_QLYMON + " ON UPDATE CASCADE" +
                ")";
        String CREATE_QLYORDER_TABLE = "CREATE TABLE " + TABLE_QLYORDER +
                "(" +
                QLYORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                ")";

        db.execSQL(CREATE_QLYMON_TABLE);
        db.execSQL(CREATE_NHANVIEN_TABLE);
        db.execSQL(CREATE_QLYBAN_TABLE);
        db.execSQL(CREATE_LOAIMON_TABLE);
        db.execSQL(CREATE_QLYORDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QLYMON);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NHANVIEN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QLYBAN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QLYORDER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOAIMON);
            onCreate(db);
        }

    }
}
