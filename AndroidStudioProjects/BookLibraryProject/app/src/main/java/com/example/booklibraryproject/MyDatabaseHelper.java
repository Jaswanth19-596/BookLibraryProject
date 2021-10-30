package com.example.booklibraryproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME="BookLibrary.db";
    public static final int DATABASE_VERSION=1;

    public static final String TABLE_NAME="my_library";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_TITLE="book_title";
    public static final String COLUMN_AUTHOR="book_author";
    public static final String COLUMN_PAGES="book_pages";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE "+TABLE_NAME+ " ( "+COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                COLUMN_TITLE+" TEXT, "+
                                COLUMN_AUTHOR+" TEXT, "+
                                COLUMN_PAGES+" INTEGER );";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addBook(String title,String author,int pages){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_AUTHOR,author);
        values.put(COLUMN_PAGES,pages);

        long row=db.insert(TABLE_NAME,null,values);

        if(row==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }


    Cursor readAllData(){

        String query = "Select * from "+TABLE_NAME;

        SQLiteDatabase db=this.getReadableDatabase();

        //Stores the values
        Cursor cursor=null;

        //If database is not null
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }else{
            Toast.makeText(context, "No items", Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }


    void updateData(String row,String title,String author,String pages){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_ID,row);
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_AUTHOR,author);
        values.put(COLUMN_PAGES,pages);
        long rowUpdated=db.update(TABLE_NAME,values,"_id=?",new String[]{row});
        if(rowUpdated==-1){
            Toast.makeText(context, "UnSuccesfull", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Succesfull "+row, Toast.LENGTH_SHORT).show();

        }

    }
}
