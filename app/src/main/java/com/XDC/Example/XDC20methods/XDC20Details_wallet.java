package com.XDC.Example.XDC20methods;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.Example.utils.SharedPreferenceHelper;
import com.XDC.Example.utils.Utility;
import com.XDC.R;
import com.XDCJava.Model.TokenDetailsResponse;
import com.XDCJava.Model.WalletData;
import com.XDCJava.XDC20Client;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class XDC20Details_wallet extends AppCompatActivity implements View.OnClickListener {

    TextView text_tokenbalance, txt_balanceof,txt_allownce,text_tokensymbol,xdc_transaction, token_event, txt_decreaseallownce, txt_increaseallownce, txt_transfer, txt_transferfrom, txt_approve;
    BottomSheetBehavior bottomSheetwriteMethods,bottomSheetreadMethods;
    LinearLayout lin_writemethod,lin_readmethods;
    TokenDetailsResponse tokenDetail;
    WalletData user_wallet;
    ImageView img_threedot,img_back20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xdc20details);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        user_wallet = Utility.getProfile(XDC20Details_wallet.this);
        text_tokenbalance = findViewById(R.id.text_tokenbalance);
        text_tokensymbol = findViewById(R.id.text_tokensymbol);
        lin_writemethod = findViewById(R.id.lin_writemethod);
        lin_readmethods = findViewById(R.id.lin_readmethods);
        token_event = findViewById(R.id.token_event);
        xdc_transaction = findViewById(R.id.xdc_transaction);
        img_threedot = findViewById(R.id.img_threedot);
        tokenDetail = Utility.gettokeninfo(XDC20Details_wallet.this);
        img_back20 = findViewById(R.id.img_back20);
        text_tokensymbol.setText(tokenDetail.getSymbol());

        txt_decreaseallownce = findViewById(R.id.txt_decreaseallownce);
        txt_decreaseallownce.setOnClickListener(this::onClick);

        txt_increaseallownce = findViewById(R.id.txt_increaseallownce);
        txt_increaseallownce.setOnClickListener(this::onClick);

        txt_transfer = findViewById(R.id.txt_transfer);
        txt_transfer.setOnClickListener(this::onClick);

        txt_transferfrom = findViewById(R.id.txt_transferfrom);
        txt_transferfrom.setOnClickListener(this::onClick);

        txt_approve = findViewById(R.id.txt_approve);
        txt_approve.setOnClickListener(this::onClick);

        txt_balanceof = findViewById(R.id.txt_balanceof);
        txt_balanceof.setOnClickListener(this::onClick);

        txt_allownce = findViewById(R.id.txt_allownce);
        txt_allownce.setOnClickListener(this::onClick);

        bottomSheetwriteMethods = BottomSheetBehavior.from(lin_writemethod);
        bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetreadMethods = BottomSheetBehavior.from(lin_readmethods);
        bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);

        token_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);

            }
        });

        img_threedot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            { bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        img_back20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {


            case R.id.txt_approve:


                intent = new Intent(XDC20Details_wallet.this, ApproveXDC20Activity.class);
                startActivity(intent);
                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;

            case R.id.txt_transfer:
                intent = new Intent(XDC20Details_wallet.this, TransfertokenXDC20Activity.class);
                startActivity(intent);
                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;

            case R.id.txt_increaseallownce:
                intent = new Intent(XDC20Details_wallet.this, IncreaseAllowanceXDC20Activity.class);
                startActivity(intent);
                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_decreaseallownce:
                intent = new Intent(XDC20Details_wallet.this, DecreaseAllowanceXDC20Activity.class);
                startActivity(intent);
                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;

            case R.id.txt_transferfrom:
                intent = new Intent(XDC20Details_wallet.this, TransferfromXDC20Activity.class);
                startActivity(intent);
                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_allownce:
                intent = new Intent(XDC20Details_wallet.this, AllowanceXDC20Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_balanceof:
                intent = new Intent(XDC20Details_wallet.this, BalanceXDC20Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;


        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        text_tokenbalance.setText(XDC20Client.getInstance().getBalance(tokenDetail.getToken_address(),user_wallet.getAccountAddress()));
        SharedPreferenceHelper.setSharedPreferenceString(XDC20Details_wallet.this, "XDC20B", text_tokenbalance.getText().toString());

        String transactionhash = SharedPreferenceHelper.getSharedPreferenceString(XDC20Details_wallet.this, "transactionhash", "");
        xdc_transaction.setText(transactionhash);
    }
}