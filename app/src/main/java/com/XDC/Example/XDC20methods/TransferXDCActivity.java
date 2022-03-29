package com.XDC.Example.XDC20methods;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.Example.utils.Utility;
import com.XDC.R;
import com.XDCJava.Model.WalletData;
import com.XDCJava.XDC20Client;
import com.XDCJava.callback.EventCallback;

public class TransferXDCActivity extends AppCompatActivity {

    EditText edt_receiver_address, edt_token_totransfer;
    Button button_send;
    TextView text_transaction_hash;
    WalletData user_wallet;
    ImageView back_txdc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_xdc);

        edt_receiver_address = (EditText) findViewById(R.id.receiver_address);
        edt_token_totransfer = (EditText) findViewById(R.id.value);
        button_send = (Button) findViewById(R.id.send);
        back_txdc = findViewById(R.id.back_txdc);
        text_transaction_hash = (TextView) findViewById(R.id.text_transaction_hash);
        user_wallet = Utility.getProfile(TransferXDCActivity.this);
        button_send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                 if (!hasText(edt_receiver_address)) {
                    edt_receiver_address.setError(getResources().getString(R.string.error_empty));
                } else if (!hasText(edt_token_totransfer)) {
                    edt_token_totransfer.setError(getResources().getString(R.string.error_empty));

                } else {


                    if(user_wallet!=null && user_wallet.getAccountAddress()!=null && user_wallet.getAccountAddress().length()>0&& user_wallet.getPrivateKey()!=null)
                    {
                        XDC20Client.getInstance().TransferXdc(user_wallet.getPrivateKey() ,user_wallet.getAccountAddress(), edt_receiver_address.getText().toString(), edt_token_totransfer.getText().toString(), new EventCallback() {
                            @Override
                            public void success(String hash)
                            {
                                text_transaction_hash.setText(hash);
                                Utility.closeKeyboard(TransferXDCActivity.this);
                            }

                            @Override
                            public void failure(Throwable t) {

                            }

                            @Override
                            public void failure(String message) {

                            }
                        });
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