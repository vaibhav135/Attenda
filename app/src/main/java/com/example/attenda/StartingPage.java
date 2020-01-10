package com.example.attenda;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class StartingPage extends AppCompatActivity {
    Button btn;
    String data , getdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);
        Intent intent = getIntent();
        SharedPreferences.Editor editor = getSharedPreferences("your_app", Context.MODE_PRIVATE).edit();
        editor.putBoolean("reached_c", true);
        editor.commit();
        init();
    }

    public  void init(){


        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        TimeZone India = TimeZone.getTimeZone("Asia/Kolkata");
        sdf.setTimeZone(India);
        data = sdf.format(new Date());


        final Activity activity = this;
        btn = (Button)findViewById(R.id.scanBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setPrompt("scanning");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();

                sentData();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if(result.getContents() == null)
                 Toast.makeText(this, "you have terminated the process", Toast.LENGTH_SHORT).show();
            else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                getdata = result.getContents();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void sentData(){
            if(data == getdata){
               //code to save data to realtime database will be written here


            }
            else{
                Toast.makeText(this,"Try again...",Toast.LENGTH_SHORT).show();
            }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.profile:

                return true;

            case R.id.settings:
                startActivity(new Intent(StartingPage.this,Settings.class


                ));

                return true;

            case R.id.about:

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }



    }
}
