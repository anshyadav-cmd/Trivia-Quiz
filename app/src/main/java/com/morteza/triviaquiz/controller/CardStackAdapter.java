package com.morteza.triviaquiz.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.morteza.triviaquiz.R;
import com.morteza.triviaquiz.model.QuizQuestion;
import com.morteza.triviaquiz.view.QuizViewHolder;

import java.security.SecureRandom;
import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private Context mContext;
    private List<QuizQuestion> mQuizQuestions;
    private LayoutInflater mLayoutInflater;
    private final Vibrator mVibrator;

    private int colors[] = {R.color.l_ran1, R.color.l_ran2, R.color.l_ran3, R.color.l_ran4,
            R.color.l_rand5, R.color.l_rand6, R.color.l_rand7, R.color.l_rand8,
            R.color.l_rand9, R.color.l_rand10};
    private SecureRandom mSecureRandom;

    public CardStackAdapter(Context context, List<QuizQuestion> quizName){
        mContext = context;
        mQuizQuestions = quizName;
        mLayoutInflater = LayoutInflater.from(context);
        mSecureRandom = new SecureRandom();
        mVibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);

    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.quiz_view, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, @SuppressLint("RecyclerView") int position) {
        resetButtonbackground(holder);
        randomCardBackground(holder, position);
        holder.getTxtQuizQuestino().setText(mQuizQuestions.get(position).getQuestion());
        holder.getTrueBtn().setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(mQuizQuestions.get(position).isCorrectAnswer()){
                    holder.getTrueBtn().setBackgroundColor(mContext.getResources().getColor((R.color.corr_ans)));
                }else {
                    holder.getTrueBtn().setBackgroundColor(mContext.getResources().getColor((R.color.false_ans)));
                    YoYo.with(Techniques.Shake)
                            .duration(500)
                            .repeat(0)
                            .playOn(holder.getTrueBtn());
                    vibrate();
                }
            }
        });
        holder.getFalseBtn().setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (!mQuizQuestions.get(position).isCorrectAnswer()) {
                    holder.getFalseBtn().setBackgroundColor(mContext.getResources().getColor(R.color.corr_ans));
                } else {
                    vibrate();
                    holder.getFalseBtn().setBackgroundColor(mContext.getResources().getColor(R.color.false_ans));
                    YoYo.with(Techniques.Shake)
                            .duration(500)
                            .repeat(0)
                            .playOn(holder.getFalseBtn());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuizQuestions.size();
    }

    private void randomCardBackground(QuizViewHolder holder, int num){
        holder.itemView.setBackgroundResource(colors[mSecureRandom.nextInt(colors.length)]);
        for (int i = 0 ; i < num ; i++) {
            holder.itemView.setBackgroundResource(colors[mSecureRandom.nextInt(colors.length)]);
        }
    }

    private void resetButtonbackground( QuizViewHolder holder){
        holder.getTrueBtn().setBackgroundColor(Color.parseColor("#2d00f7"));
        holder.getFalseBtn().setBackgroundColor(Color.parseColor("#2d00f7"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void vibrate(){
        final VibrationEffect vibrationEffect = VibrationEffect.createOneShot(150,VibrationEffect.DEFAULT_AMPLITUDE);
        mVibrator.cancel();
        mVibrator.vibrate(vibrationEffect);
    }
}
