package com.example.truefalsequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "TrueFalseQuiz";
    String questionsString;

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionsString = readFileToString("questions.json");
        buildQuiz(questionsString, 10);
    }

    private List<Question> gsonTranslateQuestions(String questionsString) {
        Question[] questions =  gson.fromJson(questionsString, Question[].class);
        List<Question> questionList = Arrays.asList(questions);
        Log.d(TAG, "onCreate: " + questionList.toString());
        return questionList;
    }

    private String readFileToString(String fileName) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;

        try{
            inputStream = getAssets().open(fileName);
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

    private Quiz buildQuiz(String jsonQuestionsString, int numQuestions) {
        List<Question> allQuestions = gsonTranslateQuestions(jsonQuestionsString);
        Quiz quiz = new Quiz(allQuestions, 0, numQuestions, 0);
        return quiz;
    }
}
