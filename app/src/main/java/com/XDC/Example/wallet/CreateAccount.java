package com.XDC.Example.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.Example.MainActivity;
import com.XDC.Example.profile.UserprofileActivity;
import com.XDC.Example.utils.SharedPreferenceHelper;
import com.XDC.R;
import com.XDCJava.Model.WalletData;
import com.XDCJava.XDC20Client;
import com.XDCJava.callback.CreateAccountCallback;
import com.google.gson.Gson;

import java.io.File;

public class CreateAccount extends AppCompatActivity {
    EditText edt_password, edt_confirmpassword;
    Button btn_createacc;
    TextView txt_info;
    ImageView img_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_confirmpassword = (EditText) findViewById(R.id.edt_confirmpassword);
        btn_createacc = (Button) findViewById(R.id.btn_createacc);
        txt_info = (TextView) findViewById(R.id.txt_info);
        img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount.this.finish();
            }
        });

        btn_createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_password.getText().toString().length() > 0) {
                    if (edt_confirmpassword.getText().toString().length() > 0) {

                        if (edt_password.getText().toString().equalsIgnoreCase(edt_confirmpassword.getText().toString())) {


                            try {

                                File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES.toString() +
                                        File.separator + "web3j");
                                path.mkdir();

                                XDC20Client.getInstance().generateWallet(path, edt_password.getText().toString(), new CreateAccountCallback() {
                                    @Override
                                    public void success(WalletData walletData)
                                    {

                                        txt_info.setText("Account address: " + walletData.getAccountAddress() + "\n" + "privateKey: " + walletData.getPrivateKey() +
                                                "\n" + "Seed Phrase: " + walletData.getSeedPhrase());
                                        Gson gson = new Gson();
                                        String json = gson.toJson(walletData);
                                        SharedPreferenceHelper.setSharedPreferenceString(CreateAccount.this, "userprofile", json);

                                       /* Intent intent = new Intent(CreateAccount.this, UserprofileActivity.class);
                                        startActivity(intent);*/
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
                            Toast.makeText(CreateAccount.this, "Password Does not match", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(CreateAccount.this, "Please Confirm Password", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CreateAccount.this, "Please Enter Password", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}