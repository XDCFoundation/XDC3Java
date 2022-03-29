package com.XDC.Example.XDC721methods;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.Example.XDC20methods.AllowanceXDC20Activity;
import com.XDC.Example.XDC20methods.ApproveXDC20Activity;
import com.XDC.Example.XDC20methods.BalanceXDC20Activity;
import com.XDC.Example.XDC20methods.TransferfromXDC20Activity;
import com.XDC.Example.XDC20methods.TransfertokenXDC20Activity;
import com.XDC.Example.utils.SharedPreferenceHelper;
import com.XDC.Example.utils.Utility;
import com.XDC.R;
import com.XDCJava.Model.Token721DetailsResponse;
import com.XDCJava.Model.TokenDetailsResponse;
import com.XDCJava.Model.WalletData;
import com.XDCJava.XDC20Client;
import com.XDCJava.XDC721Client;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class XDC721Details extends AppCompatActivity implements View.OnClickListener {

    TextView text_tokenbalance, text_tokensymbol, token_event, xdc_transaction,
            txt_approve, txt_setapprovalforall, txt_transferfrom, txt_safetransferfrom,
            txt_tokenuri, txt_balanceof, txt_ownerbyindex,txt_tokenbyindex,
            txt_ownerof,txt_getapproved,txt_isapprovedforall,txt_supportinterface,txt_totalsupply;
    BottomSheetBehavior bottomSheetwriteMethods, bottomSheetreadMethods;
    LinearLayout lin_writemethod, lin_readmethods;
    Token721DetailsResponse tokenDetail;
    WalletData user_wallet;
    ImageView img_threedot,img_back20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xdc721details);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        user_wallet = Utility.getProfile(XDC721Details.this);
        text_tokenbalance = findViewById(R.id.text_tokenbalance);
        text_tokensymbol = findViewById(R.id.text_tokensymbol);

        lin_writemethod = findViewById(R.id.lin_writemethod);
        lin_readmethods = findViewById(R.id.lin_readmethods);
        img_back20 = findViewById(R.id.img_back20);
        token_event = findViewById(R.id.token_event);
        xdc_transaction = findViewById(R.id.xdc_transaction);
        img_threedot = findViewById(R.id.img_threedot);
        tokenDetail = Utility.getnftinfo(XDC721Details.this);

        text_tokensymbol.setText(tokenDetail.getSymbol());


        txt_approve = findViewById(R.id.txt_approve);
        txt_approve.setOnClickListener(this::onClick);

        txt_setapprovalforall = findViewById(R.id.txt_setapprovalforall);
        txt_setapprovalforall.setOnClickListener(this::onClick);

        txt_transferfrom = findViewById(R.id.txt_transferfrom);
        txt_transferfrom.setOnClickListener(this::onClick);

        txt_safetransferfrom = findViewById(R.id.txt_safetransferfrom);
        txt_safetransferfrom.setOnClickListener(this::onClick);


//read methods
        txt_tokenuri = findViewById(R.id.txt_tokenuri);
        txt_tokenuri.setOnClickListener(this::onClick);

        txt_balanceof = findViewById(R.id.txt_balanceof);
        txt_balanceof.setOnClickListener(this::onClick);


        txt_ownerbyindex = findViewById(R.id.txt_ownerbyindex);
        txt_ownerbyindex.setOnClickListener(this::onClick);


        txt_tokenbyindex = findViewById(R.id.txt_tokenbyindex);
        txt_tokenbyindex.setOnClickListener(this::onClick);


        txt_ownerof = findViewById(R.id.txt_ownerof);
        txt_ownerof.setOnClickListener(this::onClick);


        txt_getapproved = findViewById(R.id.txt_getapproved);
        txt_getapproved.setOnClickListener(this::onClick);


        txt_isapprovedforall = findViewById(R.id.txt_isapprovedforall);
        txt_isapprovedforall.setOnClickListener(this::onClick);


        txt_supportinterface = findViewById(R.id.txt_supportinterface);
        txt_supportinterface.setOnClickListener(this::onClick);

        txt_totalsupply = findViewById(R.id.txt_totalsupply);
        txt_totalsupply.setOnClickListener(this::onClick);


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
            public void onClick(View v) {
                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
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
                intent = new Intent(XDC721Details.this, ApproveXDC721Activity.class);
                startActivity(intent);
                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;

            case R.id.txt_setapprovalforall:
                intent = new Intent(XDC721Details.this, SetApprovalforAllXDC721Activity.class);
                startActivity(intent);
                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;


            case R.id.txt_transferfrom:
                intent = new Intent(XDC721Details.this, TransferfromXDC721Activity.class);
                startActivity(intent);
                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_safetransferfrom:
                intent = new Intent(XDC721Details.this, SafeTransferfromXDC721Activity.class);
                startActivity(intent);
                bottomSheetwriteMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;

            //read methods
            case R.id.txt_tokenuri:
                intent = new Intent(XDC721Details.this, TokenURIXDC721Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_balanceof:
                intent = new Intent(XDC721Details.this, BalanceOfXDC721Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_ownerbyindex:
                intent = new Intent(XDC721Details.this, TokenOfOwnerByIndexXDC721Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_tokenbyindex:
                intent = new Intent(XDC721Details.this, TokenByIndexXDC721Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_ownerof:
                intent = new Intent(XDC721Details.this, OwnerOfXDC721Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_getapproved:
                intent = new Intent(XDC721Details.this, GetApprovedXDC721Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_isapprovedforall:
                intent = new Intent(XDC721Details.this, IsApprovedForAllXDC721Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_supportinterface:
                intent = new Intent(XDC721Details.this, SupportInterfaceXDC721Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.txt_totalsupply:
                intent = new Intent(XDC721Details.this, TotalSupplyXDC721Activity.class);
                startActivity(intent);
                bottomSheetreadMethods.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;


        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        text_tokenbalance.setText(XDC721Client.getInstance().getBalance(tokenDetail.getTokenAddress(), user_wallet.getAccountAddress()));
        String transactionhash = SharedPreferenceHelper.getSharedPreferenceString(XDC721Details.this, "nfthash", "");
        xdc_transaction.setText(transactionhash);
    }
}