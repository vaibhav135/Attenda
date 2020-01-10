package com.example.attenda;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private TextView Clicktxt;
    private EditText signInEmail , signInPassword;
    private ProgressBar progressBar;
    private Button btn;
    private FirebaseAuth mAuth;
    SignInButton googlebtn;
    googleLogin gclass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitializeUI();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserAccount();
            }
        });

        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gclass.signIn();
            }
        });
    }

    public void createAccount(){
        Clicktxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this , Register.class));
            }
        });
    }

    private void loginUserAccount() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = signInEmail.getText().toString();
        password = signInPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(login.this, StartingPage.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }


    public void InitializeUI(){
        signInEmail = findViewById(R.id.signINemail);
        signInPassword = findViewById(R.id.signINpassword);
        Clicktxt = findViewById(R.id.createAccount);
        btn = findViewById(R.id.signInBtn);
        gclass = new googleLogin();
        googlebtn = findViewById(R.id.sign_in_button);

        createAccount();
    }
}
