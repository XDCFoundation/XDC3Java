package com.XDC.Example.XDC721methods;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.Example.XDC20methods.TransferXDCActivity;
import com.XDC.Example.profile.UserprofileActivity;
import com.XDC.Example.utils.SharedPreferenceHelper;
import com.XDC.Example.utils.Utility;
import com.XDC.R;
import com.XDCJava.Model.Token721DetailsResponse;
import com.XDCJava.Model.TokenDetailsResponse;
import com.google.gson.Gson;

import java.math.BigInteger;

public class AddNFT extends AppCompatActivity {

     EditText edttoken_address , edt_tknsymbol;
     Button btn_add_token ;
     ImageView back_addtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nft);

        edttoken_address = (EditText) findViewById(R.id.edttoken_address);
        edt_tknsymbol = (EditText) findViewById(R.id.edt_tknsymbol);
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

                        Token721DetailsResponse tokenDetail = new Token721DetailsResponse();
                            tokenDetail.setTokenAddress(edttoken_address.getText().toString());
                            tokenDetail.setSymbol(edt_tknsymbol.getText().toString());
                            Gson gson = new Gson();
                            String json = gson.toJson(tokenDetail);
                            SharedPreferenceHelper.setSharedPreferenceString(AddNFT.this, "nftinfo", json);
                        Token721DetailsResponse nftDetail = Utility.getnftinfo(AddNFT.this);
                        Log.e("nftDetail", nftDetail.getTokenAddress());
                        Utility.closeKeyboard(AddNFT.this);
                            Intent intent = new Intent(AddNFT.this, UserprofileActivity.class);
                            startActivity(intent);
                        }


                    else
                    {
                        Toast.makeText(AddNFT.this, "Please add Token Symbol", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(AddNFT.this, "Please add Token Address", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(AddNFT.this, UserprofileActivity.class);
        startActivity(intent);
    }
}