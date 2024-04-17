package com.example.quickguess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    EditText email1;
    Button button;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ProgressBar progressBarreset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        email1 = findViewById(R.id.edittextresetpass);
        button = findViewById(R.id.buttoncontinue);
        progressBarreset = findViewById(R.id.progressBarforgot);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email1.getText().toString();
                resetPassword(userEmail);

            }
        });
    }
    public void resetPassword(String userEmail){
        progressBarreset.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(userEmail)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(forgotpassword.this,"We Sent Email to reset the Password",Toast.LENGTH_LONG).show();
                            progressBarreset.setVisibility(View.INVISIBLE);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(forgotpassword.this,"Sorry try agin after some time",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}