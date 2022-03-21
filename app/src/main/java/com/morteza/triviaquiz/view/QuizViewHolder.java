package com.morteza.triviaquiz.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.morteza.triviaquiz.R;

public class QuizViewHolder extends RecyclerView.ViewHolder {

    private TextView txtQuizQuestino;
    private Button trueBtn;
    private Button falseBtn;

    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);

        txtQuizQuestino = itemView.findViewById(R.id.questionTextView);
        trueBtn = itemView.findViewById(R.id.truebtn);
        falseBtn = itemView.findViewById(R.id.falsebtn);
    }

    public TextView getTxtQuizQuestino() {
        return txtQuizQuestino;
    }

    public Button getTrueBtn() {
        return trueBtn;
    }

    public Button getFalseBtn() {
        return falseBtn;
    }
}
