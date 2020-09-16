package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import model.QuizFeed;
import model.QuizResponse;
import model.UserInfo;
import network.NetworkUtil;
import network.NetworkUtilQuiz;
import task.UiShowFeedTask;
import task.UiShowQuizTask;

public class HomeActivity extends AppCompatActivity {

    TextView showText, showFeedText;
    Button showQuizButton, showFeedButton;

    NetworkUtilQuiz networkUtilQuiz;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init(){
        Intent intent = getIntent();
        UserInfo userInfo = (UserInfo) intent.getSerializableExtra("MyInfo");
        token = userInfo.getToken();
        showText = (TextView) findViewById(R.id.showText);
        showFeedText = (TextView) findViewById(R.id.showFeedText);
        showQuizButton = (Button) findViewById(R.id.showButton);
        showFeedButton = (Button) findViewById(R.id.showFeedButton);

        networkUtilQuiz =  new NetworkUtilQuiz();

        showQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkUtilQuiz.showMyQuiz(token, new UiShowQuizTask() {
                    @Override
                    public void showMyQuizzes(List<QuizResponse> quizzes) {
                        Log.d("response in feed  ","got quizzes");
                        showText.setText(quizzes.get(0).getOwner());
                    }
                });
                Log.d("response in feed  ","pressed show button");
            }
        });

        showFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("tag","General");
                hashMap.put("title","Engineering");
                networkUtilQuiz.showTopFeedQuizzes(token, hashMap, 0, 2, new UiShowFeedTask() {
                    @Override
                    public void showTopFeedQuizzes(List<QuizFeed> quizzes) {
                        Log.d("response in feed  ","got quizzes");
                        showFeedText.setText(quizzes.get(0).getOwner());
                    }
                });
                Log.d("response in feed  ","pressed show feed button");
            }
        });
    }
}