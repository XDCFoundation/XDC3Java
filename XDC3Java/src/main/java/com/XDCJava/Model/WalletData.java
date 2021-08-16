package com.XDCJava.Model;

import java.io.Serializable;

public class WalletData implements Serializable
{



    private String privateKey;
    public String getPrivateKey() {
        return privateKey;
    }
    public void setPrivateKey(String param) {
        this.privateKey = param;
    }

    private String publickeyKey;
    public String getPublickeyKey() {
        return publickeyKey;
    }
    public void setPublickeyKey(String param) {
        this.publickeyKey = param;
    }

    private String accountAddress;
    public String getAccountAddress() {
        return accountAddress;
    }
    public void setAccountAddress(String param) {
        this.accountAddress = param;
    }


    //to recover private key
    private String seedPhrase;
    public String getSeedPhrase() {
        return seedPhrase;
    }
    public void setSeedPhrase(String param) {
        this.seedPhrase = param;
    }



}
