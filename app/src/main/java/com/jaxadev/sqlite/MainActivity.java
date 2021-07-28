package com.jaxadev.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

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

        bookAdapter = new BookAdapter(books);

        recyclerView.setAdapter(bookAdapter);

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

}