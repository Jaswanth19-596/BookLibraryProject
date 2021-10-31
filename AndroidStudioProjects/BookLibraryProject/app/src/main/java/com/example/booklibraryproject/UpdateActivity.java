package com.example.booklibraryproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    String strId,strTitle,strAuthor,strPages;
    EditText title,author,pages;
    Button updateButton,deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title=findViewById(R.id.title_input2);
        author=findViewById(R.id.author_input2);
        pages=findViewById(R.id.pages_input2);
        updateButton=findViewById(R.id.updateButton);
        deleteButton=findViewById(R.id.deleteButton);

        //Retrieving data through intent and sets the data to the global variables
        getIntentData();

        //To set the action bar
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle(strTitle);
        }

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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
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

    void showAlertDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+strTitle+" ?");
        builder.setMessage("Are you sure you want to delete "+strTitle+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper db=new MyDatabaseHelper(UpdateActivity.this);
                db.deleteData(strId);
                finish();        //Directly takes you back to the main Activity
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}