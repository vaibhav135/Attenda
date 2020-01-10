package com.example.attenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText  email , createPass , confirmPass;
    private Button but;
    private ProgressBar progress;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        initializeUI();

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });

    }

    private void registerNewUser() {
        progress.setVisibility(View.VISIBLE);

        String Email , password , confirmPassword ;
        Email  = email.getText().toString();
        password = createPass.getText().toString();
        confirmPassword = confirmPass.getText().toString();

        if(TextUtils.isEmpty(Email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password...", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(getApplicationContext(),"Please enter password again to confirm...",Toast.LENGTH_LONG).show();
            return;
        }

        if(password != confirmPassword){
            Toast.makeText(getApplicationContext(),"password doesn't match....",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.GONE);

                    Intent intent = new Intent(Register.this ,MainActivity.class );
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initializeUI(){
        email = findViewById(R.id.createEmail);
        but = findViewById(R.id.submitbut);
        createPass = findViewById(R.id.createPassword);
        confirmPass = findViewById(R.id.confirmPassword);
        progress = findViewById(R.id.progressBar);
    }


}
