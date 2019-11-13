package com.example.attenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edt1 , edt2;
    private Button bt1 , bt2;
    private RadioGroup rg;
    String Name , rollNo , gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = (EditText)findViewById(R.id.etName);
        edt2 = (EditText)findViewById(R.id.etRollNo);
        bt1 = (Button)findViewById(R.id.button1);
        bt2 = (Button)findViewById(R.id.button2);

        edt1.addTextChangedListener(loginText);
        edt2.addTextChangedListener(loginText);

        SharedPreferences mPref = getSharedPreferences("your_app", Context.MODE_PRIVATE);
        if(mPref.getBoolean("reached_c",false)){
            Intent intent = new Intent(MainActivity.this,StartingPage.class);

            startActivity(intent);
            finish();
        }


        but();
    }

    private TextWatcher loginText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Name = edt1.getText().toString();
            rollNo =  edt2.getText().toString();

            bt1.setEnabled(!Name.isEmpty() && !rollNo.isEmpty());
            bt2.setEnabled(!Name.isEmpty() && !rollNo.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    public void but() {

        //this functionality is added in order to save the data in our profile

        rg = (RadioGroup) findViewById(R.id.radio);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.male) {
                    gender = "male";
                } else if (i == R.id.female) {
                    gender = "female";
                } else {
                    gender = "other";
                }
            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, more_info.class);
                intent.putExtra("name",Name);
                intent.putExtra("rollno",rollNo);
                intent.putExtra("gender",gender);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, StartingPage.class);
                startActivity(intent2);
                finish();
            }
        });

    }

}
