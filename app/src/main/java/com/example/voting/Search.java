package com.example.voting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Search extends AppCompatActivity {

    Button button;
    EditText editText;
    EditText editText2;
    String name;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_vote_history);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = editText.getText().toString();
                date = editText2.getText().toString();


                Intent intent = new Intent(Search.this, DisplaySearch.class);
                intent.putExtra("STRING",name);
                intent.putExtra("DATE",date);
                startActivity(intent);


            }

        });
    }
}
