package com.XDC.Example;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.XDC.R;
import com.XDCJava.Model.Token721DetailsResponse;
import com.XDCJava.XDC20Client;
import com.XDCJava.XDC721Client;

import java.math.BigInteger;

public class Details721 extends AppCompatActivity implements View.OnClickListener {

    TextView xdc_address_value, name_value, symbol_value, total_supply_value, balance_off_value, ownerof_value, is_Supoortinterface, approve_trasactonhash, getapproved_value,
            isapprovedForAll_value, setapprovedForAll_value, safeTransferFrom_trasactonhash, transferFrom_trasactonhash, token_Uri_value,tokenbyindex_value,tokenownerbyindex_value;
    BigInteger dec_bal, dec_supply;
    Token721DetailsResponse tokenResponse = new Token721DetailsResponse();

    EditText edt_tokenid, edt_balance_spender,
            edt_privatekey, edt_contract_address, edt_interfaceID, edt_approve_tokenid, edt_approve_spender, edt_getapprove_tokenid,
            edt_isapproveforall_spender, edt_setapproveforall_spender, edt_safeTransferFrom_spender, edt_safetrans_tokenid, edt_transferFrom_spender,
            edt_trans_tokenid, edt_setapprobe_booean, edt_token_address, edt_token_uri_tokenid,tokenbyindex_address,edt_tokenby_index_value,edt_tokenbyownerindex_address,
            edt_owner_index_address,edt_tokenownerby_index_value,edt_total_supply_token;
    Button submit_ownerof,
            check_address, submit_balanceof, submit_interface, submit_approve, submit_getapproved, submit_isapprovedforall,
            submit_setapprovedforall, submit_safetrans, submit_trans, submit_tokenuri,submit_tokenby_index,submit_tokenownerby_index,submit_total_supply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details721);

        xdc_address_value = findViewById(R.id.xdc_address_value);
        name_value = findViewById(R.id.name_value);
        symbol_value = findViewById(R.id.symbol_value);
        balance_off_value = findViewById(R.id.balance_off_value);

        edt_privatekey = findViewById(R.id.edt_privatekey);
        check_address = findViewById(R.id.submit);
        edt_contract_address = findViewById(R.id.edt_contract_address);

        submit_balanceof = findViewById(R.id.submit_balanceof);
        submit_balanceof.setOnClickListener(this::onClick);
        edt_balance_spender = findViewById(R.id.edt_balance_spender);

        ownerof_value = findViewById(R.id.ownerof_value);
        edt_tokenid = findViewById(R.id.edt_tokenid);
        submit_ownerof = findViewById(R.id.submit_ownerof);
        submit_ownerof.setOnClickListener(this::onClick);


        submit_interface = findViewById(R.id.submit_interface);
        submit_interface.setOnClickListener(this::onClick);
        edt_interfaceID = findViewById(R.id.edt_interfaceID);
        is_Supoortinterface = findViewById(R.id.is_Supoortinterface);

        edt_approve_tokenid = findViewById(R.id.edt_approve_tokenid);
        edt_approve_spender = findViewById(R.id.edt_approve_spender);
        submit_approve = findViewById(R.id.submit_approve);
        submit_approve.setOnClickListener(this::onClick);
        approve_trasactonhash = findViewById(R.id.approve_trasactonhash);

        getapproved_value = findViewById(R.id.getapproved_value);
        edt_getapprove_tokenid = findViewById(R.id.edt_getapprove_tokenid);
        submit_getapproved = findViewById(R.id.submit_getapproved);
        submit_getapproved.setOnClickListener(this::onClick);

        isapprovedForAll_value = findViewById(R.id.isapprovedForAll_value);
        submit_isapprovedforall = findViewById(R.id.submit_isapprovedforall);
        submit_isapprovedforall.setOnClickListener(this::onClick);
        edt_isapproveforall_spender = findViewById(R.id.edt_isapproveforall_spender);

        setapprovedForAll_value = findViewById(R.id.setapprovedForAll_value);
        edt_setapprobe_booean = findViewById(R.id.edt_setapprobe_booean);
        edt_setapproveforall_spender = findViewById(R.id.edt_setapproveforall_spender);
        submit_setapprovedforall = findViewById(R.id.submit_setapprovedforall);
        submit_setapprovedforall.setOnClickListener(this::onClick);


        safeTransferFrom_trasactonhash = findViewById(R.id.safeTransferFrom_trasactonhash);
        edt_safeTransferFrom_spender = findViewById(R.id.edt_safeTransferFrom_spender);
        edt_safetrans_tokenid = findViewById(R.id.edt_safetrans_tokenid);
        submit_safetrans = findViewById(R.id.submit_safetrans);
        submit_safetrans.setOnClickListener(this::onClick);

        transferFrom_trasactonhash = findViewById(R.id.transferFrom_trasactonhash);
        edt_transferFrom_spender = findViewById(R.id.edt_transferFrom_spender);
        edt_trans_tokenid = findViewById(R.id.edt_trans_tokenid);
        submit_trans = findViewById(R.id.submit_trans);
        submit_trans.setOnClickListener(this::onClick);

        token_Uri_value = findViewById(R.id.token_Uri_value);
        edt_token_address = findViewById(R.id.edt_token_address);
        edt_token_uri_tokenid = findViewById(R.id.edt_token_uri_tokenid);
        submit_tokenuri = findViewById(R.id.submit_tokenuri);
        submit_tokenuri.setOnClickListener(this::onClick);


        tokenbyindex_value = findViewById(R.id.tokenbyindex_value);
        tokenbyindex_address = findViewById(R.id.tokenbyindex_address);
        edt_tokenby_index_value = findViewById(R.id.edt_tokenby_index_value);
        submit_tokenby_index = findViewById(R.id.submit_tokenby_index);
        submit_tokenby_index.setOnClickListener(this::onClick);


        tokenownerbyindex_value = findViewById(R.id.tokenownerbyindex_value);
        edt_tokenbyownerindex_address = findViewById(R.id.edt_tokenbyownerindex_address);
        edt_owner_index_address = findViewById(R.id.edt_owner_index_address);
        edt_tokenownerby_index_value = findViewById(R.id.edt_tokenownerby_index_value);
        submit_tokenownerby_index = findViewById(R.id.submit_tokenownerby_index);
        submit_tokenownerby_index.setOnClickListener(this::onClick);


        total_supply_value = findViewById(R.id.total_supply_value);
        edt_total_supply_token = findViewById(R.id.edt_total_supply_token);
        submit_total_supply = findViewById(R.id.submit_total_supply);
        submit_total_supply.setOnClickListener(this::onClick);
        if (getIntent().hasExtra("tokendetail")) {
            tokenResponse = (Token721DetailsResponse) getIntent().getSerializableExtra("tokendetail");

            Log.e("cardetail", tokenResponse.getName() + "");
        }


        assignValues();


        check_address.setOnClickListener(this);
    }


    public void assignValues() {


        if (name_value != null) {
            name_value.setText(tokenResponse.getName());
        } else
            name_value.setText("-");

        if (symbol_value != null) {
            symbol_value.setText(tokenResponse.getSymbol());
        } else
            symbol_value.setText("-");

        xdc_address_value.setText(tokenResponse.getTokenAddress());
        edt_token_address.setText(tokenResponse.getTokenAddress());
        edt_total_supply_token.setText(tokenResponse.getTokenAddress());
        tokenbyindex_address.setText(tokenResponse.getTokenAddress());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                verifyPrivatekey();

                break;


            case R.id.submit_balanceof:
                getbalance();
                break;
            case R.id.submit_ownerof:
                getownerOf();
                break;
            case R.id.submit_interface:
                isSupoortinterface();
                break;

            case R.id.submit_approve:
                try {
                    approve();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.submit_getapproved:
                getapproved();
                break;
            case R.id.submit_isapprovedforall:
                isapprovedforall();
                break;
            case R.id.submit_setapprovedforall:
                try {
                    submit_setapprovedforall();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.submit_safetrans:
                try {
                    safeTransferfrom();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.submit_trans:
                try {
                    transferfrom();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.submit_tokenuri:
                getTokenUri();
                break;
            case R.id.submit_tokenby_index:
                getTokenbyIndex();
                break;

            case R.id.submit_tokenownerby_index:
                getTokenOwnerbyIndex();
                break;

            case R.id.submit_total_supply:
                gettotalSupply();
                break;




        }

    }

    private void gettotalSupply()
    {
        if (edt_total_supply_token.getText().toString() != null && edt_total_supply_token.getText().toString().length() > 0) {


            String balance = XDC721Client.getInstance().gettotalSupply(edt_total_supply_token.getText().toString());
            total_supply_value.setText(balance);


        } else {
            Toast.makeText(Details721.this, "Please Enter Token Address", Toast.LENGTH_LONG).show();
        }
    }

    private void getTokenOwnerbyIndex() {

        if (edt_tokenbyownerindex_address.getText().toString() != null && edt_tokenbyownerindex_address.getText().toString().length() > 0) {
            if (edt_owner_index_address.getText().toString() != null && edt_owner_index_address.getText().toString().length() > 0) {
                if (edt_tokenownerby_index_value.getText().toString() != null && edt_tokenownerby_index_value.getText().toString().length() > 0) {
                    String tokenbyIndex = XDC721Client.getInstance().tokenOfOwnerByIndex(edt_tokenbyownerindex_address.getText().toString(), edt_owner_index_address.getText().toString(),edt_tokenownerby_index_value.getText().toString());
                    tokenownerbyindex_value.setText(tokenbyIndex);
                } else {
                    Toast.makeText(Details721.this, "Please Enter Token index", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(Details721.this, "Please Enter Owner Address", Toast.LENGTH_LONG).show();
            }


        } else {
            Toast.makeText(Details721.this, "Please Enter Token Address", Toast.LENGTH_LONG).show();
        }
    }

    private void getTokenbyIndex() {

        if (tokenbyindex_address.getText().toString() != null && tokenbyindex_address.getText().toString().length() > 0) {
            if (edt_tokenby_index_value.getText().toString() != null && edt_tokenby_index_value.getText().toString().length() > 0) {
                String tokenbyIndex = XDC721Client.getInstance().gettokenByIndex(tokenbyindex_address.getText().toString(), edt_tokenby_index_value.getText().toString());
                tokenbyindex_value.setText(tokenbyIndex);
            } else {
                Toast.makeText(Details721.this, "Please Enter Token index", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(Details721.this, "Please Enter Token Address", Toast.LENGTH_LONG).show();
        }
    }

    private void getTokenUri() {

        if (edt_token_address.getText().toString() != null && edt_token_address.getText().toString().length() > 0) {
            if (edt_token_uri_tokenid.getText().toString() != null && edt_token_uri_tokenid.getText().toString().length() > 0) {
                String tokenUri = XDC721Client.getInstance().getTokenUri(edt_token_address.getText().toString(), edt_token_uri_tokenid.getText().toString());
                token_Uri_value.setText(tokenUri);
            } else {
                Toast.makeText(Details721.this, "Please Enter Token id", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(Details721.this, "Please Enter Token Address", Toast.LENGTH_LONG).show();
        }
    }

    private void transferfrom() throws Exception {
        if (xdc_address_value.getText().toString() != null && xdc_address_value.getText().toString().length() > 0) {

            if (edt_privatekey.getText().toString() != null && edt_privatekey.getText().toString().length() > 0) {


                if (edt_transferFrom_spender.getText().toString() != null && edt_transferFrom_spender.getText().toString().length() > 0) {

                    if (edt_trans_tokenid.getText().toString() != null && edt_trans_tokenid.getText().toString().length() > 0) {
                      //  String setapproveforall = XDC721Client.getInstance().transferfrom(xdc_address_value.getText().toString(), edt_privatekey.getText().toString(), edt_transferFrom_spender.getText().toString(), edt_trans_tokenid.getText().toString());

                        String setapproveforall = XDC721Client.getInstance().transferfrom(xdc_address_value.getText().toString(), edt_privatekey.getText().toString(), edt_transferFrom_spender.getText().toString(), edt_trans_tokenid.getText().toString());


                        transferFrom_trasactonhash.setText(setapproveforall + "");

                    } else {
                        Toast.makeText(Details721.this, "Please Enter Token id", Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(Details721.this, "Please Enter Receiver Address", Toast.LENGTH_LONG).show();
                }


            } else {
                Toast.makeText(Details721.this, "Please Enter Private key", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(Details721.this, "Please Enter Token Address", Toast.LENGTH_LONG).show();
        }


    }

    private void safeTransferfrom() throws Exception {
        if (xdc_address_value.getText().toString() != null && xdc_address_value.getText().toString().length() > 0) {

            if (edt_privatekey.getText().toString() != null && edt_privatekey.getText().toString().length() > 0) {


                if (edt_safeTransferFrom_spender.getText().toString() != null && edt_safeTransferFrom_spender.getText().toString().length() > 0) {

                    if (edt_safetrans_tokenid.getText().toString() != null && edt_safetrans_tokenid.getText().toString().length() > 0) {
                        String setapproveforall = XDC721Client.getInstance().safeTransferFrom(xdc_address_value.getText().toString(), edt_privatekey.getText().toString(), edt_safeTransferFrom_spender.getText().toString(), edt_safetrans_tokenid.getText().toString());
                        safeTransferFrom_trasactonhash.setText(setapproveforall + "");

                    } else {
                        Toast.makeText(Details721.this, "Please Enter Token id", Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(Details721.this, "Please Enter Receiver Address", Toast.LENGTH_LONG).show();
                }


            } else {
                Toast.makeText(Details721.this, "Please Enter Private key", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(Details721.this, "Please Enter Token Address", Toast.LENGTH_LONG).show();
        }


    }

    private void submit_setapprovedforall() throws Exception {
        if (xdc_address_value.getText().toString() != null && xdc_address_value.getText().toString().length() > 0) {

            if (edt_privatekey.getText().toString() != null && edt_privatekey.getText().toString().length() > 0) {


                if (edt_setapproveforall_spender.getText().toString() != null && edt_setapproveforall_spender.getText().toString().length() > 0) {

                    if (edt_setapprobe_booean.getText().toString() != null && edt_setapprobe_booean.getText().toString().length() > 0) {
                        String setapproveforall = XDC721Client.getInstance().setApprovalForAll(xdc_address_value.getText().toString(), edt_privatekey.getText().toString(), edt_setapproveforall_spender.getText().toString(), edt_setapprobe_booean.getText().toString());
                        setapprovedForAll_value.setText(setapproveforall + "");
                    } else {
                        Toast.makeText(Details721.this, "Please Enter Boolean Value", Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(Details721.this, "Please Enter Operator Address", Toast.LENGTH_LONG).show();
                }


            } else {
                Toast.makeText(Details721.this, "Please Enter Private key", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(Details721.this, "Please Enter Token Address", Toast.LENGTH_LONG).show();
        }
    }

    private void isapprovedforall() {
        if (xdc_address_value.getText().toString() != null && xdc_address_value.getText().toString().length() > 0) {

            if (edt_contract_address.getText().toString() != null && edt_contract_address.getText().toString().length() > 0) {


                if (edt_isapproveforall_spender.getText().toString() != null && edt_isapproveforall_spender.getText().toString().length() > 0) {

                    boolean isapproveforall = XDC721Client.getInstance().isApprovedForAll(xdc_address_value.getText().toString(), edt_contract_address.getText().toString(), edt_isapproveforall_spender.getText().toString());
                    isapprovedForAll_value.setText(isapproveforall + "");


                } else {
                    Toast.makeText(Details721.this, "Please Enter Operator Address", Toast.LENGTH_LONG).show();
                }


            } else {
                Toast.makeText(Details721.this, "Please Enter Contact Address", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(Details721.this, "Please Enter Token Address", Toast.LENGTH_LONG).show();
        }
    }

    private void getapproved() {
        if (edt_getapprove_tokenid.getText().toString() != null && edt_getapprove_tokenid.getText().toString().length() > 0) {


            String getapproved = XDC721Client.getInstance().getApproved(xdc_address_value.getText().toString(), edt_getapprove_tokenid.getText().toString());
            getapproved_value.setText(getapproved);


        } else {
            Toast.makeText(Details721.this, "Please Enter  Token id", Toast.LENGTH_LONG).show();
        }
    }

    private void approve() throws Exception {
        if (xdc_address_value.getText().toString() != null && xdc_address_value.getText().toString().length() > 0) {

            if (edt_privatekey.getText().toString() != null && edt_privatekey.getText().toString().length() > 0) {

                if (edt_approve_tokenid.getText().toString() != null && edt_approve_tokenid.getText().toString().length() > 0) {

                    if (edt_approve_spender.getText().toString() != null && edt_approve_spender.getText().toString().length() > 0) {

                        String approve = XDC721Client.getInstance().approve(xdc_address_value.getText().toString(), edt_privatekey.getText().toString(), edt_approve_tokenid.getText().toString(), edt_approve_spender.getText().toString());
                        approve_trasactonhash.setText(approve + "");


                    } else {
                        Toast.makeText(Details721.this, "Please Enter Receiver Address", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(Details721.this, "Please Enter Token Id", Toast.LENGTH_LONG).show();
                }


            } else {
                Toast.makeText(Details721.this, "Please Enter Private key", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(Details721.this, "Please Enter Token Address", Toast.LENGTH_LONG).show();
        }
    }

    private void isSupoortinterface() {
        if (edt_interfaceID.getText().toString() != null && edt_interfaceID.getText().toString().length() > 0) {

            boolean is_Supoort = XDC721Client.getInstance().getsupportInterface(tokenResponse.getTokenAddress(), edt_interfaceID.getText().toString());
            is_Supoortinterface.setText(is_Supoort + "");


        } else {
            Toast.makeText(Details721.this, "Please Enter Interface Id", Toast.LENGTH_LONG).show();
        }
    }

    private void getbalance() {
        if (edt_balance_spender.getText().toString() != null && edt_balance_spender.getText().toString().length() > 0) {


            String balance = XDC721Client.getInstance().getBalance(tokenResponse.getTokenAddress(), edt_balance_spender.getText().toString());
            balance_off_value.setText(balance);


        } else {
            Toast.makeText(Details721.this, "Please Enter  Address to check balance", Toast.LENGTH_LONG).show();
        }
    }

    private void getownerOf() {
        if (edt_tokenid.getText().toString() != null && edt_tokenid.getText().toString().length() > 0) {


            String balance = XDC721Client.getInstance().getOwnerof(tokenResponse.getTokenAddress(), edt_tokenid.getText().toString());
            ownerof_value.setText(balance);


        } else {
            Toast.makeText(Details721.this, "Please Enter  Token id", Toast.LENGTH_LONG).show();
        }
    }

    private void verifyPrivatekey() {
        if (edt_privatekey.getText().toString() != null && edt_privatekey.getText().toString().length() > 0) {
            String contract_address = XDC20Client.getInstance().getContractAddress(edt_privatekey.getText().toString());
            edt_contract_address.setText(contract_address);
        } else {
            Toast.makeText(Details721.this, "Please Enter Private key", Toast.LENGTH_LONG).show();
        }
    }
}