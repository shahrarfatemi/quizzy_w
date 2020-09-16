package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import model.UserInfo;
import network.NetworkUtil;
import task.UiLoginTask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button loginButton;
    Button signupNavigationButton;
    EditText emailEditText;
    EditText passEditText;

    NetworkUtil networkUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        loginButton = (Button) findViewById(R.id.loginButton);
        signupNavigationButton = (Button) findViewById(R.id.signupNavigationButton);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);

        loginButton.setOnClickListener(this);
        signupNavigationButton.setOnClickListener(this);

        networkUtil = new NetworkUtil();
    }

    @Override
    public void onClick(View v) {
        if(v == loginButton){
            String email = emailEditText.getText().toString();
            String password = passEditText.getText().toString();
            Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show();
            networkUtil.handleLogin(email, password, new UiLoginTask() {
                @Override
                public void logIn(UserInfo userInfo) {
                    signupNavigationButton.setText(userInfo.getToken());
                    Log.d("nav ","to home page "+userInfo.getUser().getName());
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("MyInfo",userInfo);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}