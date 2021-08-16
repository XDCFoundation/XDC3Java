package com.XDCJava.callback;

import com.XDCJava.Model.WalletData;

public interface EventCallback
{
    void success( String hash);




    void failure(Throwable t);

    void failure(String message);
}
