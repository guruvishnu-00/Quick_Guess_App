package com.example.quickguess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    TextView result;
    Button again;
    Button exit;
    int Score ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.textViewresultend);
        exit = findViewById(R.id.buttonexit);
        again = findViewById(R.id.buttonagain);
        Intent intent = getIntent();
        Score = intent.getIntExtra("Score",0);
        String userScore = String.valueOf(Score);
        result.setText("Your Score : "+userScore);

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(result.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}