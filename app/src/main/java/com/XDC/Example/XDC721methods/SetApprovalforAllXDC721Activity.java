package com.XDC.Example.XDC721methods;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.Example.utils.SharedPreferenceHelper;
import com.XDC.Example.utils.Utility;
import com.XDC.R;
import com.XDCJava.Model.Token721DetailsResponse;
import com.XDCJava.Model.WalletData;
import com.XDCJava.XDC721Client;

public class SetApprovalforAllXDC721Activity extends AppCompatActivity {

    EditText edt_receiver_address, edt_token_totransfer;
    Button send_approve;
    TextView text_transaction_hash;
    WalletData user_wallet;
    ImageView back_txdc;
    Token721DetailsResponse tokenDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setapproval_xdc721);

        edt_receiver_address = (EditText) findViewById(R.id.receiver_address);
        edt_token_totransfer = (EditText) findViewById(R.id.value);
        send_approve = (Button) findViewById(R.id.send_approve);
        back_txdc = findViewById(R.id.back_txdc);
        text_transaction_hash = (TextView) findViewById(R.id.text_transaction_hash);
        user_wallet = Utility.getProfile(SetApprovalforAllXDC721Activity.this);
        tokenDetail = Utility.getnftinfo(SetApprovalforAllXDC721Activity.this);
        send_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasText(edt_receiver_address)) {
                    edt_receiver_address.setError(getResources().getString(R.string.error_empty));
                } else if (!hasText(edt_token_totransfer)) {
                    edt_token_totransfer.setError(getResources().getString(R.string.error_empty));

                } else {


                    if (user_wallet != null && user_wallet.getAccountAddress() != null && user_wallet.getAccountAddress().length() > 0 && user_wallet.getPrivateKey() != null) {
                        try {
                            String hash = null;
                            try {
                                hash = XDC721Client.getInstance().setApprovalForAll(tokenDetail.getTokenAddress(), user_wallet.getPrivateKey(), edt_receiver_address.getText().toString(), edt_token_totransfer.getText().toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            text_transaction_hash.setText(hash);
                            Utility.closeKeyboard(SetApprovalforAllXDC721Activity.this);
                            SharedPreferenceHelper.setSharedPreferenceString(SetApprovalforAllXDC721Activity.this, "nfthash", hash);                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                }
            }


        });

        back_txdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public static boolean hasText(EditText s) {
        if (s.getText().toString().trim().equalsIgnoreCase(""))
            return false;
        else
            return true;
    }
}