package com.example.truefalsequiz;

public class Question {
    private String question;
    private Boolean answer;

    public Question(String question, Boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question() {
        this.question = "";
        this.answer = true;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

}
