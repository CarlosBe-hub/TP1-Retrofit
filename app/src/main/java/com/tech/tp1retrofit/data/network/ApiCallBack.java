package com.tech.tp1retrofit.data.network;

public interface ApiCallBack <T> {
    void onSuccess(T result);
    void onError(String message);
}
