package com.example.churmo.choys;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    //VERSION DE LA BASE DE DATOS
    public static final int DATABASE_VERSION = 1;

    //CREACION DE LA BASE DE DATOS
    public static final String DATABASE_NAME = "usuariosChoys";

    //CREACION DE LA TABLA USUARIOS
    private static final String TABLE_CREATE =
            "CREATE TABLE usuarios" +
                    "(" +
                    "id TEXT PRIMARY KEY," +
                    "first_name TEXT," +
                    "last_name TEXT," +
                    "email TEXT," +
                    "avatar TEXT" +
                    ")";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
