package com.example.booklibraryproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList bookId,bookName,bookAuthor,bookPages;
    Activity activity;

    CustomAdapter(Activity activity,Context context,ArrayList bookId,ArrayList bookName,ArrayList bookAuthor,ArrayList bookPages){
            this.context=context;
            this.bookId=bookId;
            this.bookName=bookName;
            this.bookAuthor=bookAuthor;
            this.bookPages=bookPages;
            this.activity=activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
            holder.book_id.setText(String.valueOf(bookId.get(position)));
            holder.book_name.setText(String.valueOf(bookName.get(position)));
            holder.book_author.setText(String.valueOf(bookAuthor.get(position)));
            holder.book_pages.setText(String.valueOf(bookPages.get(position)));
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,UpdateActivity.class);
                    intent.putExtra("id", String.valueOf(bookId.get(holder.getAdapterPosition())));
                    intent.putExtra("name", String.valueOf(bookName.get(holder.getAdapterPosition())));
                    intent.putExtra("author", String.valueOf(bookAuthor.get(holder.getAdapterPosition())));
                    intent.putExtra("pages", String.valueOf(bookPages.get(holder.getAdapterPosition())));
                    activity.startActivityForResult(intent,1);
                }
            });
    }

    @Override
    public int getItemCount() {
        return bookId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView book_id,book_name,book_author,book_pages;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id=itemView.findViewById(R.id.bookId);
            book_name=itemView.findViewById(R.id.bookName);
            book_author=itemView.findViewById(R.id.bookAuthor);
            book_pages=itemView.findViewById(R.id.bookPages);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
