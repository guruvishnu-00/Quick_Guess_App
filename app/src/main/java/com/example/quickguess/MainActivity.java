package com.example.quickguess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button Add;
    Button Multi;
    Button Sub;
    TextView logout;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Add = findViewById(R.id.buttonAdd);
        Sub = findViewById(R.id.buttonSub);
        Multi = findViewById(R.id.buttonMulti);
        logout = findViewById(R.id.textViewlogout);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,adds.class);
                startActivity(intent);
                finish();
            }
        });
        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,sub.class);
                startActivity(intent);
                finish();
            }
        });

        Multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,multi.class);
                startActivity(intent);
                finish();

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent i = new Intent(MainActivity.this,loginpage.class);
                startActivity(i);
                finish();
            }
        });


    }
}