package com.example.admin.recipedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DBNAME = "Receipifinal.db";
    private static String DBPATH = "";
    String TableMain = "Receipi";
    private SQLiteDatabase mDatabase;
    private final Context mContext;

    public DatabaseHelper(Context mContext) {
        super(mContext, DBNAME, null, 1);
        this.mContext = mContext;

        DBPATH = "/data/data/" + mContext.getPackageName() + "/databases/";
        Log.e("path of db: ", DBPATH);
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();

        if (dbExist) {
            // Already Exist
        } else {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (Exception e) {
                throw new Error("Error copying Database");
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try {
            String mypath = DBPATH + DBNAME;
            checkDB = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDatabase() {
        byte[] buffer = new byte[1024];

        OutputStream myOutput = null;
        int length;

        InputStream myInput = null;
        try {
            myInput = mContext.getAssets().open(DBNAME);
            myOutput = new FileOutputStream(DBPATH + DBNAME);

            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDatabase() {
        String myPath = DBPATH + DBNAME;
        try {
            mDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cursor getCategory() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT DISTINCT menu FROM '" + TableMain + "'", null);
        return res;
    }

    public Cursor CategoryData(String menu) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM '" + TableMain + "' WHERE menu=?", new String[]{menu});
        return res;
    }

    public void Update(int fav_value, String submenu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("fav", fav_value);

        db.update(TableMain, contentValues, "submenu=?", new String[]{submenu});
    }

    public Cursor getFavourite() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM '" + TableMain + "' WHERE fav=1 ORDER BY submenu DESC", null);
        return res;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

