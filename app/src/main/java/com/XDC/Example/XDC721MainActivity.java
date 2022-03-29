package com.XDC.Example;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.R;
import com.XDCJava.Model.Token721DetailsResponse;
import com.XDCJava.XDC721Client;
import com.XDCJava.callback.Token721DetailCallback;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;


public class XDC721MainActivity extends AppCompatActivity {

    private Button submit_button,deploy_contract;
    TextView enterXdcAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main721);

        enterXdcAddress = findViewById(R.id.enter_xdc_address);
        submit_button = findViewById(R.id.submit);
        deploy_contract = findViewById(R.id.deploy_contract);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(enterXdcAddress.getText().toString()!=null && enterXdcAddress.getText().toString().length()>0)
                {
                    XDC721Client.getInstance().getTokenoinfo(enterXdcAddress.getText().toString(), new Token721DetailCallback() {
                        @Override
                        public void success(Token721DetailsResponse tokenDetailsResponse) {
                            Intent intent = new Intent(XDC721MainActivity.this, Details721.class);
                            intent.putExtra("tokendetail",(Serializable) tokenDetailsResponse);
                            startActivity(intent);
                        }

                        @Override
                        public void success(String message) {

                        }

                        @Override
                        public void failure(Throwable t) {

                        }

                        @Override
                        public void failure(String message) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(XDC721MainActivity.this, "Please Enter Token Address", Toast.LENGTH_LONG).show();
                }





            }
        });




        deploy_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //85ca400ea23a5a72f9848a21b7e9a84649fd405365fcb592f429dd41ad5432a9
                //0x3694005b865eb2deaae8e5efc73451a1c4d8f290dbce27a5614c51139144b18c
                try {
                    XDC721Client.getInstance().deploy_NFT("5724b3006f227d6a8efac9d9310beec7874cf0dcdc12e5d0c4890d2ba497b9c6", new Token721DetailCallback() {
                        @Override
                        public void success(Token721DetailsResponse tokenApiModel)
                        {

                        }

                        @Override
                        public void success(String message)
                        {
                            Log.e("message",message);
                            enterXdcAddress.setText(message);
                        }

                        @Override
                        public void failure(Throwable t) {

                        }

                        @Override
                        public void failure(String message) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

               /* try {
                    //XDC721Client.getInstance().mintToken("0x0c0d7D6a5c174Da1CE0912836028f347bD4C2157");
                }
                */



            }
        });



    }




}