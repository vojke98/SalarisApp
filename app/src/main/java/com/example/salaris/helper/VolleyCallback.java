package com.example.salaris.helper;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyCallback{
    void onSuccess(String response);
    void onError(VolleyError error);
}