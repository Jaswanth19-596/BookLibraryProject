package com.example.booklibraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText title_input;
    private EditText author_input;
    private EditText pages_input;
    private Button addButton;
//    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input=findViewById(R.id.title_input);
        author_input=findViewById(R.id.author_input);
        pages_input=findViewById(R.id.pages_input);
        addButton=findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper helper=new MyDatabaseHelper(AddActivity.this);
                String title=title_input.getText().toString().trim();
                String author=author_input.getText().toString().trim();
                int pages=Integer.parseInt(pages_input.getText().toString());

                helper.addBook(title,author,pages);


            }
        });
    }
}