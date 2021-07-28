package com.jaxadev.sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    ArrayList<Book> books;

    public BookAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {

        Book book = books.get(position);

        holder.pages.setText(book.pages);
        holder.author.setText(book.author);
        holder.title.setText(book.title);
        holder.id.setText(book.id);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, title, author, pages;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.tv_book_id);
            title = itemView.findViewById(R.id.tv_book_title);
            author = itemView.findViewById(R.id.tv_book_author);
            pages = itemView.findViewById(R.id.tv_book_pages);

        }
    }

}
