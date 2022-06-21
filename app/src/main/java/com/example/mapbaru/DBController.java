package com.example.mapbaru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DBController extends SQLiteOpenHelper {
    public DBController(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.DB", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE TEMPAT(ID INTEGER PRIMARY KEY AUTOINCREMENT, Tempat TEXT UNIQUE, LOKASI TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOKASI;");
        onCreate(sqLiteDatabase);
    }

    public void insert_datamap(String Tempat, String Lokasi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEMPAT", Tempat);
        contentValues.put("LOKASI", Lokasi);
        this.getWritableDatabase().insertOrThrow("TEMPAT","",contentValues);
    }

    public void delete_datamap(String Tempat){
        this.getWritableDatabase().delete("TEMPAT","Tempat='"+Tempat+"'",null);
    }

    public void update_datamap(String old_Tempat, String new_Tempat){
        this.getWritableDatabase().execSQL("UPDATE TEMPAT SET Tempat='"+new_Tempat+"' WHERE Tempat='"+old_Tempat+"'");
    }

    public void list_all_datamap(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Tempat", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }
    }

}
