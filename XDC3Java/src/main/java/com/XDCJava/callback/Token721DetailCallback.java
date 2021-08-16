package com.XDCJava.callback;

import com.XDCJava.Model.Token721DetailsResponse;

public interface Token721DetailCallback {
    void success(Token721DetailsResponse tokenApiModel);

    void success(String message);
    void failure(Throwable t);

    void failure(String message);
}
