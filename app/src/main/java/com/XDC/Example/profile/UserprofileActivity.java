package com.XDC.Example.profile;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.XDC.Example.SplashScreen;
import com.XDC.Example.XDC20methods.AddToken;
import com.XDC.Example.XDC20methods.TransferXDCActivity;
import com.XDC.Example.XDC20methods.XDC20Details;
import com.XDC.Example.XDC721methods.AddNFT;
import com.XDC.Example.XDC721methods.SetApprovalforAllXDC721Activity;
import com.XDC.Example.XDC721methods.XDC721Details;
import com.XDC.Example.utils.SharedPreferenceHelper;
import com.XDC.Example.utils.Utility;
import com.XDC.Example.wallet.ImportorCreateActivity;
import com.XDC.R;
import com.XDCJava.Model.Token721DetailsResponse;
import com.XDCJava.Model.TokenDetailsResponse;
import com.XDCJava.Model.WalletData;
import com.XDCJava.XDC20Client;
import com.XDCJava.XDC721Client;
import com.XDCJava.callback.Token721DetailCallback;
import com.XDCJava.callback.TokenDetailCallback;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;


public class UserprofileActivity extends AppCompatActivity {

    TextView text_accountaddress, text_XDC, transfer_XDC, text_addtoken, text_tokenbalance,text_tokensymbol,
            text_nftbalance,text_nftsymbol,text_addnfttoken;
    WalletData user_wallet;
    TokenDetailsResponse tokenDetail;
    Token721DetailsResponse nftDetail;
    ImageView img_copy,img_logout;
    LinearLayout lin_token_info,lin_nfttoken_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        text_accountaddress = findViewById(R.id.text_accountaddress);
        img_copy = findViewById(R.id.img_copy);
        text_XDC = findViewById(R.id.text_XDC);
        text_addtoken = findViewById(R.id.text_addtoken);
        transfer_XDC = findViewById(R.id.transfer_XDC);
        lin_token_info = findViewById(R.id.lin_token_info);
        lin_nfttoken_info = findViewById(R.id.lin_nfttoken_info);
        text_tokenbalance = findViewById(R.id.text_tokenbalance);
        text_tokensymbol = findViewById(R.id.text_tokensymbol);
        img_logout = findViewById(R.id.img_logout);
        text_nftbalance = findViewById(R.id.text_nftbalance);
        text_nftsymbol = findViewById(R.id.text_nftsymbol);
        text_addnfttoken = findViewById(R.id.text_addnfttoken);



        String xdc = SharedPreferenceHelper.getSharedPreferenceString(UserprofileActivity.this, "xdc", "");
        text_XDC.setText(xdc);





        text_addtoken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserprofileActivity.this, AddToken.class);
                startActivity(intent);
                finish();
            }
        });

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(UserprofileActivity.this)
                        .content("Are you sure you want to log out?")
                        .positiveText("Yes")
                        .negativeText("Not Right Now")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog,
                                                @NonNull DialogAction which) {
                                dialog.dismiss();
                                if (user_wallet !=null && user_wallet.getAccountAddress()!=null && user_wallet.getAccountAddress().length()>0) {

                                    // callLogout();
                                    SharedPreferenceHelper.clearSharedPreference(UserprofileActivity.this);
                                    Intent intent = new Intent(UserprofileActivity.this, ImportorCreateActivity.class);
                                    startActivity(intent);
                                    UserprofileActivity.this.finish();


                                }
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog,
                                                @NonNull DialogAction which) {

                                dialog.dismiss();
                            }
                        }).show();
            }
        });

        text_addnfttoken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserprofileActivity.this, AddNFT.class);
                startActivity(intent);
                finish();
            }
        });


        img_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("wallet Address", user_wallet.getAccountAddress());
                clipboard.setPrimaryClip(clip);
            }
        });

        transfer_XDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserprofileActivity.this, TransferXDCActivity.class);
                startActivity(intent);
            }
        });

        lin_token_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserprofileActivity.this, XDC20Details.class);
                startActivity(intent);

            }
        });

        lin_nfttoken_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserprofileActivity.this, XDC721Details.class);
                startActivity(intent);

            }
        });


    }

    private void displaytokenInfo() {

        if (tokenDetail != null && tokenDetail.getToken_address() != null && tokenDetail.getToken_address().length() > 0) {
            lin_token_info.setVisibility(View.VISIBLE);
            text_tokenbalance.setText(SharedPreferenceHelper.getSharedPreferenceString(UserprofileActivity.this, "XDC20B", ""));
            XDC20Client.getInstance().getTokenoinfo(tokenDetail.getToken_address(), new TokenDetailCallback() {
                @Override
                public void success(TokenDetailsResponse tokendetail)
                {
                    text_tokensymbol.setText(tokendetail.getSymbol());
                    tokendetail.setBalance(text_tokenbalance.getText().toString());
                    Gson gson = new Gson();
                    String json = gson.toJson(tokendetail);
                    SharedPreferenceHelper.setSharedPreferenceString(UserprofileActivity.this, "tokeninfo", json);
                }

                @Override
                public void failure(Throwable t) {

                }

                @Override
                public void failure(String message) {

                }
            });
        } else {
            lin_token_info.setVisibility(View.GONE);
        }

        if (nftDetail != null && nftDetail.getTokenAddress() != null && nftDetail.getTokenAddress().length() > 0) {
            lin_nfttoken_info.setVisibility(View.VISIBLE);
            text_nftbalance.setText(SharedPreferenceHelper.getSharedPreferenceString(UserprofileActivity.this, "XDC721B", ""));
            XDC721Client.getInstance().getTokenoinfo(nftDetail.getTokenAddress(), new Token721DetailCallback() {
                @Override
                public void success(Token721DetailsResponse tokendetail) {
                    text_nftsymbol.setText(nftDetail.getSymbol());
                    nftDetail.setBalance(text_nftbalance.getText().toString());
                    Gson gson = new Gson();
                    String json = gson.toJson(nftDetail);
                    SharedPreferenceHelper.setSharedPreferenceString(UserprofileActivity.this, "nftinfo", json);
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
        } else {
            lin_nfttoken_info.setVisibility(View.GONE);
        }
    }

    private void getXDCBalance()
    {
        SharedPreferenceHelper.setSharedPreferenceString(UserprofileActivity.this, "xdc", XDC20Client.getInstance().getXdcBalance(user_wallet.getAccountAddress()));
        String xdc = SharedPreferenceHelper.getSharedPreferenceString(UserprofileActivity.this, "xdc", "");
        text_XDC.setText(xdc);
    }

    private void getXDC20Balance()
    {
        SharedPreferenceHelper.setSharedPreferenceString(UserprofileActivity.this, "XDC20B", XDC20Client.getInstance().getBalance(tokenDetail.getToken_address(),user_wallet.getAccountAddress()));
        text_tokenbalance.setText(SharedPreferenceHelper.getSharedPreferenceString(UserprofileActivity.this, "XDC20B", ""));
    }

    private void getXDC721Balance()
    {
        SharedPreferenceHelper.setSharedPreferenceString(UserprofileActivity.this, "XDC721B",XDC721Client.getInstance().getBalance(nftDetail.getTokenAddress(),user_wallet.getAccountAddress()));
        text_nftbalance.setText(SharedPreferenceHelper.getSharedPreferenceString(UserprofileActivity.this, "XDC721B", ""));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        user_wallet = Utility.getProfile(UserprofileActivity.this);
        tokenDetail = Utility.gettokeninfo(UserprofileActivity.this);
        nftDetail = Utility.getnftinfo(UserprofileActivity.this);
        text_accountaddress.setText(user_wallet.getAccountAddress());
        Log.e("walletAddress", user_wallet.getAccountAddress());
        Log.e("seedphrase", user_wallet.getSeedPhrase());
        Log.e("privatkey", user_wallet.getPrivateKey());
        displaytokenInfo();
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                //Log.e("nftDetail", nftDetail.getTokenAddress());

                getXDCBalance();
                if(tokenDetail!=null && tokenDetail.getToken_address()!=null&& tokenDetail.getToken_address().length()>0)
                {
                    getXDC20Balance();
                }

                if(nftDetail!=null && nftDetail.getTokenAddress()!=null&& nftDetail.getTokenAddress().length()>0)
                {
                    getXDC721Balance();
                }


            }
        }, 2);

    }
}