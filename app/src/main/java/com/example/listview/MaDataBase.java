package com.example.listview;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MaDataBase extends SQLiteOpenHelper {
    public static final String BASE_NAME = "BaseData2.db";
    public static final int BASE_VERSION = 1;
    public static final String NOM_TABLE = "T_product";
    public static final String COL0 = "idProduct";
    public static final String COL1 = "TITLE";
    public static final String COL2 = "DESCRIPTION";
    public static  final String COL3 = "IMAGE";


    public MaDataBase(Context context) {
        super(context, BASE_NAME, null, BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE " + NOM_TABLE + " ("
                + COL0 + " integer primary key autoincrement,"
                + COL1 + " text not null,"
                + COL2 + " text not null,"
                + COL3 + " text not null" + ");";
        Log.d("Database", "strSql: " + strSql);
        db.execSQL(strSql);
        Log.d("Database", "Création de la base de données OK " + NOM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSql = "DROP TABLE IF EXISTS " + NOM_TABLE + ";";
        Log.d("DataBase", "requête sql ds Upgrade" + strSql);
        db.execSQL(strSql);
        Log.d("DataBase", "Méthode Upgrade Call: " + NOM_TABLE);
    }

    public void insertionPRODUCTS(String TITLE, String DESCRIPTION, String IMAGE) {
        //On ne peut pas avoir  de simple cote dans une chaine de caractère
        TITLE = TITLE.replace("'", " ");
        //On les remplace donc par des espaces
        DESCRIPTION = DESCRIPTION.replace("'", " ");

        //pas ID Il est autoincrement
        String strSql = "INSERT INTO " + NOM_TABLE + "(" + COL1 + "," + COL2 + "," + COL3 + ")"
                + " values ('" + TITLE + "','" + DESCRIPTION + "','" + IMAGE + "'" + ");";

        Log.d("DataBase", "StrSql insert: " + strSql);
        getWritableDatabase().execSQL(strSql);
        Log.d("DataBase", "insertionPRODUCTS, insertion OK");


    }

    public Cursor lireTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor monCurseur = db.rawQuery("Select * from " + NOM_TABLE, null);
        return monCurseur;
    }
}
