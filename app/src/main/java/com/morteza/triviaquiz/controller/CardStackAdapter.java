package com.morteza.triviaquiz.controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.morteza.triviaquiz.R;
import com.morteza.triviaquiz.view.QuizViewHolder;

import java.security.SecureRandom;
import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private Context mContext;
    private List<String> mQuizName;
    private LayoutInflater mLayoutInflater;

    private int colors[] = {R.color.l_ran1, R.color.l_ran2, R.color.l_ran3, R.color.l_ran4,
            R.color.l_rand5, R.color.l_rand6, R.color.l_rand7, R.color.l_rand8,
            R.color.l_rand9, R.color.l_rand10};
    private SecureRandom mSecureRandom;

    public CardStackAdapter(Context context, List<String> quizName){
        mContext = context;
        mQuizName = quizName;
        mLayoutInflater = LayoutInflater.from(context);
        mSecureRandom = new SecureRandom();
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.quiz_view, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        resetButtonbackground(holder);
        randomCardBackground(holder, position);
        holder.getTxtQuizQuestino().setText(mQuizName.get(position));
        holder.getTrueBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "True is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        holder.getFalseBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Flase is clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuizName.size();
    }

    private void randomCardBackground(QuizViewHolder holder, int num){
        holder.itemView.setBackgroundResource(colors[mSecureRandom.nextInt(colors.length)]);
        for (int i = 0 ; i < num ; i++) {
            holder.itemView.setBackgroundResource(colors[mSecureRandom.nextInt(colors.length)]);
        }
    }

    private void resetButtonbackground( QuizViewHolder holder){
        holder.getTrueBtn().setBackgroundColor(Color.parseColor("#fb8500"));
        holder.getFalseBtn().setBackgroundColor(Color.parseColor("#fb8500"));
    }
}
