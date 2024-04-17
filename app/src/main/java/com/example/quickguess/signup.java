package com.example.quickguess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    EditText signupemail;
    EditText signuppass;
    Button signupsignup;
    ProgressBar progressBar;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupemail = findViewById(R.id.editTextsignupemail);
        signuppass = findViewById(R.id.editTextsignuppass);
        signupsignup = findViewById(R.id.buttonsignupsignup);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        signupsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupsignup.setClickable(false);
                String userEmail = signupemail.getText().toString();
                String userpassword = signuppass.getText().toString();
                signupFirebase(userEmail, userpassword);

            }
        });

    }

    public void signupFirebase(String userEmail, String userpassword) {
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(userEmail, userpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signup.this, "Your Account is Created", Toast.LENGTH_LONG).show();
                            finish();
                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(signup.this, "Your Account Not Created", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}