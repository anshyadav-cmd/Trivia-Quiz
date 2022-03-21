package com.morteza.triviaquiz.model;

public class QuizQuestion {
    private String question;
    private boolean correctAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public QuizQuestion(String question, boolean answer) {
        this.question = question;
        this.correctAnswer = answer;
    }
}
