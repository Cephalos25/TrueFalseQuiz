package com.example.truefalsequiz;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_SCORE = "2h;z90ef";
    static final String TAG = "TrueFalseQuiz";
    private String jsonQuestionsString;
    private List<Question> questionsList;
    Quiz quiz = new Quiz();
    private Question currentQuestion;
    private Boolean answerGiven;

    private TextView textViewQuestionNumber;
    private TextView textViewQuestion;
    private Button buttonTrue;
    private Button buttonFalse;
//    ConstraintLayout bgelement = findViewById(R.id.layout_main);

    Gson gson = new Gson();
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        wireWidgets();
        setListeners();
        InputStream questionsStream = getResources().openRawResource(R.raw.questions);
        jsonQuestionsString = readFileToString(questionsStream);
        questionsList = gsonTranslateQuestions(jsonQuestionsString);
        quiz.build(questionsList, 10);
        startQuiz(quiz);
    }

    private void setListeners() {
        buttonTrue.setOnClickListener(this);
        buttonFalse.setOnClickListener(this);
    }

    private void wireWidgets() {
        textViewQuestionNumber = findViewById(R.id.textview_main_questionnumber);
        textViewQuestion = findViewById(R.id.textview_main_question);
        buttonTrue = findViewById(R.id.button_main_true);
        buttonFalse = findViewById(R.id.button_main_false);
    }

    private void updateDisplay() {
        if(!Objects.equals(currentQuestion.getQuestion(), "error")) {
            textViewQuestionNumber.setText(String.format(Locale.US, getString(R.string.quiz_questionnumberfillable), quiz.getQuestionNumber(), quiz.getQuestions().size()));
            textViewQuestion.setText(currentQuestion.getQuestion());
        }
    }

    private void startQuiz(Quiz quiz) {
        quiz.setQuestionNumber(0);
        currentQuestion = quiz.getNextQuestion();
        updateDisplay();
    }

    private void nextQuestion(Quiz quiz) {
        currentQuestion = quiz.getNextQuestion();
        updateDisplay();
    }

    private List<Question> gsonTranslateQuestions(String questionsString) {
        Log.i(TAG, "onCreate: " + questionsString);
        Question[] questions =  gson.fromJson(questionsString, Question[].class);
        List<Question> questionList = new ArrayList<>(Arrays.asList(questions));
        Log.i(TAG, "onCreate: " + questionList.toString());
        return questionList;
    }

    private String readFileToString(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;

        try{
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return outputStream.toString();
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.button_main_true:
                answerGiven = true;
                break;
            case R.id.button_main_false:
                answerGiven = false;
                break;
        }
        if(answerGiven == quiz.getQuestions().get(quiz.getQuestionNumber()-1).getAnswer()) {
            quiz.setScore(quiz.getScore() + 1);
        }
        if(answerGiven == quiz.getQuestions().get(quiz.getQuestionNumber()-1).getAnswer()){
            long time = SystemClock.uptimeMillis();
            view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.DARKEN);
            handler.postAtTime(new Runnable() {
                @Override
                public void run() {
                    view.getBackground().clearColorFilter();
                }
            }, time + 500);
        } else {
            long time = SystemClock.uptimeMillis();
            view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
            handler.postAtTime(new Runnable() {
                @Override
                public void run() {
                    view.getBackground().clearColorFilter();
                }
            }, time + 500);
        }
        if(!quiz.lastQuestion()){
            nextQuestion(quiz);
        } else {
            long time = SystemClock.uptimeMillis();
            handler.postAtTime(new Runnable() {
                @Override
                public void run() {
                    Intent displayIntent = new Intent(MainActivity.this, DisplayScoreActivity.class);
                    displayIntent.putExtra(EXTRA_SCORE, quiz.getScore());
                    startActivity(displayIntent);
                }
            }, time + 500);
        }
    }
}
