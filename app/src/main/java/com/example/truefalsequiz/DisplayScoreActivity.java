package com.example.truefalsequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class DisplayScoreActivity extends AppCompatActivity {
    private int score;

    private TextView scoreTextView;
    private Button newQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayscore);
        Intent receivedIntent = getIntent();
        score = receivedIntent.getIntExtra(MainActivity.EXTRA_SCORE, -1);

        wireWidgets();
        setListeners();
        displayScore();
    }

    private void displayScore() {
        if (score>=0){
            scoreTextView.setText(String.format(Locale.US, getString(R.string.display_score), score));
        } else {
            scoreTextView.setText(R.string.display_noquiz);
        }
    }

    private void setListeners() {
        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newQuizIntent = new Intent(DisplayScoreActivity.this, MainActivity.class);
                startActivity(newQuizIntent);
            }
        });
    }

    private void wireWidgets() {
        newQuizButton = findViewById(R.id.button_display_restart);
        scoreTextView = findViewById(R.id.textView_display_score);
    }
}
