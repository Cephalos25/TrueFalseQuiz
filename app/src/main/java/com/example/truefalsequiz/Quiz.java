package com.example.truefalsequiz;

import java.util.List;

public class Quiz {
    private List<Question> questions;
    private int score;
    private int questionsNumber;
    private int questionNumber;

    public Quiz(List<Question> questions, int score, int questionsNumber, int questionNumber) {
        this.questions = questions;
        this.score = score;
        this.questionsNumber = questionsNumber;
        this.questionNumber = questionNumber;
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

    public int getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(int questionNumber) {
        this.questionsNumber = questionNumber;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}
