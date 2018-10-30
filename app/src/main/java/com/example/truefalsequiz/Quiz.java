package com.example.truefalsequiz;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private List<Question> questions;
    private int score;
    private int questionNumber;

    public Quiz(List<Question> questions, int score, int questionNumber) {
        this.questions = questions;
        this.score = score;
        this.questionNumber = questionNumber;
    }

    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
        this.questionNumber = 0;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Boolean lastQuestion() {
        return questionNumber >= questions.size();
    }

    public void build(List<Question> allQuestions, int numQuestions) {
        Collections.shuffle(allQuestions);
        List<Question> selectedQuestions = new ArrayList<>();
        for(int i = 1; i<=numQuestions; i++) {
            Question shiftQuestion = allQuestions.get(i-1);
            selectedQuestions.add(shiftQuestion);
        }
        this.questions = selectedQuestions;
        this.score = 0;
        this.questionNumber = 0;
    }

    public Question getNextQuestion(){
        if(!lastQuestion()) {
            this.questionNumber++;
            return questions.get(questionNumber-1);
        } else {
            return new Question("error", true);
        }
    }
}
