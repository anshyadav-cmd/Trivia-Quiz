package com.morteza.triviaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import org.json.JSONObject;

public class  MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private String url;
    private CardStackView mCardStackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();

        mCardStackView = findViewById(R.id.myCardStackView);
        mCardStackView.setLayoutManager(new CardStackLayoutManager(this));
        mCardStackView.setAdapter(new CardStackAdapter());

        url = "https://opentdb.com/api.php?amount=10&category=18&difficulty=easy&type=boolean";

        JsonObjectRequest quizJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("QUIZ", response+"");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(quizJsonObjectRequest);
    }
}
