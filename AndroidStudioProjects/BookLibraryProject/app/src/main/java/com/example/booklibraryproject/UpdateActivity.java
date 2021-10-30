package com.example.booklibraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    String strId,strTitle,strAuthor,strPages;
    EditText title,author,pages;
    Button updateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title=findViewById(R.id.title_input2);
        author=findViewById(R.id.author_input2);
        pages=findViewById(R.id.pages_input2);
        updateButton=findViewById(R.id.updateButton);

        getIntentData();
        //Retrieving data through intent
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper db=new MyDatabaseHelper(UpdateActivity.this);
                strTitle=title.getText().toString();
                strAuthor=author.getText().toString();
                strPages=pages.getText().toString();
                db.updateData(strId,strTitle,strAuthor,strPages);
            }
        });
    }


    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            //Getting data
            strId= getIntent().getStringExtra("id");
            strTitle= getIntent().getStringExtra("name");
            strAuthor= getIntent().getStringExtra("author");
            strPages= getIntent().getStringExtra("pages");

            //Setting data
            title.setText(strTitle);
            author.setText(strAuthor);
            pages.setText(strPages);

        }else{
            Toast.makeText(this, "Doesnt get enough values", Toast.LENGTH_SHORT).show();
        }
    }

}