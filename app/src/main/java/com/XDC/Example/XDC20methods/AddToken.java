package com.XDC.Example.XDC20methods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.Example.profile.UserprofileActivity;
import com.XDC.Example.utils.SharedPreferenceHelper;
import com.XDC.R;
import com.XDCJava.Model.TokenDetailsResponse;
import com.google.gson.Gson;

import java.math.BigInteger;

public class AddToken extends AppCompatActivity {

     EditText edttoken_address , edt_tknsymbol,edt_tkndec;
     Button btn_add_token ;
     ImageView back_addtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_token);

        edttoken_address = (EditText) findViewById(R.id.edttoken_address);
        edt_tknsymbol = (EditText) findViewById(R.id.edt_tknsymbol);
        edt_tkndec = (EditText) findViewById(R.id.edt_tkndec);
        btn_add_token= (Button)findViewById(R.id.btn_add_token);
        back_addtoken = (ImageView)findViewById(R.id.back_addtoken);

        btn_add_token.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(edttoken_address.getText().toString()!=null && edttoken_address.getText().toString().length()>0)
                {
                    if(edt_tknsymbol.getText().toString()!=null && edt_tknsymbol.getText().toString().length()>0)
                    {
                        if(edt_tkndec.getText().toString()!=null && edt_tkndec.getText().toString().length()>0)
                        {
                            TokenDetailsResponse tokenDetail = new TokenDetailsResponse();
                            tokenDetail.setToken_address(edttoken_address.getText().toString());
                            tokenDetail.setSymbol(edt_tknsymbol.getText().toString());
                            tokenDetail.setDecimal(BigInteger.valueOf(Long.parseLong(edt_tkndec.getText().toString())));
                            Gson gson = new Gson();
                            String json = gson.toJson(tokenDetail);
                            SharedPreferenceHelper.setSharedPreferenceString(AddToken.this, "tokeninfo", json);
                            Intent intent = new Intent(AddToken.this, UserprofileActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(AddToken.this, "Please add Token Decimal", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(AddToken.this, "Please add Token Symbol", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(AddToken.this, "Please add Token Address", Toast.LENGTH_LONG).show();
                }


            }
        });


        back_addtoken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(AddToken.this, UserprofileActivity.class);
        startActivity(intent);
    }
}