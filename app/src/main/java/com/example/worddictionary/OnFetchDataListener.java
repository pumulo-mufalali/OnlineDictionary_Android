package com.example.worddictionary;

import com.example.worddictionary.Models.ApiResponse;

public interface OnFetchDataListener {
    void onFetchedData(ApiResponse apiResponse, String message);
    void onError(String message);
}
