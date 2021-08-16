package com.XDCJava.callback;

import com.XDCJava.Model.WalletData;

public interface CreateAccountCallback
{
    void success( WalletData walletData);




    void failure(Throwable t);

    void failure(String message);
}
