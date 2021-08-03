package com.jaxadev.sqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MyDatabaseHelper myDatabaseHelper;
    ArrayList<Book> books;

    BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.add_btn);
        recyclerView = findViewById(R.id.recycler_view);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddActivity.class);

                startActivity(intent);

            }
        });

        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);

        books = new ArrayList<>();

        storeDataInArray();

        bookAdapter = new BookAdapter(books, new BookAdapter.ItemClickListener() {
            @Override
            public void onClick(Book book) {

                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra(getString(R.string.id_key), book.id);
                intent.putExtra(getString(R.string.title_key), book.title);
                intent.putExtra(getString(R.string.author_key), book.author);
                intent.putExtra(getString(R.string.pages_key), book.pages);
                startActivity(intent);

            }
        });

        recyclerView.setAdapter(bookAdapter);

        bookAdapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void storeDataInArray() {

        Cursor cursor = myDatabaseHelper.readAllData();

        if (cursor.getCount() != 0) {

            while (cursor.moveToNext()) {

                books.add(new Book(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));

            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.delete_all: confirmDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete All?")
                .setMessage("Are you sure you want to delete all data ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MyDatabaseHelper myDb = new MyDatabaseHelper(MainActivity.this);
                        myDb.deleteAllData();
                        recreate();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();


    }

}