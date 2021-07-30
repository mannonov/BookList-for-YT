package com.jaxadev.sqlite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button update_button;

    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        update_button = findViewById(R.id.update_button);

        getAndSetIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(UpdateActivity.this);

                title = title_input.getText().toString();
                author = author_input.getText().toString();
                pages = pages_input.getText().toString();
                myDatabaseHelper.updateData(id, title, author, pages);

            }
        });

    }

    void getAndSetIntentData() {

        if (getIntent().hasExtra(getString(R.string.id_key)) && getIntent().hasExtra(getString(R.string.title_key)) &&
                getIntent().hasExtra(getString(R.string.author_key)) && getIntent().hasExtra(getString(R.string.pages_key))) {

            id = getIntent().getStringExtra(getString(R.string.id_key));
            title = getIntent().getStringExtra(getString(R.string.title_key));
            author = getIntent().getStringExtra(getString(R.string.author_key));
            pages = getIntent().getStringExtra(getString(R.string.pages_key));

            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_LONG).show();
        }

    }

}