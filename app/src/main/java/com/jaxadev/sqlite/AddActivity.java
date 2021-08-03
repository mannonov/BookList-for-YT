package com.jaxadev.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText edtTitle, edtAuthor, edtPages;

    Button addButton;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtAuthor = findViewById(R.id.item_author);
        edtPages = findViewById(R.id.item_pages);
        edtTitle = findViewById(R.id.item_title);
        addButton = findViewById(R.id.add_book_btn);

        myDatabaseHelper = new MyDatabaseHelper(AddActivity.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDatabaseHelper.addBook(edtTitle.getText().toString(),edtAuthor.getText().toString(),Integer.parseInt(edtPages.getText().toString()));
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                //CRUD
                // C - Create
            }
        });

    }
}