package com.example.attenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class more_info extends AppCompatActivity{
    Spinner spinner1 , spinner2;
    EditText edt1 , edt2 , edt3;
    Button btn;
    DataHelper Mydb;
    String name , rollno , gender ,sem , dob , skill , course , activity;

    private static final String TAG = "more_info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        rollno = intent.getStringExtra("rollno");
        gender = intent.getStringExtra("gender");

        edt1 = (EditText)findViewById(R.id.etSem);
        edt2 = (EditText)findViewById(R.id.etSkills);
        edt3 = (EditText)findViewById(R.id.etDob);


        edt1.addTextChangedListener(logincheck);
        edt2.addTextChangedListener(logincheck);
        edt3.addTextChangedListener(logincheck);

        spinner1 = (Spinner)findViewById(R.id.sp1);
        spinner2 = (Spinner)findViewById(R.id.sp2);

        btn = (Button)findViewById(R.id.save);

        Mydb = new DataHelper(this);
        init();
    }

    private TextWatcher logincheck = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            sem = edt1.getText().toString();
            skill = edt2.getText().toString();
            dob = edt3.getText().toString();

            btn.setEnabled(!sem.isEmpty()&&!skill.isEmpty()&&!dob.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void init(){

        ArrayAdapter<CharSequence> arrayAdapter1 =  ArrayAdapter.createFromResource(this,R.array.Course,android.R.layout.simple_spinner_item);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(this,R.array.activities,android.R.layout.simple_spinner_item);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(arrayAdapter1);
        spinner2.setAdapter(arrayAdapter2);

        course = spinner1.getSelectedItem().toString();
        activity = spinner2.getSelectedItem().toString();



        //Save data in SQLite


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean ifsaved = Mydb.insertData(name,rollno,gender,sem,skill,dob,course ,activity);
                if(ifsaved == true){
                    Toast.makeText(more_info.this,"saved",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(more_info.this,"not saved",Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(more_info.this, StartingPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }


}

