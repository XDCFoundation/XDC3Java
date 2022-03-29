package com.XDC.Example.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.Example.SplashScreen;
import com.XDC.Example.profile.UserprofileActivity;
import com.XDC.Example.utils.SharedPreferenceHelper;
import com.XDC.R;
import com.XDCJava.Model.WalletData;
import com.XDCJava.XDC20Client;
import com.XDCJava.callback.CreateAccountCallback;
import com.google.gson.Gson;

import java.io.File;

public class Importwallet extends AppCompatActivity {
    EditText edt_password,edt_confirmpassword,edt_recovery;
    Button btn_importaccount;
    TextView txt_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_account);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_confirmpassword = (EditText)findViewById(R.id.edt_confirmpassword);
        btn_importaccount = (Button) findViewById(R.id.btn_importaccount);
        txt_info = (TextView) findViewById(R.id.txt_info);
        edt_recovery = findViewById(R.id.edt_recovery);

        btn_importaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_recovery.getText().toString().length() > 0) {
                    if (edt_password.getText().toString().length() > 0) {
                        if (edt_confirmpassword.getText().toString().length() > 0) {

                            if (edt_password.getText().toString().equalsIgnoreCase(edt_confirmpassword.getText().toString())) {


                                try {

                                    File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES.toString() +
                                            File.separator + "web3j");
                                    path.mkdir();

                                    XDC20Client.getInstance().importWallet(edt_recovery.getText().toString(), edt_password.getText().toString(),path, new CreateAccountCallback() {
                                        @Override
                                        public void success(WalletData walletData) {

                                            txt_info.setText("Account address: " + walletData.getAccountAddress() + "\n" + "privateKey: " + walletData.getPrivateKey());
                                            Gson gson = new Gson();
                                            String json = gson.toJson(walletData);
                                            SharedPreferenceHelper.setSharedPreferenceString(Importwallet.this, "userprofile", json);

                                            new Handler().postDelayed(new Runnable()
                                            {
                                                @Override
                                                public void run()
                                                {



                                                        startActivity(new Intent(Importwallet.this, UserprofileActivity.class));
                                                        finish();



                                                }
                                            }, 500);
                                        }

                                        @Override
                                        public void failure(Throwable t) {
                                            txt_info.setText(t.getMessage());
                                        }

                                        @Override
                                        public void failure(String message) {
                                            txt_info.setText(message);

                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(Importwallet.this, "Password Does not match", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Importwallet.this, "Please Confirm Password", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Importwallet.this, "Please Enter Password", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(Importwallet.this, "Please Enter Recovery Phrase", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}