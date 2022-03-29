package com.XDCJava.Model;

import java.io.Serializable;

public class Token721DetailsResponse implements Serializable
{


    private String symbol;
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String param) {
        this.symbol = param;
    }

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String param) {
        this.name = param;
    }


    private String tokenAddress;
    public String getTokenAddress() {
        return tokenAddress;
    }
    public void setTokenAddress(String param) {
        this.tokenAddress = param;
    }

    private String balance;
    public String getBalance() {
        return balance;
    }
    public void setBalance(String param) {
        this.balance = param;
    }
}
