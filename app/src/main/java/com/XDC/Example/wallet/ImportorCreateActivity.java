package com.XDC.Example.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.R;


public class ImportorCreateActivity extends AppCompatActivity {

    private TextView btn_importaccount,btn_createaccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_importaccount);


        btn_createaccount = (TextView)findViewById(R.id.btn_createaccount);
        btn_importaccount = (TextView)findViewById(R.id.btn_importaccount);






        btn_createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImportorCreateActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        btn_importaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImportorCreateActivity.this, Importwallet.class);
                startActivity(intent);
            }
        });


    }




}