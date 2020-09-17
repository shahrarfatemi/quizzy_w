package com.example.quizzy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import model.QuizFeed;
import model.QuizResponse;
import model.UserResponse;
import network.NetworkUtilQuiz;
import task.UiShowFeedTask;
import task.UiShowQuizTask;

public class HomeActivity extends AppCompatActivity {

    TextView showText, showFeedText;
    Button showQuizButton, showFeedButton, chooseImageButton, postImageButton;

    NetworkUtilQuiz networkUtilQuiz;
    String token;

    private static final int IMAGE_REQUEST = 1;
    private static final int IMAGE_PERMISSION = 1;

    Uri imageUri;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init(){
        Intent intent = getIntent();
        UserResponse userResponse = (UserResponse) intent.getSerializableExtra("MyInfo");
        token = userResponse.getToken();
        showText = (TextView) findViewById(R.id.showText);
        showFeedText = (TextView) findViewById(R.id.showFeedText);
        showQuizButton = (Button) findViewById(R.id.showButton);
        showFeedButton = (Button) findViewById(R.id.showFeedButton);
        chooseImageButton = (Button) findViewById(R.id.chooseImageButton);
        postImageButton = (Button) findViewById(R.id.postImageButton);

        imageView = (ImageView) findViewById(R.id.imageId);

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
                        Log.d("response in feed  ","got quizzes ");
                        showFeedText.setText(quizzes.get(0).getTitle());
                    }
                });
                Log.d("response in feed  ","pressed show feed button");
            }
        });

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(HomeActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    chooseImage();
                }
                else{
                    requestPermission();
                }
                Log.d("choosing image =>","here to choose image");
            }
        });

        postImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("posting image =>","here to post image");
            }
        });

    }

    public void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this).
                    setTitle("Permission needed").
                    setMessage("bla bla bla").
                    setPositiveButton("okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(HomeActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                    IMAGE_PERMISSION);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        }
        else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == IMAGE_PERMISSION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(HomeActivity.this, "Granted", Toast.LENGTH_LONG).show();
                chooseImage();
            }
            else {
                Toast.makeText(HomeActivity.this, "Not granted", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();

//            Picasso.with(this).load(imageUri).into(imageView);
            Picasso.get().load(imageUri).into(imageView);
        }
    }
}