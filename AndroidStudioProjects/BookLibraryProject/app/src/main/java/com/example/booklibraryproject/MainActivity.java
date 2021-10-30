package com.example.booklibraryproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper helper;
    ArrayList<String> bookId,bookName,bookAuthor,bookPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        add_button=findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        helper=new MyDatabaseHelper(this);
        bookId=new ArrayList<>();
        bookName=new ArrayList<>();
        bookAuthor=new ArrayList<>();
        bookPages=new ArrayList<>();

        storeDataInArrays();

        CustomAdapter customAdapter=new CustomAdapter(MainActivity.this,this,bookId,bookName,bookAuthor,bookPages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();        //Restarts the app
        }
    }

    void storeDataInArrays(){
        Cursor cursor=helper.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No rows", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                bookId.add(cursor.getString(0));
                bookName.add(cursor.getString(1));
                bookAuthor.add(cursor.getString(2));
                bookPages.add(cursor.getString(3));
            }
        }
    }
}