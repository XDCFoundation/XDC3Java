package com.XDCJava.callback;

import com.XDCJava.Model.TokenDetailsResponse;

public interface TokenDetailCallback {
    void success(TokenDetailsResponse tokendetail);


    void failure(Throwable t);

    void failure(String message);
}
