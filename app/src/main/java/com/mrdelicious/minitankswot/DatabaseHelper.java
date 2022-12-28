package com.mrdelicious.minitankswot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_PATH = "/data/data/com.mrdelicious.minitankswot/databases/";

    private static final String TAG = "DatabaseHelper";
    private SQLiteDatabase db;
    private final Context context;

    public DatabaseHelper(@Nullable Context context, String db_name) {
        super(context, db_name, null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    @Override
    public synchronized void close(){
        if(db!=null){
            db.close();
        }
        super.close();
    }
    private boolean checkDataBase(String db_name) {
        SQLiteDatabase tempDB = null;
        try {
            String myPath = DB_PATH + db_name;
            tempDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            Log.e("checkDataBase", e.getMessage());
        }
        boolean dbExists = tempDB != null;
        if (dbExists)
            tempDB.close();
        Log.i("dbHelper", "db exists: " + dbExists);
        return dbExists;
    }
    public void copyDataBase(String db_name) throws IOException {
        try {
            InputStream myInput = context.getAssets().open("databases/"+db_name);
            String outputFileName = DB_PATH + db_name;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;

            while((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
            Log.i("dbHelper", "copied db to: " + outputFileName);
        } catch (Exception e) {
            Log.e("copyDatabase", e.getMessage());
        }

    }
    public void openDataBase(String db_name) throws SQLException {
        String myPath = DB_PATH + db_name;
        Log.i("dbHelper", "opening db at: " + myPath);
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public void createDataBase(String db_name) throws IOException {
        Log.d(TAG, "createDataBase() called with: db_name = [" + db_name + "]");
        boolean dbExist = checkDataBase(db_name);

        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDataBase(db_name);
            } catch (IOException e) {
                Log.e("createDataBase", e.getMessage());
            }
        }
    }
    public List<String> getAllFromDatabase(String db_name, String table){
        List<String> listCrits = new ArrayList<>();
        openDataBase(db_name);
        Cursor c;

        try {
            c = db.rawQuery("SELECT * FROM " + table , null);
            if(c == null) return null;

            String name;
            c.moveToFirst();
            do {
                name = c.getString(1);
                listCrits.add(name);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.e("getAllCrits", e.getMessage());
        }

        db.close();

        return listCrits;
    }
}