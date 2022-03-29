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

public class IsApprovedForAllXDC721Activity extends AppCompatActivity {

    EditText token_address, owner_address,token_index;
    Button send_approve;
    TextView text_transaction_hash;
    WalletData user_wallet;
    ImageView back_txdc;
    Token721DetailsResponse tokenDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isapprovedforall_xdc721);

        token_address = (EditText) findViewById(R.id.token_address);
        owner_address = (EditText) findViewById(R.id.owner_address);
        token_index = (EditText) findViewById(R.id.value);
        send_approve = (Button) findViewById(R.id.send_approve);
        back_txdc = findViewById(R.id.back_txdc);
        text_transaction_hash = (TextView) findViewById(R.id.text_transaction_hash);
        user_wallet = Utility.getProfile(IsApprovedForAllXDC721Activity.this);
        tokenDetail = Utility.getnftinfo(IsApprovedForAllXDC721Activity.this);

        send_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasText(token_address)) {
                    token_address.setError(getResources().getString(R.string.error_empty));
                } else if (!hasText(owner_address)) {
                    owner_address.setError(getResources().getString(R.string.error_empty));

                }
                else if (!hasText(token_index)) {
                    token_index.setError(getResources().getString(R.string.error_empty));

                }else {


                        try {
                            Boolean hash = null;
                            try {
                                hash = XDC721Client.getInstance().isApprovedForAll(token_address.getText().toString(),owner_address.getText().toString(),token_index.getText().toString() );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            text_transaction_hash.setText(hash.toString());
                            Utility.closeKeyboard(IsApprovedForAllXDC721Activity.this);
                            //SharedPreferenceHelper.setSharedPreferenceString(IsApprovedForAllXDC721Activity.this, "nfthash", hash.toString());                        } catch (Exception e) {

                        } catch (Exception e) {
                            e.printStackTrace();
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