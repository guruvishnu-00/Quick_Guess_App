package com.example.quickguess;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class loginpage extends AppCompatActivity {

    EditText email;
    EditText Password;
    Button signin;
    Button signup;
    SignInButton SigninGoogle;
    TextView forgotpass;
    ProgressBar progressBarM;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        email = findViewById(R.id.editTextemainM);
        Password = findViewById(R.id.editTextloginpassm);
        signin = findViewById(R.id.buttonSignin);
        signup = findViewById(R.id.buttonsingup);
        SigninGoogle = findViewById(R.id.signInButtonGoogle);
        forgotpass = findViewById(R.id.textViewforgotpassm);
        progressBarM = findViewById(R.id.progressBar3);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userpassword = Password.getText().toString();
                signInWithFirebase(userEmail, userpassword);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginpage.this, signup.class);
                startActivity(i);

            }
        });
        SigninGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signInGoogle();


            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(loginpage.this, forgotpassword.class);
                startActivity(in);

            }
        });
    }

    public void signInWithFirebase(String userEmail, String userPassword) {
        progressBarM.setVisibility(View.VISIBLE);
        signin.setClickable(false);
        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(loginpage.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                            progressBarM.setVisibility(View.INVISIBLE);
                            Toast.makeText(loginpage.this, "Sign In Successful", Toast.LENGTH_LONG).show();
                            signin.setClickable(true);
                        } else {
                            Toast.makeText(loginpage.this, "Sign In NOT Successful", Toast.LENGTH_LONG).show();
                            signin.setClickable(true);
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(loginpage.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signInGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        signIn();
    }


    public void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            firebaseSignInWithGoogle(task);
        }
    }

    private void firebaseSignInWithGoogle(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Toast.makeText(loginpage.this, "signed into google successfully", Toast.LENGTH_LONG).show();
            Intent main = new Intent(loginpage.this, MainActivity.class);
            startActivity(main);
            finish();
            firebaseGoogleAccount(account);
        } catch (ApiException e) {
            e.printStackTrace();
            Toast.makeText(loginpage.this, "signin to google successfully", Toast.LENGTH_LONG).show();
            Intent main = new Intent(loginpage.this, MainActivity.class);
            startActivity(main);
            finish();

        }

    }

    private void firebaseGoogleAccount(GoogleSignInAccount account) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                    } else {

                    }

                }
            }
        });
    }
}