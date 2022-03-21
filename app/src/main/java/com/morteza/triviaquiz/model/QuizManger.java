package com.morteza.triviaquiz.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.morteza.triviaquiz.MainActivity;
import com.morteza.triviaquiz.R;
import com.morteza.triviaquiz.VolleySingleton;
import com.morteza.triviaquiz.controller.CardStackAdapter;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.StackFrom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuizManger {
    private Context mContext;
    private RequestQueue mRequestQueue;
    private String url;
    private CardStackLayoutManager mCardStackLayoutManager;


    public QuizManger(Context context){
        mContext = context;
        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();
        url = "https://opentdb.com/api.php?amount=10&type=boolean";

//        getQuizQuestion();
    }

    public void getQuizQuestion( Activity activity) {

        List<QuizQuestion> quizQuestions = new ArrayList<>();

        JsonObjectRequest quizJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("QUIZ_QUESTIONS", response+"");
                try {
                    JSONArray results = response.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++ ){
                        JSONObject jsonObject = results.getJSONObject(i);
                        String questionText = jsonObject.getString("question");
                        boolean correctAns = jsonObject.getBoolean("correct_answer");
                        quizQuestions.add(new QuizQuestion(questionText, correctAns));

                        mCardStackLayoutManager = new CardStackLayoutManager(mContext);
                        CardStackView mCardStackView = activity.findViewById(R.id.myCardStackView);
                        mCardStackView.setLayoutManager(mCardStackLayoutManager);
                        mCardStackLayoutManager.setStackFrom(StackFrom.Top);

                        mCardStackView.setAdapter(new CardStackAdapter(mContext, quizQuestions));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(quizJsonObjectRequest);

    }
}
