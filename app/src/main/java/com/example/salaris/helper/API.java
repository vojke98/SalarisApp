package com.example.salaris.helper;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.net.URI;

public class API {
    private final String BASE = "https://salaris-beta.herokuapp.com/api";
    private final String USERS = "/users";
    private final String COMPANIES = "/companies";
    private Context context;
    private RequestQueue queue;

    public API(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(this.context);
    }

    public void getUser(final String email, final String password, final VolleyCallback callback) {
        final String url = URL(BASE, USERS) + Query("email", email, "password", password);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> callback.onSuccess(response),
                error -> callback.onError(error));

        queue.add(stringRequest);
    }

    public void getCompany(Integer id, final VolleyCallback callback) {
        final String url = URL(BASE, COMPANIES) + id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> callback.onSuccess(response),
                error -> callback.onError(error));

        queue.add(stringRequest);
    }

    private String URL(String... parts) {
        String url = "";
        for (String part : parts) url += part;

        return url + "/";
    }

    private String Query(String... params) {
        StringBuilder query = new StringBuilder("?");
        for (int i = 0; i < params.length; i+=2) {
            if(i != 0) query.append("&");
            query.append(params[i]).append("=").append(params[i + 1]);
        }

        return query.toString();
    }
}
